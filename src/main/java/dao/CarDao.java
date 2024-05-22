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
    private static final String ADD_CAR = "insert into car (person_id, model,horse_power) VALUES (?,?,?)";
    private static final String DELETE_CAR= "delete from car where car_id=?";
    private static final String UPDATE_CAR= "update car set model = ?, horse_power = ? where id=?";

    private final PreparedStatement getCars;

    private final PreparedStatement getCarById;
    private final PreparedStatement getCarByPersonId;
    private final PreparedStatement addCar;
    private final PreparedStatement deleteCar;
    private final PreparedStatement updateCar;

    public CarDao() throws SQLException, IOException {
        Connection connection = DataBaseConnectorSingleton.getInstance().getConnection();
        getCars= connection.prepareStatement(GET_CARS);
        addCar =connection.prepareStatement(ADD_CAR);
        deleteCar=connection.prepareStatement(DELETE_CAR);
        updateCar=connection.prepareStatement(UPDATE_CAR);
        getCarById=connection.prepareStatement(GET_CAR_BY_ID);
        getCarByPersonId=connection.prepareStatement(GET_CAR_BY_PERSON_ID);
    }

    public List<CarBuilder> getCars() throws SQLException {
        List<CarBuilder> carList = new ArrayList<>();
        ResultSet resultSet=getCars.executeQuery();

        while(resultSet.next()){
                CarBuilder carBuilder = new CarBuilder.Builder()
                        .setId(resultSet.getInt("car_id"))
                        .setPersonId(resultSet.getInt("person_id"))
                        .setModel(resultSet.getString("model"))
                        .setHorsePower(resultSet.getInt("horse_power"))
                        .build();

                carList.add(carBuilder);
            }
        return carList;
    }

    public CarBuilder getCarById(int id) throws SQLException {
        getCarById.setInt(1, id);
        ResultSet resultSet=getCarById.executeQuery();
            if(resultSet.next()){
               CarBuilder carBuilder = new CarBuilder.Builder()
                        .setId(resultSet.getInt("car_id"))
                        .setPersonId(resultSet.getInt("person_id"))
                        .setModel(resultSet.getString("model"))
                        .setHorsePower(resultSet.getInt("horse_power"))
                        .build();

             return carBuilder;
            }
            return null;
    }

    public void addCar(CarBuilder carBuilder) throws SQLException {
            addCar.setInt(1, carBuilder.getPersonId());
            addCar.setString(2, carBuilder.getModel());
            addCar.setInt(3, carBuilder.getHorsePower());

            addCar.executeUpdate();
    }

    public void updateCar(CarBuilder carBuilder, int id) throws SQLException {
            updateCar.setString(1, carBuilder.getModel());
            updateCar.setInt(2, carBuilder.getHorsePower());
            updateCar.setInt(3, id);

            updateCar.executeUpdate();
    }

    public void deleteCar(int id) throws SQLException {
            deleteCar.setInt(1, id);
            deleteCar.executeUpdate();
    }

    public CarBuilder getCarByPersonId(int id) throws SQLException {
        getCarByPersonId.setInt(2, id);
        ResultSet resultSet=getCarById.executeQuery();
        if(resultSet.next()){
            CarBuilder carBuilder = new CarBuilder.Builder()
                    .setId(resultSet.getInt("car_id"))
                    .setPersonId(resultSet.getInt("person_id"))
                    .setModel(resultSet.getString("model"))
                    .setHorsePower(resultSet.getInt("horse_power"))
                    .build();

            return carBuilder;
        }
        return null;
    }

}
