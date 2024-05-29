package dao;

import connection.DataBaseConnectorSingleton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonGasStationDAO {
    private Connection connection;

    public PersonGasStationDAO(Connection connection) {
        this.connection = connection;
    }

    public PersonGasStationDAO() throws SQLException, IOException {
        connection = DataBaseConnectorSingleton.getInstance().getConnection();
    }

    public int insertPersonGasStation(int personId, int gasStationId) throws SQLException, IOException {
        String sql = "INSERT INTO person_gas_station (person_id, gas_station_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, personId);
            ps.setInt(2, gasStationId);
            return ps.executeUpdate();
        }
    }

    public int deletePersonGasStation(int personId, int gasStationId) throws SQLException, IOException {
        String sql = "DELETE FROM person_gas_station WHERE person_id = ? AND gas_station_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, personId);
            ps.setInt(2, gasStationId);
            return ps.executeUpdate();
        }
    }
}
