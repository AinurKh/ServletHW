package dao;

import connection.DataBaseConnectorSingleton;
import model.GasStationBuilder;
import model.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GasStationDAO {
   private static final String ADD_STATION = "INSERT INTO gas_station (name,number) VALUES (?,?) returning id";
   private static final String GET_ALL_STATIONS = "SELECT * FROM gas_station";
   private static final String GET_STATION_BY_ID = "SELECT * FROM gas_station WHERE id = ?";
   private static final String UPDATE_STATION = "UPDATE gas_station SET name = ?, number = ? WHERE id = ?";
   private static final String DELETE_STATION = "DELETE FROM gas_station WHERE id = ?";
   private static final String GET_PERSONS_OF_STATION = "SELECT * FROM person JOIN person_gas_station ON person.id = person_gas_station.person_id WHERE person_gas_station.gas_station_id = ?";
   private static final String GET_STATIONS_BY_PERSON_ID = "SELECT * FROM gas_station JOIN person_gas_station ON gas_station.id = person_gas_station.gas_station_id WHERE person_gas_station.person_id = ?";

    private final Connection connection;


    public GasStationDAO() throws SQLException, IOException {
        connection = DataBaseConnectorSingleton.getInstance().getConnection();
    }

    public GasStationDAO(Connection connector) throws SQLException {
        this.connection = connector;
    }

    public int addStation(GasStationBuilder gasStation) throws SQLException {
        try(PreparedStatement insertStation  = connection.prepareStatement(ADD_STATION)) {
            insertStation.setString(1, gasStation.getName());
            insertStation.setInt(2, gasStation.getNumber());

            try(ResultSet resultSet= insertStation.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        throw new SQLException();
    }

    public List<GasStationBuilder> getAllStations() throws SQLException {
        List<GasStationBuilder> gasStations = new ArrayList<>();
           try(PreparedStatement getAllStations = connection.prepareStatement(GET_ALL_STATIONS)) {
               try (ResultSet resultSet = getAllStations.executeQuery()) {
                   while(resultSet.next()) {
                       GasStationBuilder gasStation = new GasStationBuilder.Builder()
                               .setId(resultSet.getInt("id"))
                               .setName(resultSet.getString("name"))
                               .setNumber(resultSet.getInt("number"))
                               .build();

                       gasStations.add(gasStation);
                   }
               }
           }
        return gasStations;
    }

    public GasStationBuilder getStationById(int id) throws SQLException {
        try(PreparedStatement getStationById = connection.prepareStatement(GET_STATION_BY_ID)) {
            getStationById.setInt(1, id);
            try (ResultSet resultSet = getStationById.executeQuery()) {
                if(resultSet.next()) {
                    GasStationBuilder gasStation = new GasStationBuilder.Builder()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setNumber(resultSet.getInt("number"))
                            .build();

                    return gasStation;
                }
            }
        }
        return null;
    }

    public void updateStation(GasStationBuilder gasStation, int id) throws SQLException {

       try(PreparedStatement updateStation = connection.prepareStatement(UPDATE_STATION)){
           updateStation.setString(1, gasStation.getName());
           updateStation.setInt(2, gasStation.getNumber());
           updateStation.setInt(3, id);

           updateStation.executeUpdate();
       }
    }

    public void deleteStation(int id) throws SQLException {
        try(PreparedStatement deleteStation = connection.prepareStatement(DELETE_STATION)) {
            deleteStation.setInt(1, id);
            deleteStation.executeUpdate();
        }
    }

    public List<PersonBuilder> getPersonsOfStation(int stationId) throws SQLException, IOException {
        List<PersonBuilder> persons = new ArrayList<>();
        CarDao carDao = new CarDao();
        PersonDao personDao = new PersonDao();

       try(PreparedStatement getPersonsOfStation = connection.prepareStatement(GET_PERSONS_OF_STATION)){
           getPersonsOfStation.setInt(1, stationId);
           try (ResultSet resultSet = getPersonsOfStation.executeQuery()) {
               while(resultSet.next()) {
                   PersonBuilder personBuilder = new PersonBuilder.Builder()
                           .setId(resultSet.getInt("id"))
                           .setName(resultSet.getString("name"))
                           .setAge(resultSet.getInt("age"))
                           .setStationList(getGasStationsByPersonId(resultSet.getInt("id")))
                           .setCar(Optional.ofNullable(carDao.getCarById(resultSet.getInt("id"))))
                           .build();
                   persons.add(personBuilder);
               }
           }
       }
       return persons;
    }

    public    List<GasStationBuilder> getGasStationsByPersonId(int id) throws SQLException, IOException {
        List<GasStationBuilder> gasStations = new ArrayList<>();
        try(PreparedStatement getGasStationsByPersonId = connection.prepareStatement(GET_STATIONS_BY_PERSON_ID)) {
            getGasStationsByPersonId.setInt(1,id);
            try(ResultSet resultSet = getGasStationsByPersonId.executeQuery()) {
                while (resultSet.next()) {
                    GasStationBuilder stationBuilder = new GasStationBuilder.Builder()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setNumber(resultSet.getInt("number"))
                            .build();
                    gasStations.add(stationBuilder);
                }
            }
        }
        return gasStations;
    }


}
