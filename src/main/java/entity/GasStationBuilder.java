package entity;

import java.util.List;

public class GasStationBuilder {
    private int id;
    private String name;
    private int number;
    private List<PersonBuilder> people;

    public GasStationBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.number = builder.number;
        this.people = builder.people;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public List<PersonBuilder> getPeople() {
        return people;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GasStationBuilder)) return false;

        GasStationBuilder that = (GasStationBuilder) o;
        return id == that.id && number == that.number && name.equals(that.name) && people.equals(that.people);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + number;
        result = 31 * result + people.hashCode();
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

    public static class Builder{
        private int id;
        private String name;
        private int number;
        private List<PersonBuilder> people;

       public Builder(){}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setPeople(List<PersonBuilder> people) {
            this.people = people;
            return this;
        }

        public GasStationBuilder build() {
           return new GasStationBuilder(this);
        }
    }
}
