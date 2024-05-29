package dto;

import model.CarBuilder;
import model.GasStationBuilder;
import model.PersonBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonDTOTest {
    PersonDTO person = new PersonDTO();

    @Test
    @Order(1)
    void testSetGetIdTest(){
        int id = 1;
        person.setId(id);

        assertEquals(id, person.getId(),"id must match");
    }

    @Test
    @Order(2)
    void testSetGetName(){
        String name = "John Doe";
        person.setName(name);

        assertEquals(name, person.getName(),"name must match");
    }

    @Test
    @Order(3)
    void testSetGetAge(){
        int age = 20;
        person.setAge(age);

        assertEquals(age, person.getAge(),"age must match");
    }

    @Test
    @Order(4)
    void testSetGetCar(){
        CarDTO carDTO = new CarDTO();

        person .setCar(carDTO);

        assertEquals(carDTO, person.getCar(),"carDTO must match");
    }

    @Test
    @Order(5)
    void testSetGetList(){
       List<GasStationDTO> list = new ArrayList<>();

        person.setStationList(list);

        assertEquals(list,person.getStationList(),"Could be same");
    }


    @Test
    @Order(7)
    void testEquals(){
        person.setName("John Doe");
        person.setAge(20);

        PersonDTO personCopy = person;

        PersonDTO differentPersonDTO = new PersonDTO();
        differentPersonDTO.setName("Jacob");


        assertEquals(personCopy, person,"Must be the same");
        assertNotEquals(differentPersonDTO, person,"Must be different");
    }

    @Test
    @Order(6)
    void testHashCode(){
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("AUDI");

        person = new PersonDTO();
        person.setName("SAM");
        person.setCar(carDTO);
        person.setStationList(new ArrayList<GasStationDTO>());
        person.setAge(20);


        PersonDTO samePerson = person;

        int hashCodeOfPerson = person.hashCode();
        int hashCodeOfSamePerson = samePerson.hashCode();

        assertEquals(hashCodeOfPerson, hashCodeOfSamePerson,"Must be the same");
    }

    @Test
    @Order(8)
    void testToString(){
        String toString = ("PersonDTO{" +
                "id=" + person.getId() +
                ", name='" + person.getName() + '\'' +
                ", age=" + person.getAge() +
                ", car=" + person.getCar() +
                ", stationList=" + person.getStationList() +
                '}');

        assertEquals(toString, person.toString(),"Must be the same");
    }
}
