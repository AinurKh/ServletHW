package service;

import dao.PersonDao;
import dto.MapperDTO;
import dto.PersonDTO;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<PersonBuilder> getAllPersons() throws SQLException, IOException {
       return personDao.getPeople();
    }

    public PersonBuilder getPersonById(int id) throws SQLException, IOException {
        return personDao.getPersonById(id);
    }

    public void addPerson(PersonBuilder person) throws SQLException, IOException {
        personDao.addPerson(person);
    }
    public void updatePerson(PersonBuilder person, int id) throws SQLException, IOException {
        personDao.updatePerson(person,id);
    }
    public void deletePerson(int id) throws SQLException, IOException {
        personDao.deletePerson(id);
    }

    public List<PersonDTO> getPeopleAsDTO() throws SQLException, IOException {
        return personDao.getPeople()
                .stream()
                .map(MapperDTO::toPersonDTO).
                collect(Collectors.toList());
    }

    public PersonDTO getPersonAsDTO(PersonBuilder person) throws SQLException, IOException {
       return MapperDTO.toPersonDTO(person);
    }

    public PersonBuilder convertToPersonBuilder(PersonDTO person) throws SQLException, IOException {
        return MapperDTO.toPersonBuilder(person);
    }

    public void createPersonDTO(PersonDTO person) throws SQLException, IOException {
        personDao.addPerson(convertToPersonBuilder(person));
    }

    public void updatePersonDTO(PersonDTO person, int id) throws SQLException, IOException {
        personDao.updatePerson(convertToPersonBuilder(person), id);
    }

    public PersonDTO getPersonDTOById(int id) throws SQLException, IOException {
       return MapperDTO.toPersonDTO(personDao.getPersonById(id));
    }




}
