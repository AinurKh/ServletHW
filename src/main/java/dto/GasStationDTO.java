package dto;

import java.util.List;

public class GasStationDTO {
    private int id;
    private String name;
    private int number;
    private List<PersonDTO> personDTOList;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<PersonDTO> getPersonDTOList() {
        return personDTOList;
    }

    public void setPersonDTOList(List<PersonDTO> personDTOList) {
        this.personDTOList = personDTOList;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GasStationDTO)) return false;

        GasStationDTO that = (GasStationDTO) o;
        return id == that.id && number == that.number && name.equals(that.name) && personDTOList.equals(that.personDTOList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + number;
        result = 31 * result + personDTOList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GasStationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", personDTOList=" + personDTOList +
                '}';
    }
}
