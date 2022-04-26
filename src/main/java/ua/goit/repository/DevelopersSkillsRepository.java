package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersSkillsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersSkillsRepository {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO developers_skills (developer_id, skill_id) VALUES (?, ?);";
    private static final String FIND_BY_DEVELOPER_ID = "SELECT * FROM developers_skills WHERE developer_id = ?;";
    private static final String FIND_BY_SKILL_ID = "SELECT * FROM developers_skills WHERE skill_id = ?;";
    private static final String UPDATE = "UPDATE developers_skills SET developer_id = ?, skill_id = ? " +
            "WHERE developer_id = ? AND skill_id = ?;";
    private static final String REMOVE = "DELETE FROM developers_skills WHERE developer_id = ? AND skill_id = ?;";

    public DevelopersSkillsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    public void save(DevelopersSkillsDao developersSkillsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, developersSkillsDao.getDeveloperId());
            preparedStatement.setInt(2, developersSkillsDao.getSkillId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<DevelopersSkillsDao>> findByDeveloperId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_DEVELOPER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersSkillsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<DevelopersSkillsDao>> findBySkillId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SKILL_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersSkillsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int update(DevelopersSkillsDao developersSkillsDaoNew, DevelopersSkillsDao developersSkillsDaoOld) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, developersSkillsDaoNew.getDeveloperId());
            preparedStatement.setInt(2, developersSkillsDaoNew.getSkillId());
            preparedStatement.setInt(3, developersSkillsDaoOld.getDeveloperId());
            preparedStatement.setInt(4, developersSkillsDaoOld.getSkillId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    public void remove(DevelopersSkillsDao developersSkillsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setInt(1, developersSkillsDao.getDeveloperId());
            preparedStatement.setInt(2, developersSkillsDao.getSkillId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<List<DevelopersSkillsDao>> mapToDevelopersSkillsDao(ResultSet resultSet) throws SQLException {
        DevelopersSkillsDao developersSkillsDao = null;
        List<DevelopersSkillsDao> developersSkillsDaos = new ArrayList<>();
        while (resultSet.next()) {
            developersSkillsDao = new DevelopersSkillsDao();
            developersSkillsDao.setDeveloperId(resultSet.getInt("developer_id"));
            developersSkillsDao.setSkillId(resultSet.getInt("skill_id"));
            developersSkillsDaos.add(developersSkillsDao);
        }
        return Optional.ofNullable(developersSkillsDaos);
    }
}
