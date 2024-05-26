package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CarDao;
import dao.PersonDao;
import dto.CarDTO;
import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cars/*")
public class CarServlet extends HttpServlet {
    private CarService carService;

    public void init() {

        try {
            CarDao carDao = new CarDao();
            carService = new CarService(carDao);


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarDTO> carDTOS = null;
        try {
            carDTOS = carService.getCarsAsDTO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("carDTOS", carDTOS);
        req.getRequestDispatcher("/WEB-INF/view/Car.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDTO carDTO = new CarDTO();

        carDTO.setPersonId(Integer.parseInt(req.getParameter("personId")));
        carDTO.setModel(req.getParameter("model"));
        carDTO.setHorsePower(Integer.parseInt(req.getParameter("horsePower")));

        try {
            carService.createCarFromDTO(carDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/cars");
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
        CarDTO carDTO = objectMapper.readValue(json, CarDTO.class);
        System.out.println(carDTO.getPersonId());
        System.out.println(carDTO.getModel());
        System.out.println(carDTO.getHorsePower());
        System.out.println(carDTO.getId());

        try {
            carService.updateCarDTO(carDTO,carDTO.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {

            String[] pathParts = pathInfo.split("/");
            int carId = Integer.parseInt(pathParts[1]);

            try {
                carService.deleteCar(carId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
