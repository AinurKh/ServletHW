package dao;

import connection.DataBaseConnectorSingleton;
import entity.CarBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao {

    public List<CarBuilder> getCars() throws SQLException, IOException {
        String sql = "select * from car";
        List<CarBuilder> carBuilderList = new ArrayList<>();

        try(Connection connection= DataBaseConnectorSingleton.getInstance().getConnection();
            Statement statement = connection.prepareStatement(sql)){

            ResultSet resultSet=statement.getResultSet();
            while(resultSet.next()){
                CarBuilder carBuilder = new CarBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setPersonId(resultSet.getInt("person_id"))
                        .setModel(resultSet.getString("model"))
                        .setHorsePower(resultSet.getInt("horse_power"))
                        .build();

                carBuilderList.add(carBuilder);
            }
        }
        return carBuilderList;
    }

    public Optional<CarBuilder> getCarById(int id) throws SQLException, IOException {
        String sql = "select * from car where id=?";
        CarBuilder carBuilder = null;

        try(Connection connection= DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                carBuilder = new CarBuilder.Builder()
                        .setId(resultSet.getInt("id"))
                        .setPersonId(resultSet.getInt("person_id"))
                        .setModel(resultSet.getString("model"))
                        .setHorsePower(resultSet.getInt("horse_power"))
                        .build();
            }
            return Optional.ofNullable(carBuilder);
        }
    }

    public void addCar(CarBuilder carBuilder) throws SQLException, IOException {
        String sql = "insert into car values(?,?,?,?)";

        try(Connection connection= DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql)){

            preparedStatement.setInt(1, carBuilder.getId());
            preparedStatement.setInt(2, carBuilder.getPersonId());
            preparedStatement.setString(3, carBuilder.getModel());
            preparedStatement.setInt(4, carBuilder.getHorsePower());

            preparedStatement.executeUpdate();
        }
    }

    public void updateCar(CarBuilder carBuilder) throws SQLException, IOException {
        String sql = "update car set model=?, horse_power=? where id=?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, carBuilder.getModel());
            statement.setInt(2, carBuilder.getHorsePower());
            statement.setInt(3, carBuilder.getId());

            statement.executeUpdate();
        }
    }

    public void deleteCar(CarBuilder carBuilder) throws SQLException, IOException {
        String sql = "delete from car where id=?";

        try(Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, carBuilder.getId());
            preparedStatement.executeUpdate();
        }
    }
}
