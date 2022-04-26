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

@WebServlet("/find-developer")
public class FindDeveloperServlet extends HttpServlet {
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        DevelopersDto developersDto;
        try {
            developersDto = developersService.findById(Integer.parseInt(developerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Developer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("developer", developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/findDeveloper.jsp").forward(req, resp);
    }
}
