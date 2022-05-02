package ua.goit.controller.relationsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.DevelopersSkillsConverter;
import ua.goit.model.dto.DevelopersSkillsDto;
import ua.goit.repository.DevelopersSkillsRepository;
import ua.goit.service.DevelopersSkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers-skills-created")
public class DevelopersSkillsCreatedServlet extends HttpServlet {
    private DevelopersSkillsService developersSkillsService;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersSkillsService = new DevelopersSkillsService(new DevelopersSkillsConverter(), new DevelopersSkillsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer developerId = Integer.valueOf(req.getParameter("developerId"));
        Integer skillId = Integer.valueOf(req.getParameter("skillId"));
        DevelopersSkillsDto developersSkillsDto = new DevelopersSkillsDto();
        developersSkillsDto.setDeveloperId(developerId);
        developersSkillsDto.setSkillId(skillId);
        developersSkillsService.save(developersSkillsDto);
        req.getRequestDispatcher("/WEB-INF/html/relationsJSP/developersSkillsCreated.jsp").forward(req, resp);
    }
}
