package dto;

import model.GasStationBuilder;
import model.PersonBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GasStationDTOTest {
    private GasStationDTO stationDTO = new GasStationDTO();

    @Test
    @Order(1)
    void testSetGetId(){
        int id = 1;
        stationDTO.setId(id);

        assertEquals(id, stationDTO.getId());
    }

    @Test
    @Order(2)
    void testSetGetName(){
        String name = "AINUR";
        stationDTO.setName(name);

        assertEquals(name, stationDTO.getName());
    }

    @Test
    @Order(3)
    void testSetGetNumber(){
        int number = 1;
        stationDTO.setNumber(number);

        assertEquals(number, stationDTO.getNumber());
    }

    @Test
    @Order(4)
    void testSetGetPeople(){
        List<PersonDTO> list = new ArrayList<>();

        stationDTO.setPersonDTOList(list);

        assertEquals(list, stationDTO.getPersonDTOList());
    }

    @Test
    @Order(6)
    void testEquals(){
        stationDTO.setNumber(1);

        GasStationDTO clone = stationDTO;

        assertEquals(stationDTO, clone,"Must be the same");
    }

    @Test
    @Order(5)
    void testHashCode(){
        stationDTO.setName("Ainur");
        stationDTO.setNumber(1);
        stationDTO.setPersonDTOList(new ArrayList<PersonDTO>());


        GasStationDTO clone = stationDTO;

        int hashOriginal = stationDTO.hashCode();
        int hashClone = clone.hashCode();

        assertEquals(hashOriginal,hashClone,"Must be the same");
    }

    @Test
    @Order(7)
    void testToString(){
        stationDTO.setName("Ainur");



        String toString = ("GasStationDTO{" +
                "id=" + stationDTO.getId() +
                ", name='" + stationDTO.getName() + '\'' +
                ", number=" + stationDTO.getNumber() +
                ", personDTOList=" + stationDTO.getPersonDTOList() +
                '}');

        assertEquals(toString, stationDTO.toString(),"Must be the same");
    }
}
