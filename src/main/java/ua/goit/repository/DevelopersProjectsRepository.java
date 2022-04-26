package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersProjectsRepository {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO developers_projects (developer_id, project_id) VALUES (?, ?);";
    private static final String FIND_BY_DEVELOPER_ID = "SELECT * FROM developers_projects WHERE developer_id = ?;";
    private static final String FIND_BY_PROJECT_ID = "SELECT * FROM developers_projects WHERE project_id = ?;";
    private static final String UPDATE = "UPDATE developers_projects SET developer_id = ?, project_id = ? " +
            "WHERE developer_id = ? AND project_id = ?;";
    private static final String REMOVE = "DELETE FROM developers_projects WHERE developer_id = ? AND project_id = ?;";

    public DevelopersProjectsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    public void save(DevelopersProjectsDao developersProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, developersProjectsDao.getDeveloperId());
            preparedStatement.setInt(2, developersProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<DevelopersProjectsDao>> findByDeveloperId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_DEVELOPER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<DevelopersProjectsDao>> findByProjectId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PROJECT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int update(DevelopersProjectsDao developersProjectsDaoNew, DevelopersProjectsDao developersProjectsDaoOld) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, developersProjectsDaoNew.getDeveloperId());
            preparedStatement.setInt(2, developersProjectsDaoNew.getProjectId());
            preparedStatement.setInt(3, developersProjectsDaoOld.getDeveloperId());
            preparedStatement.setInt(4, developersProjectsDaoOld.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    public void remove(DevelopersProjectsDao developersProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setInt(1, developersProjectsDao.getDeveloperId());
            preparedStatement.setInt(2, developersProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<List<DevelopersProjectsDao>> mapToDevelopersProjectsDao(ResultSet resultSet) throws SQLException {
        DevelopersProjectsDao developersProjectsDao = null;
        List<DevelopersProjectsDao> developersProjectsDaos = new ArrayList<>();
        while (resultSet.next()) {
            developersProjectsDao = new DevelopersProjectsDao();
            developersProjectsDao.setDeveloperId(resultSet.getInt("developer_id"));
            developersProjectsDao.setProjectId(resultSet.getInt("project_id"));
            developersProjectsDaos.add(developersProjectsDao);
        }
        return Optional.ofNullable(developersProjectsDaos);
    }
}
