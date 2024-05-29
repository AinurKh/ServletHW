package servlets;

import dto.CarDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CarService;
import servlet.CarServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServletTest {
    @Mock
    private CarService carService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private CarServlet carServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet() throws Exception {
        List<CarDTO> carDTOList = new ArrayList<>();
        CarDTO carDTO = new CarDTO();

        carDTO.setId(1);
        carDTO.setPersonId(2);
        carDTO.setModel("AUDI");
        carDTO.setHorsePower(220);
        carDTOList.add(carDTO);

        when(carService.getCarsAsDTO()).thenReturn(carDTOList);
        when(request.getRequestDispatcher("/WEB-INF/view/Car.jsp")).thenReturn(requestDispatcher);

        carServlet.doGet(request,response);
        verify(request).setAttribute("carDTOS",carDTOList);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void testDoPost() throws Exception {
        when(request.getParameter("model")).thenReturn("AUDI");
        when(request.getParameter("personId")).thenReturn(String.valueOf(2));
        when(request.getParameter("horsePower")).thenReturn("220");

        carServlet.doPost(request,response);

        verify(carService).createCarFromDTO(any(CarDTO.class));
        verify(response).sendRedirect(request.getContextPath() + "/cars");
    }

    @Test
    void testDoPut() throws Exception{
        String json ="{\"personId\":\"2\",\"model\":\"AUDI\",\"horsePower\":220}";
        StringReader reader = new StringReader(json);
        BufferedReader bufferedReader = new BufferedReader(reader);

        when(request.getReader()).thenReturn(bufferedReader);
        when(request.getPathInfo()).thenReturn("/0");

        carServlet.doPut(request,response);

        verify(carService).updateCarDTO(any(CarDTO.class),eq(0));
    }

    @Test
    void testDoDelete() throws Exception{
        when(request.getPathInfo()).thenReturn("/0");
        carServlet.doDelete(request,response);
        verify(carService).deleteCar(0);
    }
}
