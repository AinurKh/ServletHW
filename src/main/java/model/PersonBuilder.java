package model;

import java.util.List;
import java.util.Optional;

public class PersonBuilder {
    private  int id;
    private  String name;
    private  int age;
    private Optional<CarBuilder> carBuilder = Optional.empty();
    private  List<GasStationBuilder> stationList;

    private PersonBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.carBuilder = builder.carBuilder;
        this.stationList = builder.stationList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Optional<CarBuilder> getCar() {
        return carBuilder;
    }

    public List<GasStationBuilder> getStationList() {
        return stationList;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonBuilder)) return false;

        PersonBuilder that = (PersonBuilder) o;
        return id == that.id && age == that.age && name.equals(that.name) && carBuilder.equals(that.carBuilder) && stationList.equals(that.stationList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + carBuilder.hashCode();
        result = 31 * result + stationList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonBuilder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", carBuilder=" + carBuilder +
                ", stationList=" + stationList +
                '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private int age;
        private Optional<CarBuilder> carBuilder = Optional.empty();
        private List<GasStationBuilder> stationList;

        public Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setCar(Optional<CarBuilder> carBuilder) {
            this.carBuilder = carBuilder;
            return this;
        }

        public Builder setStationList(List<GasStationBuilder> stationList) {
            this.stationList = stationList;
            return this;
        }

        public PersonBuilder build() {
            return new PersonBuilder(this);
        }
    }
}
