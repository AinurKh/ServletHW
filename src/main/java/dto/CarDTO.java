package dto;

public class CarDTO {
    private int id;
    private int personId;
    private String model;
    private int horsePower;

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
        if (!(o instanceof CarDTO)) return false;

        CarDTO carDTO = (CarDTO) o;
        return id == carDTO.id && personId == carDTO.personId && horsePower == carDTO.horsePower && model.equals(carDTO.model);
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
        return "CarDTO{" +
                "id=" + id +
                ", personId=" + personId +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
