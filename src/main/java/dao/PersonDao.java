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
    private static final String GET_ALL_PERSONS = "select * from person order by id ASC ";
    private static final String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String ADD_PERSON = "INSERT INTO person(name,age) VALUES(?, ?) returning id";
    private static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
    private static final String UPDATE_PERSON = "UPDATE person SET name = ?, age = ? WHERE id = ?";

    Connection connection;

    public PersonDao(Connection connector) throws SQLException, IOException {
        this.connection = connector;
    }

    public PersonDao() throws SQLException, IOException {
        connection = DataBaseConnectorSingleton.getInstance().getConnection();
    }

    public int addPerson(PersonBuilder personBuilder) throws SQLException {
        try(PreparedStatement addPerson = connection.prepareStatement(ADD_PERSON)) {
            addPerson.setString(1, personBuilder.getName());
            addPerson.setInt(2, personBuilder.getAge());

            try(ResultSet resultSet = addPerson.executeQuery()){
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        throw new SQLException();
    }

    public List<PersonBuilder> getPeople() throws SQLException, IOException {
        List<PersonBuilder> peopleList = new ArrayList<>();
        CarDao carDao=new CarDao();
        GasStationDAO gasStationDAO = new GasStationDAO();

        try(PreparedStatement getAllPersons = connection.prepareStatement(GET_ALL_PERSONS)) {
            try(ResultSet resultSet = getAllPersons.executeQuery()){
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    PersonBuilder personBuilder = new PersonBuilder.Builder()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setAge(resultSet.getInt("age"))
                            .setStationList(gasStationDAO.getGasStationsByPersonId(id))
                            .setCar(carDao.getCarByPersonId(id))
                            .build();
                    peopleList.add(personBuilder);
                }
            }
        }
        return peopleList;
    }

    public PersonBuilder getPersonById(int id) throws SQLException, IOException {
        try(PreparedStatement getPersonById = connection.prepareStatement(GET_PERSON_BY_ID)) {
            getPersonById.setInt(1, id);
            CarDao carDao = new CarDao();
            GasStationDAO gasStationDao = new GasStationDAO();
            ResultSet resultSet = getPersonById.executeQuery();

            if (resultSet.next()){
                PersonBuilder personBuilder = new PersonBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setAge(resultSet.getInt("age"))
                        .setStationList(gasStationDao.getGasStationsByPersonId(id))
                        .setCar(carDao.getCarByPersonId(id))
                        .build();

                return personBuilder;
            }
        }
        return null;
    }

    public void updatePerson(PersonBuilder personBuilder, int id) throws SQLException {
        try(PreparedStatement updatePerson = connection.prepareStatement(UPDATE_PERSON)) {
            updatePerson.setString(1, personBuilder.getName());
            updatePerson.setInt(2, personBuilder.getAge());
            updatePerson.setInt(3, id);

            updatePerson.executeUpdate();
        }

    }

    public void deletePerson(int id) throws SQLException {
         try(PreparedStatement deletePerson = connection.prepareStatement(DELETE_PERSON)){
             deletePerson.setInt(1, id);
             deletePerson.executeUpdate();
         }
    }
}
