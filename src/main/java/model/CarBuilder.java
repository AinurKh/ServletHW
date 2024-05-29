package model;

public class CarBuilder {
    private int id;
    private int personId;
    private String model;
    private int horsePower;

    private CarBuilder(Builder builder){
        this.id=builder.id;
        this.personId=builder.personId;
        this.model=builder.model;
        this.horsePower=builder.horsePower;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public String getModel() {
        return model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarBuilder)) return false;

        CarBuilder that = (CarBuilder) o;
        return id == that.id && personId == that.personId && horsePower == that.horsePower && model.equals(that.model);
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
        return "CarBuilder{" +
                "id=" + id +
                ", personId=" + personId +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }

    public static class Builder {
        private int id;
        private int personId;
        private String model;
        private int horsePower;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPersonId(int personId) {
            this.personId = personId;
            return this;
        }


        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setHorsePower(int horsePower) {
            this.horsePower = horsePower;
            return this;
        }

        public CarBuilder build(){
            return new CarBuilder(this);
        }


    }
}
