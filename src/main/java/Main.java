import dao.CarDao;
import dao.GasStationDAO;
import dao.PersonDao;
import dao.PersonGasStationDAO;
import model.CarBuilder;
import model.GasStationBuilder;
import model.PersonBuilder;
import service.PersonService;

import java.io.IOException;
import java.sql.SQLException;

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

        PersonService personService = new PersonService(personDao);

           person=personDao.getPersonById(1);
        System.out.println(carDao.getCars());

        PersonGasStationDAO personGasStationDAO = new PersonGasStationDAO();

        GasStationDAO gasStationDAO = new GasStationDAO();

        GasStationBuilder gasStationBuilder = new GasStationBuilder.Builder()
                .setNumber(14)
                .setName("GRACOS")
                .build();

    }
}
