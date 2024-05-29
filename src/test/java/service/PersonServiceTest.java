package service;

import dao.PersonDao;
import dto.MapperDTO;
import dto.PersonDTO;
import model.PersonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonDao personDao;
    @InjectMocks
    private PersonService personService;
    private PersonDTO personDTO;
    private PersonBuilder personBuilder;

    @BeforeEach
    void setUp() {
        personBuilder = new PersonBuilder.Builder()
                .setAge(12)
                .setName("Jame")
                .build();

        personDTO = new PersonDTO();
        personDTO.setAge(12);
        personDTO.setName("Jame");
    }

    @Test
    void testGetAllPeople() throws SQLException, IOException {
        List<PersonBuilder> personList = new ArrayList<>();
        personList.add(personBuilder);

        when(personDao.getPeople()).thenReturn(personList);
        List<PersonBuilder> resultList = personService.getAllPersons();

        assertEquals(1, resultList.size());
    }

    @Test
    void getPersonById() throws SQLException, IOException {
        int id = 1;
        when(personDao.getPersonById(id)).thenReturn(personBuilder);
        PersonBuilder result = personService.getPersonById(id);

        assertEquals(personBuilder, result);
    }

    @Test
    void addPerson() throws SQLException, IOException {
        personService.addPerson(personBuilder);
        verify(personDao, times(1)).addPerson(personBuilder);
    }

    @Test
    void updatePerson() throws SQLException, IOException {
        int id = 1;
        doNothing().when(personDao).updatePerson(personBuilder,id);
        personService.updatePerson(personBuilder,id);
        verify(personDao, times(1)).updatePerson(personBuilder,id);
    }

    @Test
    void deletePerson() throws SQLException, IOException {
       int id = 1;
       doNothing().when(personDao).deletePerson(id);
       personService.deletePerson(id);
       verify(personDao, times(1)).deletePerson(id);
    }

    @Test
    void getPeopleAsDTO() throws SQLException, IOException {
        List<PersonBuilder> people = new ArrayList<>();
        people.add(personBuilder);

        when(personDao.getPeople()).thenReturn(people);
        List<PersonDTO> result = personService.getPeopleAsDTO();

        assertEquals(1, result.size());
        assertEquals("Jame", result.get(0).getName());
    }

    @Test
    void getPersonAsDTO() throws SQLException, IOException {
        personDTO = personService.getPersonAsDTO(personBuilder);
        assertEquals(personDTO.getName(), personBuilder.getName());
    }

    @Test
    void convertToPersonBuilder() throws SQLException, IOException {
       personBuilder = personService.convertToPersonBuilder(personDTO);
       assertEquals(personBuilder.getName(), personDTO.getName());
    }

    @Test
    void createPersonDTO() throws SQLException, IOException {
        personService.createPersonDTO(personDTO);
        verify(personDao,times(1)).addPerson(any(PersonBuilder.class));
    }

    @Test
    void updatePersonDTO() throws SQLException, IOException {
        personDTO.setName("SAM");
        personDTO.setAge(20);

        personService.updatePersonDTO(personDTO, 1);
        verify(personDao, times(1)).updatePerson(any(PersonBuilder.class), eq(1));
    }

}
