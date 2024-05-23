package servlet;

import dao.PersonDao;
import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/people")
public class PersonServlet extends HttpServlet {
    private PersonService personService;

    public void init() {
        PersonDao personDao = null;
        try {
            personDao = new PersonDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        personService = new PersonService(personDao);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PersonDTO> list = personService.ToPersonDTOList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
