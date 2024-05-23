package service;

import dao.PersonDao;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private final PersonDao personDao;
    public PersonService() throws SQLException, IOException {
        this.personDao=new PersonDao();
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


}
