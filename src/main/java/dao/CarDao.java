package dao;

import connection.DataBaseConnectorSingleton;
import entity.CarBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static final String GET_CARS= "select * from car";
    private static final String GET_CAR_BY_ID= "select * from car where car_id=?";
    private static final String GET_CAR_BY_PERSON_ID= "select * from car where person_id=?";
    private static final String ADD_CAR = "insert into car (person_id,model,horse_power) VALUES (?,?,?) returning car_id";
    private static final String DELETE_CAR= "delete from car where car_id=?";
    private static final String UPDATE_CAR= "update car set model = ?, horse_power = ? where car_id=?";
    private final Connection connection;

    public CarDao() throws SQLException, IOException {
       connection = DataBaseConnectorSingleton.getInstance().getConnection();
    }

    public CarDao(Connection connection) {
        this.connection = connection;
    }

    public List<CarBuilder> getCars() throws SQLException {
        List<CarBuilder> carList = new ArrayList<>();
        try(PreparedStatement getCars= connection.prepareStatement(GET_CARS);
           ResultSet resultSet=getCars.executeQuery()){

            while(resultSet.next()){
               CarBuilder carBuilder = new CarBuilder.Builder()
                       .setId(resultSet.getInt("car_id"))
                       .setPersonId(resultSet.getInt("person_id"))
                       .setModel(resultSet.getString("model"))
                       .setHorsePower(resultSet.getInt("horse_power"))
                       .build();

               carList.add(carBuilder);
           }
       }

        return carList;
    }

    public CarBuilder getCarById(int id) throws SQLException {
        try(PreparedStatement getCarById=connection.prepareStatement(GET_CAR_BY_ID)){
            getCarById.setInt(1, id);
           try(ResultSet resultSet=getCarById.executeQuery();){
               if(resultSet.next()){
                   CarBuilder carBuilder = new CarBuilder.Builder()
                           .setId(resultSet.getInt("car_id"))
                           .setPersonId(resultSet.getInt("person_id"))
                           .setModel(resultSet.getString("model"))
                           .setHorsePower(resultSet.getInt("horse_power"))
                           .build();

                   return carBuilder;
               }
           }
       }
        return null;
    }

    public int addCar(CarBuilder carBuilder) throws SQLException {
        try(PreparedStatement addCar = connection.prepareStatement(ADD_CAR)){

            addCar.setInt(1, carBuilder.getPersonId());
            addCar.setString(2, carBuilder.getModel());
            addCar.setInt(3, carBuilder.getHorsePower());

            try(ResultSet resultSet= addCar.executeQuery();) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        throw new SQLException();
    }

    public void updateCar(CarBuilder carBuilder, int id) throws SQLException {
        try(PreparedStatement updateCar = connection.prepareStatement(UPDATE_CAR)){

            updateCar.setString(1, carBuilder.getModel());
            updateCar.setInt(2, carBuilder.getHorsePower());
            updateCar.setInt(3, id);

            updateCar.executeUpdate();
        }
    }

    public void deleteCar(int id) throws SQLException {
        try(PreparedStatement deleteCar = connection.prepareStatement(DELETE_CAR)){
            deleteCar.setInt(1, id);
            deleteCar.executeUpdate();
        }
    }

    public CarBuilder getCarByPersonId(int personId) throws SQLException {
        try(PreparedStatement getCarByPersonId=connection.prepareStatement(GET_CAR_BY_PERSON_ID)){
            getCarByPersonId.setInt(1, personId);
            try (ResultSet resultSet=getCarByPersonId.executeQuery()) {
                if(resultSet.next()){
                    CarBuilder builder = new CarBuilder.Builder()
                            .setPersonId(resultSet.getInt("person_id"))
                            .setHorsePower(resultSet.getInt("horse_power"))
                            .setModel(resultSet.getString("model"))
                            .setId(resultSet.getInt("car_id"))
                            .build();

                    return builder;
                }
            }
        }
        return null;
    }

}
