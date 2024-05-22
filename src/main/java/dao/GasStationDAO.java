package dao;

import connection.DataBaseConnectorSingleton;
import entity.GasStationBuilder;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GasStationDAO {
   private static final String ADD_STATION = "INSERT INTO gas_station (name,number) VALUES (?,?)";
   private static final String GET_ALL_STATIONS = "SELECT * FROM gas_station";
   private static final String GET_STATION_BY_ID = "SELECT * FROM gas_station WHERE id = ?";
   private static final String UPDATE_STATION = "UPDATE gas_station SET name = ?, number = ? WHERE id = ?";
   private static final String DELETE_STATION = "DELETE FROM gas_station WHERE id = ?";
   private static final String GET_PERSONS_OF_STATION = "SELECT * FROM person JOIN person_gas_station ON person.id = person_gas_station.person_id WHERE person_gas_station.gas_station_id = ?";


   private final PreparedStatement getAllStations;
   private final PreparedStatement getStationById;
   private final PreparedStatement updateStation;
   private final PreparedStatement deleteStation;
   private final PreparedStatement insertStation;
   private final PreparedStatement getPersonsOfStation;

    public GasStationDAO() throws SQLException, IOException {
        Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();

        getAllStations= connection.prepareStatement(GET_ALL_STATIONS);
        getStationById = connection.prepareStatement(GET_STATION_BY_ID);

        updateStation= connection.prepareStatement(UPDATE_STATION);
        deleteStation= connection.prepareStatement(DELETE_STATION);
        insertStation= connection.prepareStatement(ADD_STATION);
        getPersonsOfStation = connection.prepareStatement(GET_PERSONS_OF_STATION);
    }

    public void addStation(GasStationBuilder gasStation) throws SQLException {
        insertStation.setString(1, gasStation.getName());
        insertStation.setInt(2, gasStation.getNumber());

        insertStation.executeUpdate();
    }

    public List<GasStationBuilder> getAllStations() throws SQLException {
        List<GasStationBuilder> gasStations = new ArrayList<>();
            ResultSet resultSet = getAllStations.executeQuery();
            while(resultSet.next()) {
                GasStationBuilder gasStation = new GasStationBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setNumber(resultSet.getInt("number"))
                        .build();

                gasStations.add(gasStation);
            }
        return gasStations;
    }

    public GasStationBuilder getStationById(int id) throws SQLException {
        getStationById.setInt(1, id);
        ResultSet resultSet = getStationById.executeQuery();

        if(resultSet.next()) {
            GasStationBuilder gasStation = new GasStationBuilder.Builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setNumber(resultSet.getInt("number"))
                    .build();

            return gasStation;
        } else
            return null;
    }

    public void updateStation(GasStationBuilder gasStation, int id) throws SQLException {
        updateStation.setString(1, gasStation.getName());
        updateStation.setInt(2, gasStation.getNumber());
        updateStation.setInt(3, id);

        updateStation.executeUpdate();
    }

    public void deleteStation(int id) throws SQLException {
            deleteStation.setInt(1, id);
            deleteStation.executeUpdate();
    }

    public List<PersonBuilder> getPersonsOfStation(int stationId) throws SQLException, IOException {
        List<PersonBuilder> persons = new ArrayList<>();
        CarDao carDao = new CarDao();
        PersonDao personDao = new PersonDao();
        getPersonsOfStation.setInt(1, stationId);
        ResultSet resultSet = getPersonsOfStation.executeQuery();
        while(resultSet.next()) {
            PersonBuilder personBuilder = new PersonBuilder.Builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setAge(resultSet.getInt("age"))
                    .setStationList(personDao.getGasStations(resultSet.getInt("id")))
                    .setCar(carDao.getCarById(resultSet.getInt("id")))
                    .build();
            persons.add(personBuilder);
        }
        return persons;
    }



}
