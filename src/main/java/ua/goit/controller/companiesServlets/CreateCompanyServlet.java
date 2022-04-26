package ua.goit.controller.companiesServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.CompaniesConverter;
import ua.goit.model.dto.CompaniesDto;
import ua.goit.repository.CompaniesRepository;
import ua.goit.service.CompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company-created")
public class CreateCompanyServlet extends HttpServlet {
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        companiesService = new CompaniesService(new CompaniesConverter(), new CompaniesRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        String companyLocation = req.getParameter("companyLocation");
        CompaniesDto companiesDto = new CompaniesDto();
        companiesDto.setName(companyName);
        companiesDto.setLocation(companyLocation);
        companiesService.save(companiesDto);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/companyCreated.jsp").forward(req, resp);
    }
}
