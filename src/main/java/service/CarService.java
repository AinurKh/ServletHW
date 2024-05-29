package service;

import dao.CarDao;
import dto.CarDTO;
import dto.MapperDTO;
import model.CarBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

    public CarDTO getCarAsDTO(CarBuilder car) throws SQLException {
        return MapperDTO.toCarDTO(car);
    }

    public List<CarDTO> getCarsAsDTO() throws SQLException {
        return carDao.getCars()
                .stream()
                .map(MapperDTO::toCarDTO)
                .collect(Collectors.toList());
    }

    public CarBuilder transformToCar(CarDTO car) throws SQLException {
        return MapperDTO.toCarBuilder(car);
    }

    public void createCarFromDTO(CarDTO car) throws SQLException {
        carDao.addCar(transformToCar(car));
    }

    public void updateCarDTO(CarDTO car, int id) throws SQLException {
        carDao.updateCar(transformToCar(car),id);
    }


}
