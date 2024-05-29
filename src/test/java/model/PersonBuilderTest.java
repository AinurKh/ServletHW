package model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonBuilderTest {
    private PersonBuilder person;

    @Test
    @Order(1)
    void testSetGetIdTest(){
        int id = 1;
        person = new PersonBuilder.Builder()
                .setId(id)
                .build();

        assertEquals(id, person.getId(),"id must match");
    }

    @Test
    @Order(2)
    void testSetGetName(){
        String name = "John Doe";
        person = new PersonBuilder.Builder()
                .setName(name)
                .build();
        assertEquals(name, person.getName(),"name must match");
    }

    @Test
    @Order(3)
    void testSetGetAge(){
        int age = 20;
        person = new PersonBuilder.Builder()
                .setAge(age)
                .build();

        assertEquals(age, person.getAge(),"age must match");
    }
    @Test
    @Order(4)
    void testSetGetCar(){
        CarBuilder carBuilder = new CarBuilder.Builder()
                .setModel("AUDI")
                .setHorsePower(230)
                .build();

        person = new PersonBuilder.Builder()
                .setCar(Optional.ofNullable(carBuilder))
                .build();

        assertEquals(Optional.ofNullable(carBuilder), person.getCar(),"car must match");
    }

    @Test
    @Order(5)
    void testSetGetList(){
        GasStationBuilder gasStationBuilder = new GasStationBuilder.Builder()
                .setName("TAIF")
                .setNumber(22)
                .build();
        List<GasStationBuilder> list = new ArrayList<>();
        list.add(gasStationBuilder);

        person = new PersonBuilder.Builder()
                .setStationList(list)
                .build();

        assertEquals(list,person.getStationList(),"Could be same");
    }


    @Test
    @Order(7)
    void testEquals(){
        person = new PersonBuilder.Builder()
                .setName("John Doe")
                .setAge(20)
                .build();
        PersonBuilder samePerson = person;

        PersonBuilder differentPerson = new PersonBuilder.Builder()
                .setName("Jacob")
                .build();

        assertEquals(samePerson, person,"Must be the same");
        assertNotEquals(differentPerson, person,"Must be different");
    }

    @Test
    @Order(6)
    void testHashCode(){
        person = new PersonBuilder.Builder()
                .setName("SAM")
                .setCar(Optional.ofNullable(new CarBuilder.Builder().setModel("BMW").build()))
                .setStationList(new ArrayList<GasStationBuilder>())
                .setAge(20)
                .build();

        PersonBuilder samePerson = person;

        int hashCodeOfPerson = person.hashCode();
        int hashCodeOfSamePerson = samePerson.hashCode();

        assertEquals(hashCodeOfPerson, hashCodeOfSamePerson,"Must be the same");
    }

    @Test
    @Order(8)
    void testToString(){
        person = new PersonBuilder.Builder()
                .setName("SAM")
                .setAge(20)
                .build();

        String toString = ("PersonBuilder{" +
                "id=" + person.getId() +
                ", name='" + person.getName() + '\'' +
                ", age=" + person.getAge() +
                ", carBuilder=" + person.getCar() +
                ", stationList=" + person.getStationList() +
                '}');

        assertEquals(toString, person.toString(),"Must be the same");
    }
}
