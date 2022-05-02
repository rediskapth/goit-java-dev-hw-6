package ua.goit.controller.relationsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.SkillsRepository;
import ua.goit.service.DevelopersService;
import ua.goit.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/developers-skills-form")
public class DevelopersSkillsFormServlet extends HttpServlet {
    private SkillsService skillsService;
    private DevelopersService developersService;

    @Override
    public void init(){
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        skillsService = new SkillsService(new SkillsConverter(), new SkillsRepository(dbConnector));
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SkillsDto> skills = skillsService.findAll();
        List<DevelopersDto> developers = developersService.findAll();
        req.setAttribute("skills", skills);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/html/relationsJSP/developersSkillsForm.jsp").forward(req, resp);
    }
}
