package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.ProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO projects (name, description, creation_date) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM projects p WHERE p.project_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM projects;";
    private static final String UPDATE = "UPDATE projects SET name = ?, description = ?, creation_date = ? WHERE project_id = ?;";
    private static final String REMOVE_BY_ID = "DELETE FROM companies_projects WHERE project_id = ?;\n" +
            "DELETE FROM customers_projects WHERE project_id = ?;\n" +
            "DELETE FROM developers_projects WHERE project_id = ?;\n" +
            "DELETE FROM projects p WHERE p.project_id = ?;";

    public ProjectsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(ProjectsDao projectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setString(2, projectsDao.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(projectsDao.getCreationDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ProjectsDao> findById(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> findAll() {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectsDaos(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int update(ProjectsDao projectsDao) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setString(2, projectsDao.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(projectsDao.getCreationDate()));
            preparedStatement.setInt(4, projectsDao.getProjectId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void remove(ProjectsDao projectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, projectsDao.getProjectId());
            preparedStatement.setInt(2, projectsDao.getProjectId());
            preparedStatement.setInt(3, projectsDao.getProjectId());
            preparedStatement.setInt(4, projectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<ProjectsDao> mapToProjectsDao(ResultSet resultSet) throws SQLException {
        ProjectsDao projectsDao = null;
        while (resultSet.next()) {
            projectsDao = new ProjectsDao();
            projectsDao.setProjectId(resultSet.getInt("project_id"));
            projectsDao.setName(resultSet.getString("name"));
            projectsDao.setDescription(resultSet.getString("description"));
            projectsDao.setCreationDate(resultSet.getObject("creation_date", LocalDate.class));
        }
        return Optional.ofNullable(projectsDao);
    }

    private List<ProjectsDao> mapToProjectsDaos(ResultSet resultSet) throws SQLException {
        List<ProjectsDao> projects = new ArrayList<>();
        while (resultSet.next()) {
            ProjectsDao projectsDao = new ProjectsDao();
            projectsDao.setProjectId(resultSet.getInt("project_id"));
            projectsDao.setName(resultSet.getString("name"));
            projectsDao.setDescription(resultSet.getString("description"));
            projectsDao.setCreationDate(resultSet.getObject("creation_date", LocalDate.class));
            projects.add(projectsDao);
        }
        return projects;
    }
}