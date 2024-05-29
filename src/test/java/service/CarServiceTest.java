package service;

import dao.CarDao;
import dto.CarDTO;
import dto.MapperDTO;
import model.CarBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarDao carDao;
    @InjectMocks
    private CarService carService;
    private CarBuilder carBuilder;
    private CarDTO carDTO;


    @BeforeEach
    void setUp() {
       carBuilder= new CarBuilder.Builder()
               .setModel("AUDI")
               .setHorsePower(220)
               .build();

       carDTO = new CarDTO();
       carDTO.setModel("AUDI");
       carDTO.setHorsePower(220);
    }

    @Test
    void getAllCars() throws SQLException {
        List<CarBuilder>list = new ArrayList<>();
        list.add(carBuilder);

        when(carDao.getCars()).thenReturn(list);

        List<CarBuilder>result = carService.getAllCars();

        assertEquals(result.size(), 1);
    }

    @Test
    void getCarById() throws SQLException {
        int id = 1;
        when(carDao.getCarById(id)).thenReturn(carBuilder);
        CarBuilder builder = carService.getCarById(id);

       assertEquals(carBuilder,builder);
    }

    @Test
    void addCar() throws SQLException {
        carService.addCar(carBuilder);
        verify(carDao,times(1)).addCar(carBuilder);
    }

    @Test
    void updateCar() throws SQLException {
        int id = 1;
        carService.updateCar(carBuilder,id);

        verify(carDao,times(1)).updateCar(carBuilder,id);
    }

    @Test
    void deleteCar() throws SQLException {
        int id = 1;
        carService.deleteCar(id);
        verify(carDao,times(1)).deleteCar(id);
    }

    @Test
    void getCarsAsDTO() throws SQLException {
        List<CarBuilder> list = new ArrayList<>();
        list.add(carBuilder);

        when(carDao.getCars()).thenReturn(list);
        List<CarDTO> result = carService.getCarsAsDTO();

        assertEquals(1, result.size());
        assertEquals("AUDI", result.get(0).getModel());
    }

    @Test
    void transformToCar() throws SQLException {
       carBuilder = carService.transformToCar(carDTO);

       assertEquals(carDTO.getModel(),carBuilder.getModel());
    }

    @Test
    void createCarFromDTO() throws SQLException {
        carService.createCarFromDTO(carDTO);
        verify(carDao,times(1)).addCar(any(CarBuilder.class));
    }

    @Test
    void updateCarDTO() throws SQLException {
       int id = 1;
        carService.updateCarDTO(carDTO,id);
        verify(carDao,times(1)).updateCar(any(CarBuilder.class),eq(id));
    }
}
