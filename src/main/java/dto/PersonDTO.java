package dto;

import java.util.List;

public class PersonDTO {
    private int id;
    private String name;
    private int age;
    private CarDTO car;
    private List<GasStationDTO> stationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public List<GasStationDTO> getStationList() {
        return stationList;
    }

    public void setStationList(List<GasStationDTO> stationList) {
        this.stationList = stationList;
    }
}
