package ua.goit.controller.customersServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.CustomersConverter;
import ua.goit.model.dto.CustomersDto;
import ua.goit.repository.CustomersRepository;
import ua.goit.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer-created")
public class CreateCustomerServlet extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        customersService = new CustomersService(new CustomersConverter(), new CustomersRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String customerLocation = req.getParameter("customerLocation");
        CustomersDto customersDto = new CustomersDto();
        customersDto.setName(customerName);
        customersDto.setLocation(customerLocation);
        customersService.save(customersDto);
        req.getRequestDispatcher("/WEB-INF/html/customersJSP/customerCreated.jsp").forward(req, resp);
    }
}
