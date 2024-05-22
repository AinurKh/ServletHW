package entity;

public class Car {
    private int id;
    private int personId;
    private String model;
    private int horsePower;

    public Car() {
    }

    public Car(int id, int personId, String model, int horsePower) {
        this.id = id;
        this.personId = personId;
        this.model = model;
        this.horsePower = horsePower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;
        return id == car.id && personId == car.personId && horsePower == car.horsePower && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + model.hashCode();
        result = 31 * result + horsePower;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", personId=" + personId +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
