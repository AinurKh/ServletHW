package dto;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDTOTest {
    CarDTO carDTO = new CarDTO();

    @Test
    @Order(1)
    void testSetGetIdTest(){
        int id = 1;
        carDTO.setId(id);

        assertEquals(id, carDTO.getId(),"id must match");
    }

    @Test
    @Order(3)
    void testSetGetModel(){
        String model = "John Doe";
        carDTO.setModel(model);

        assertEquals(model, carDTO.getModel(),"Model must match");
    }

    @Test
    @Order(2)
    void testSetGetPersonId(){
        int id = 20;
        carDTO.setPersonId(id);

        assertEquals(id, carDTO.getPersonId(),"age must match");
    }

    @Test
    @Order(4)
    void testSetGetHorsePower(){
        int hp = 200;
        carDTO.setHorsePower(hp);

        assertEquals(hp, carDTO.getHorsePower(),"age must match");
    }

    @Test
    @Order(6)
    void testEquals(){
        carDTO.setModel("AUDI");

        CarDTO carCopy = carDTO;

        CarDTO differentCarDTO = new CarDTO();
        differentCarDTO.setModel("BMW");


        assertEquals(carCopy, carDTO,"Must be the same");
        assertNotEquals(differentCarDTO, carDTO,"Must be different");
    }

    @Test
    @Order(5)
    void testHashCode(){
        carDTO.setId(1);
        carDTO.setPersonId(1);
        carDTO.setModel("Audi");
        carDTO.setHorsePower(100);


        CarDTO sameCar = carDTO;

        int hashCodeOfPerson = carDTO.hashCode();
        int hashCodeOfSamePerson = sameCar.hashCode();

        assertEquals(hashCodeOfPerson, hashCodeOfSamePerson,"Must be the same");
    }

    @Test
    @Order(7)
    void testToString(){
        String toString = ("CarDTO{" +
                "id=" + carDTO.getId() +
                ", personId=" + carDTO.getPersonId() +
                ", model='" + carDTO.getModel() + '\'' +
                ", horsePower=" + carDTO.getHorsePower() +
                '}');

        assertEquals(toString, carDTO.toString(),"Must be the same");
    }
}
