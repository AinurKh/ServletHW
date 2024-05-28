package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PersonDao;
import dto.GasStationDTO;
import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.logging.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/people/*")
public class PersonServlet extends HttpServlet {
    private PersonService personService;




    public void init() {

        try {
           PersonDao personDao = new PersonDao();
           personService = new PersonService(personDao);
        } catch (SQLException |IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<PersonDTO> personDTOList = null;
                try {
                    personDTOList = personService.getPeopleAsDTO();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                req.setAttribute("peopleDTOList", personDTOList);
            req.getRequestDispatcher("/WEB-INF/view/Person.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(req.getParameter("name"));
        personDTO.setAge(Integer.parseInt(req.getParameter("age")));
        try {
            personService.createPersonDTO(personDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/people");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String pathInfo = req.getPathInfo();
       String[] pathParts = pathInfo.split("/");

        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        // Преобразуем JSON в объект Java с помощью библиотеки Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDTO person = objectMapper.readValue(json, PersonDTO.class);
        try {
            personService.updatePersonDTO(person, person.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {

            String[] pathParts = pathInfo.split("/");
            int personId = Integer.parseInt(pathParts[1]);

            try {
                personService.deletePerson(personId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
