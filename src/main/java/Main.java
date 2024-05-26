import dao.CarDao;
import dao.GasStationDAO;
import dao.PersonDao;
import dao.PersonGasStationDAO;
import dto.MapperDTO;
import dto.PersonDTO;
import entity.CarBuilder;
import entity.GasStationBuilder;
import entity.PersonBuilder;
import service.PersonService;

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

        person=personDao.getPersonById(1);
        PersonService service =new PersonService(personDao);
        PersonDTO personDTO = service.getPersonDTOById(14);
        personDTO.setName("John");
        service.updatePersonDTO(personDTO, personDTO.getId());
    }
}
