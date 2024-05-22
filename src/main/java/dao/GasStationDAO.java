package dao;

import connection.DataBaseConnectorSingleton;
import entity.GasStationBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GasStationDAO {

    public void addStation(GasStationBuilder gasStation) throws SQLException, IOException {
        String sql = "INSERT INTO gas_station VALUES (?,?)";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, gasStation.getName());
            preparedStatement.setInt(2, gasStation.getNumber());
            preparedStatement.executeUpdate();
        }
    }

    public List<GasStationBuilder> getAllStations() throws SQLException, IOException {
        List<GasStationBuilder> gasStations = new ArrayList<>();
        String sql = "SELECT * FROM gas_station";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            Statement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                GasStationBuilder gasStation = new GasStationBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setNumber(resultSet.getInt("number"))
                        .build();

                gasStations.add(gasStation);
            }
        }
        return gasStations;
    }

    public Optional<GasStationBuilder> getStationById(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM gas_station WHERE id = ?";
        GasStationBuilder gasStation = null;

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                gasStation = new GasStationBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setNumber(resultSet.getInt("number"))
                        .build();
            }
        }
        return Optional.ofNullable(gasStation);
    }

    public void updateStation(GasStationBuilder gasStation) throws SQLException, IOException {
        String sql = "UPDATE gas_station SET name = ?, number = ? WHERE id = ?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, gasStation.getName());
            preparedStatement.setInt(2, gasStation.getNumber());
            preparedStatement.setInt(3, gasStation.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteStation(GasStationBuilder gasStation) throws SQLException, IOException {
        String sql = "DELETE FROM gas_station WHERE id = ?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, gasStation.getId());
            preparedStatement.executeUpdate();
        }
    }
}
