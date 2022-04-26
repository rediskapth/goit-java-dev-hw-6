package ua.goit.controller.developersServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.repository.DevelopersRepository;
import ua.goit.service.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer-updated")
public class UpdateDeveloperServlet extends HttpServlet {
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        String developerName = req.getParameter("developerName");
        int developerAge = Integer.parseInt(req.getParameter("developerAge"));
        int developerSalary = Integer.parseInt(req.getParameter("developerSalary"));
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setDeveloperId(Integer.parseInt(developerId));
        try {
            developersService.findById(Integer.parseInt(developerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Developer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developersDto.setName(developerName);
        developersDto.setAge(developerAge);
        developersDto.setSalary(developerSalary);
        developersService.update(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerUpdated.jsp").forward(req, resp);
    }
}
