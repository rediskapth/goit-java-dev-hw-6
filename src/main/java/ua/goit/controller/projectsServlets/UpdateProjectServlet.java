package ua.goit.controller.projectsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.ProjectsConverter;
import ua.goit.model.dto.ProjectsDto;
import ua.goit.repository.ProjectsRepository;
import ua.goit.service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/project-updated")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        String projectCreationDate = req.getParameter("projectCreationDate");
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setProjectId(Integer.parseInt(projectId));
        try {
            projectsService.findById(Integer.parseInt(projectId));
        } catch (Exception e) {
            req.setAttribute("exception", "Project with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        projectsDto.setName(projectName);
        projectsDto.setDescription(projectDescription);
        projectsDto.setCreationDate(LocalDate.parse(projectCreationDate));
        projectsService.update(projectsDto);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectUpdated.jsp").forward(req, resp);
    }
}
