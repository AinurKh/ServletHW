package model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarBuilderTest {
    private CarBuilder carBuilder;

    @Test
    @Order(1)
    void testSetGetId(){
        int id = 1;
        carBuilder = new CarBuilder.Builder()
                .setId(id)
                .build();

        assertEquals(id, carBuilder.getId());
    }
    @Test
    @Order(2)
    void testSetGetPersonId(){
        int personId = 1;
        carBuilder = new CarBuilder.Builder()
                .setPersonId(personId)
                .build();

        assertEquals(personId, carBuilder.getPersonId());
    }

    @Test
    @Order(3)
    void testSetGetModel(){
        String model = "BMW";
        carBuilder = new CarBuilder.Builder()
                .setModel(model)
                .build();

        assertEquals(model, carBuilder.getModel());
    }

    @Test
    @Order(4)
    void testSetGetHorsePower(){
        int horsePower = 100;
        carBuilder = new CarBuilder.Builder()
                .setHorsePower(horsePower)
                .build();

        assertEquals(horsePower, carBuilder.getHorsePower());
    }

    @Test
    @Order(6)
    void testEquals(){
        carBuilder = new CarBuilder.Builder()
                .setModel("AUDI")
                .setHorsePower(330)
                .build();

        CarBuilder sameCar = carBuilder;

        CarBuilder differentCar = new CarBuilder.Builder()
                .setModel("BMW")
                .build();

        assertEquals(sameCar, carBuilder,"Must be the same");
        assertNotEquals(differentCar, carBuilder,"Must be different");
    }

    @Test
    @Order(5)
    void testHashCode(){
        carBuilder = new CarBuilder.Builder()
                .setModel("AUDI")
                .setHorsePower(330)
                .build();

        CarBuilder sameCar = carBuilder;

        int hashCodeOfPerson = carBuilder.hashCode();
        int hashCodeOfSamePerson = sameCar.hashCode();

        assertEquals(hashCodeOfPerson, hashCodeOfSamePerson,"Must be the same");
    }

    @Test
    @Order(7)
    void testToString(){
        carBuilder = new CarBuilder.Builder()
                .setModel("AUDI")
                .setHorsePower(330)
                .build();


        String toString = ("CarBuilder{" +
                "id=" + carBuilder.getId() +
                ", personId=" + carBuilder.getPersonId() +
                ", model='" + carBuilder.getModel() + '\'' +
                ", horsePower=" + carBuilder.getHorsePower() +
                '}');

        assertEquals(toString, carBuilder.toString(),"Must be the same");
    }
}
