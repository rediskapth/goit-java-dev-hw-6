package ua.goit.controller.skillsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.SkillsRepository;
import ua.goit.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skill-created")
public class CreateSkillServlet extends HttpServlet {
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        skillsService = new SkillsService(new SkillsConverter(), new SkillsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillLanguage = req.getParameter("skillLanguage");
        String skillSkill = req.getParameter("skillSkill");
        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setLanguage(skillLanguage);
        skillsDto.setSkill(skillSkill);
        skillsService.save(skillsDto);
        req.getRequestDispatcher("/WEB-INF/html/skillsJSP/skillCreated.jsp").forward(req, resp);
    }
}
