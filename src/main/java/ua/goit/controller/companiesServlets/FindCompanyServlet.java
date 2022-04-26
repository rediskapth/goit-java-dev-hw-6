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

@WebServlet("/find-company")
public class FindCompanyServlet extends HttpServlet {
    private CompaniesService companiesService;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        companiesService = new CompaniesService(new CompaniesConverter(), new CompaniesRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("companyId");
        CompaniesDto companiesDto;
        try {
            companiesDto = companiesService.findById(Integer.parseInt(companyId));
        } catch (Exception e) {
            req.setAttribute("exception", "Company with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("company", companiesDto);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/findCompany.jsp").forward(req, resp);
    }
}
