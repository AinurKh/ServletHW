import dao.CarDao;
import dao.GasStationDAO;
import dao.PersonDao;
import dao.PersonGasStationDAO;
import entity.CarBuilder;
import entity.GasStationBuilder;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        CarDao carDao = new CarDao();
        PersonDao personDao = new PersonDao();
        CarBuilder builder = new CarBuilder.Builder()
                .setModel("Mercedes")
                .setHorsePower(184)
                .build();

        PersonBuilder person= new PersonBuilder.Builder()
                .setName("Sam")
                .setAge(17)
                .build();

        GasStationDAO gasStationDao = new GasStationDAO();
        GasStationBuilder gasStationBuilder = new GasStationBuilder.Builder()
                .setName("TAIF")
                .setNumber(1)
                .build();

        person = personDao.getPersonById(2);
        gasStationBuilder = gasStationDao.getStationById(1);
        PersonGasStationDAO personGasStationDao = new PersonGasStationDAO();
        personGasStationDao.insertPersonGasStation(person.getId(),gasStationBuilder.getId());



    }
}
