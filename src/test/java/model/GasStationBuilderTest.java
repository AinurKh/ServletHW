package model;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GasStationBuilderTest {
    private GasStationBuilder gasStationBuilder;

    @Test
    @Order(1)
    void testSetGetId(){
        int id = 1;
        gasStationBuilder = new GasStationBuilder.Builder()
                .setId(id)
                .build();

        assertEquals(id, gasStationBuilder.getId());
    }

    @Test
    @Order(2)
    void testSetGetName(){
        String name = "AINUR";
        gasStationBuilder = new GasStationBuilder.Builder()
                .setName(name)
                .build();

        assertEquals(name, gasStationBuilder.getName());
    }

    @Test
    @Order(3)
    void testSetGetNumber(){
        int number = 1;
        gasStationBuilder = new GasStationBuilder.Builder()
                .setNumber(number)
                .build();

        assertEquals(number, gasStationBuilder.getNumber());
    }

    @Test
    @Order(4)
    void testSetGetPeople(){
        List< PersonBuilder> list = new ArrayList<>();

        gasStationBuilder = new GasStationBuilder.Builder()
                .setPeople(list)
                .build();

        assertEquals(list, gasStationBuilder.getPeople());
    }

    @Test
    @Order(6)
    void testEquals(){
        gasStationBuilder = new GasStationBuilder.Builder()
                .setNumber(1)
                .build();

        GasStationBuilder clone = gasStationBuilder;

        assertEquals(gasStationBuilder, clone,"Must be the same");
    }

    @Test
    @Order(5)
    void testHashCode(){
        gasStationBuilder = new GasStationBuilder.Builder()
                .setName("Ainur")
                .setNumber(1)
                .setPeople(new ArrayList<PersonBuilder>())
                .build();

        GasStationBuilder clone = gasStationBuilder;

        int hashOriginal = gasStationBuilder.hashCode();
        int hashClone = clone.hashCode();

        assertEquals(hashOriginal,hashClone,"Must be the same");
    }

    @Test
    @Order(7)
    void testToString(){
        gasStationBuilder = new GasStationBuilder.Builder()
                .setName("Ainur")
                .build();


        String toString = ("GasStation{" +
                "id=" + gasStationBuilder.getId() +
                ", name='" + gasStationBuilder.getName() + '\'' +
                ", number=" + gasStationBuilder.getNumber() +
                ", people=" + gasStationBuilder.getPeople() +
                '}');

        assertEquals(toString, gasStationBuilder.toString(),"Must be the same");
    }

}
