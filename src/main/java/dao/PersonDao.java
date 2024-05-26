package dao;

import connection.DataBaseConnectorSingleton;
import entity.CarBuilder;
import entity.GasStationBuilder;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private static final String GET_ALL_PERSONS = "select * from person";
    private static final String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String ADD_PERSON = "INSERT INTO person(name,age) VALUES(?, ?) returning id";
    private static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
    private static final String UPDATE_PERSON = "UPDATE person SET name = ?, age = ? WHERE id = ?";
    private static final String GET_STATIONS_LIST = "SELECT * FROM gas_station JOIN person_gas_station ON gas_station.id = person_gas_station.gas_station_id WHERE person_gas_station.person_id = ?";

    private final PreparedStatement getAllPersons;
    private final PreparedStatement getPersonById;
    private final PreparedStatement addPerson;
    private final PreparedStatement deletePerson;
    private final PreparedStatement updatePerson;
    private final PreparedStatement getStationsList;

    public PersonDao() throws SQLException, IOException {
        Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
        getAllPersons=connection.prepareStatement(GET_ALL_PERSONS);
        getPersonById=connection.prepareStatement(GET_PERSON_BY_ID);
        addPerson=connection.prepareStatement(ADD_PERSON);
        deletePerson=connection.prepareStatement(DELETE_PERSON);
        updatePerson=connection.prepareStatement(UPDATE_PERSON);
        getStationsList=connection.prepareStatement(GET_STATIONS_LIST);
    }

    public void addPerson(PersonBuilder personBuilder) throws SQLException {
            addPerson.setString(1, personBuilder.getName());
            addPerson.setInt(2, personBuilder.getAge());
            ResultSet resultSet = addPerson.executeQuery();

//          Для работы через метод main раскоментировать
//            if (checkCar(personBuilder)){
//                addCar(resultSet,personBuilder);
//            }
    }

    public List<PersonBuilder> getPeople() throws SQLException, IOException {
        List<PersonBuilder> peopleList = new ArrayList<>();
        CarDao carDao=new CarDao();
        ResultSet resultSet = getAllPersons.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                PersonBuilder personBuilder = new PersonBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setAge(resultSet.getInt("age"))
                        .setCar(carDao.getCarById(id))
                        .build();
                peopleList.add(personBuilder);
            }
        return peopleList;
    }

    public PersonBuilder getPersonById(int id) throws SQLException, IOException {
        getPersonById.setInt(1, id);
        CarDao carDao = new CarDao();
        ResultSet resultSet = getPersonById.executeQuery();

        if (resultSet.next()){
            PersonBuilder personBuilder = new PersonBuilder.Builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setAge(resultSet.getInt("age"))
                    .setStationList(getGasStations(id))
                    .setCar(carDao.getCarById(id))
                    .build();

            return personBuilder;
        }
        return null;
    }

    public void updatePerson(PersonBuilder personBuilder, int id) throws SQLException {
        updatePerson.setString(1, personBuilder.getName());
        updatePerson.setInt(2, personBuilder.getAge());
        updatePerson.setInt(3, id);

        updatePerson.executeUpdate();
    }

    public void deletePerson(int id) throws SQLException {
            deletePerson.setInt(1, id);
            deletePerson.executeUpdate();
    }

    // Проверка есть ли у мужика уже машина, если есть то ее сразу вместе засунуть.
    private boolean checkCar(PersonBuilder person){
       return person.getCar().isPresent();
    }

    // Засунуть авто вместе с владельцем
    private void addCar(ResultSet resultSet, PersonBuilder personBuilder) throws SQLException, IOException {
        if (resultSet.next()) {
            int id = resultSet.getInt(1);

            CarBuilder car = new CarBuilder.Builder()
                    .setPersonId(id)
                    .setModel(personBuilder.getCar().get().getModel())
                    .setHorsePower(personBuilder.getCar().get().getHorsePower())
                    .build();

            CarDao carDao = new CarDao();

            carDao.addCar(car);
        }
    }


    public    List<GasStationBuilder> getGasStations(int id) throws SQLException, IOException {
        List<GasStationBuilder> gasStations = new ArrayList<>();
        String SQL = "SELECT * FROM gas_station JOIN person_gas_station ON gas_station.id = person_gas_station.gas_station_id WHERE person_gas_station.person_id = ?";
        getStationsList.setInt(1,id);
        ResultSet resultSet = getStationsList.executeQuery();
        while (resultSet.next()) {
            GasStationBuilder stationBuilder = new GasStationBuilder.Builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setNumber(resultSet.getInt("number"))
                    .build();
            gasStations.add(stationBuilder);
        }
        return gasStations;
    }


}
