package entity;

import java.util.List;

public class GasStation {
    private int id;
    private String name;
    private int number;
    private List<Person> people;

    public GasStation() {}

    public GasStation(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GasStation)) return false;

        GasStation that = (GasStation) o;
        return id == that.id && number == that.number && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", people=" + people +
                '}';
    }
}
