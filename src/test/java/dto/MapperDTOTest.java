package dto;

import model.CarBuilder;
import model.GasStationBuilder;
import model.PersonBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapperDTOTest {
    MapperDTO mapperDTO;

    @Test
    void  testToPersonDTO(){
      PersonBuilder personBuilder = new PersonBuilder.Builder()
              .setName("ainur")
              .setAge(22)
              .build();

     PersonDTO personDTOWithoutMapper = new PersonDTO();
     personDTOWithoutMapper.setName(personBuilder.getName());
     personDTOWithoutMapper.setAge(personBuilder.getAge());

      PersonDTO personDTO = mapperDTO.toPersonDTO(personBuilder);

      assertEquals(personDTOWithoutMapper.getName(), personDTO.getName(),"Name should match");
      assertEquals(personDTOWithoutMapper.getAge(), personDTO.getAge(),"Age should match");
    }

    @Test
    void testToPersonBuilder(){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("ainur");
        personDTO.setAge(22);

        PersonBuilder personBuilder = MapperDTO.toPersonBuilder(personDTO);

        assertEquals(personDTO.getName(), personBuilder.getName(),"Name should match");
        assertEquals(personDTO.getAge(), personBuilder.getAge(),"Age should match");
    }

    @Test
    void testToCarDTO(){
        CarDTO carDTOWithoutMapper = new CarDTO();
        CarBuilder carBuilder = new CarBuilder.Builder()
                .setModel("BMW")
                .setHorsePower(220)
                .build();

        carDTOWithoutMapper.setModel(carBuilder.getModel());
        carDTOWithoutMapper.setHorsePower(carBuilder.getHorsePower());

        CarDTO carDTOWithMapper = mapperDTO.toCarDTO(carBuilder);

        assertEquals(carDTOWithMapper.getModel(), carDTOWithoutMapper.getModel(),"Model should match");
        assertEquals(carDTOWithMapper.getHorsePower(), carDTOWithoutMapper.getHorsePower(),"HorsePower should match");
    }

    @Test
    void testToCarBuilder(){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("ainur");
        personDTO.setAge(22);

        PersonBuilder personBuilder = MapperDTO.toPersonBuilder(personDTO);

        assertEquals(personBuilder.getName(), personDTO.getName(),"Name should match");
        assertEquals(personBuilder.getAge(), personDTO.getAge(),"Age should match");
    }

    @Test
    void testToGasStationDTO(){
        GasStationBuilder gasStationBuilder = new GasStationBuilder.Builder()
                .setNumber(11)
                .setName("NAAA")
                .build();

        GasStationDTO gasStationDTOWithOutMapper = new GasStationDTO();
        gasStationDTOWithOutMapper.setNumber(gasStationBuilder.getNumber());
        gasStationDTOWithOutMapper.setName(gasStationBuilder.getName());

        GasStationDTO gasStationDTOWithMapper = mapperDTO.toGasStationDTO(gasStationBuilder);

        assertEquals(gasStationDTOWithMapper.getNumber(),gasStationDTOWithOutMapper.getNumber(),"Number should match");
        assertEquals(gasStationDTOWithMapper.getName(),gasStationDTOWithOutMapper.getName(),"Name should match");

    }

    @Test
    void testToGasStationBuilder(){
        GasStationDTO gasStationDTO = new GasStationDTO();
        gasStationDTO.setNumber(11);
        gasStationDTO.setName("NAAA");

        GasStationBuilder gasStationBuilder = MapperDTO.toGasStationBuilder(gasStationDTO);

        assertEquals(gasStationBuilder.getNumber(),gasStationDTO.getNumber(),"Number should match");
        assertEquals(gasStationBuilder.getName(),gasStationDTO.getName(),"Name should match");
    }
}
