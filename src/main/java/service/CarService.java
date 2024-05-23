package service;

import dao.CarDao;
import entity.CarBuilder;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<CarBuilder> getAllCars() throws SQLException {
        return carDao.getCars();
    }

    public CarBuilder getCarById(int id) throws SQLException {
        return carDao.getCarById(id);
    }

    public void addCar(CarBuilder car) throws SQLException {
        carDao.addCar(car);
    }

    public void updateCar(CarBuilder car, int id) throws SQLException {
        carDao.updateCar(car, id);
    }

    public void deleteCar(int id) throws SQLException {
        carDao.deleteCar(id);
    }

}
