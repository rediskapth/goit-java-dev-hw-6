package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.SkillsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SkillsRepository implements Repository<SkillsDao> {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO skills (language, skill) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM skills s WHERE s.skill_id = ?;";
    private static final String UPDATE = "UPDATE skills SET language = ?, skill = ? WHERE skill_id = ?;";
    private static final String REMOVE_BY_ID = "DELETE FROM skills s WHERE s.skill_id = ?;";

    public SkillsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(SkillsDao skillsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, skillsDao.getLanguage());
            preparedStatement.setString(2, skillsDao.getSkill());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SkillsDao> findById(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSkillsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int update(SkillsDao skillsDao) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, skillsDao.getLanguage());
            preparedStatement.setString(2, skillsDao.getSkill());
            preparedStatement.setInt(3, skillsDao.getSkillId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void remove(SkillsDao skillsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, skillsDao.getSkillId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<SkillsDao> mapToSkillsDao(ResultSet resultSet) throws SQLException {
        SkillsDao skillsDao = null;
        while (resultSet.next()) {
            skillsDao = new SkillsDao();
            skillsDao.setSkillId(resultSet.getInt("skill_id"));
            skillsDao.setLanguage(resultSet.getString("language"));
            skillsDao.setSkill(resultSet.getString("skill"));
        }
        return Optional.ofNullable(skillsDao);
    }
}
