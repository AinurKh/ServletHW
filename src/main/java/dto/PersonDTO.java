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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO)) return false;

        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && age == personDTO.age && name.equals(personDTO.name) && car.equals(personDTO.car) && stationList.equals(personDTO.stationList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + car.hashCode();
        result = 31 * result + stationList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", stationList=" + stationList +
                '}';
    }
}
