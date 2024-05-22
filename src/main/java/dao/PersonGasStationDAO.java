package dao;

import connection.DataBaseConnectorSingleton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonGasStationDAO {


    public void insertPersonGasStation(int personId, int gasStationId) throws SQLException, IOException {
        String sql = "INSERT INTO person_gas_station (person_id, gas_station_id) VALUES (?, ?)";

        try (Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, gasStationId);
            preparedStatement.executeUpdate();
        }
    }
}
