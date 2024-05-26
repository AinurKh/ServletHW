package dto;

import entity.CarBuilder;
import entity.GasStationBuilder;
import entity.PersonBuilder;

import java.util.stream.Collectors;

public class MapperDTO {

    public static PersonDTO toPersonDTO(PersonBuilder person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setAge(person.getAge());
        if (person.getStationList()!=null){
            personDTO.setStationList(
                    person.getStationList()
                            .stream()
                            .map(MapperDTO::toGasStationDTO)
                            .collect(Collectors.toList()));
        }
        if (person.getCar().isPresent()){
            personDTO.setCar(toCarDTO(person.getCar().get()));
        }
        return personDTO;
    }

    public static PersonBuilder toPersonBuilder(PersonDTO personDTO){
        PersonBuilder person = new PersonBuilder.Builder()
                .setId(personDTO.getId())
                .setName(personDTO.getName())
                .setAge(personDTO.getAge())
                .build();

        return person;
    }

    public static CarDTO toCarDTO(CarBuilder car){
        CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setPersonId(car.getPersonId());
        carDTO.setModel(car.getModel());
        carDTO.setHorsePower(car.getHorsePower());

        return carDTO;
    }

    public static CarBuilder toCarBuilder(CarDTO carDTO){
        CarBuilder car = new CarBuilder.Builder()
                .setId(carDTO.getId())
                .setPersonId(carDTO.getPersonId())
                .setModel(carDTO.getModel())
                .setHorsePower(carDTO.getHorsePower())
                .build();

        return car;
    }

    public static GasStationDTO toGasStationDTO(GasStationBuilder gasStation){
        GasStationDTO gasStationDTO = new GasStationDTO();

        gasStationDTO.setId(gasStation.getId());
        gasStationDTO.setName(gasStation.getName());
        gasStationDTO.setNumber(gasStation.getNumber());
        if (gasStation.getPeople()!=null){
            gasStationDTO.setPersonDTOList(
                    gasStation.getPeople()
                            .stream()
                            .map(MapperDTO::toPersonDTO)
                            .collect(Collectors.toList())
            );
        }
        return gasStationDTO;
    }

    public static GasStationBuilder toGasStationBuilder(GasStationDTO gasStationDTO){
        GasStationBuilder builder = new GasStationBuilder.Builder()
                .setId(gasStationDTO.getId())
                .setName(gasStationDTO.getName())
                .setNumber(gasStationDTO.getNumber())
                .build();

        return builder;
    }
}
