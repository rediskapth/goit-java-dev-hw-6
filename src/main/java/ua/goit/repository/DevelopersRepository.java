package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO developers (name, age, salary) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM developers d WHERE d.developer_id = ?;";
    private static final String UPDATE = "UPDATE developers SET name = ?, age = ?, salary = ? WHERE developer_id = ?;";
    private static final String REMOVE_BY_ID = "DELETE FROM developers d WHERE d.developer_id = ?;";

    public DevelopersRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(DevelopersDao developersDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, developersDao.getName());
            preparedStatement.setInt(2, developersDao.getAge());
            preparedStatement.setInt(3, developersDao.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevelopersDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int update(DevelopersDao developersDao) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, developersDao.getName());
            preparedStatement.setInt(2, developersDao.getAge());
            preparedStatement.setInt(3, developersDao.getSalary());
            preparedStatement.setInt(4, developersDao.getDeveloperId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void remove(DevelopersDao developersDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, developersDao.getDeveloperId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<DevelopersDao> mapToDevelopersDao(ResultSet resultSet) throws SQLException {
        DevelopersDao developersDao = null;
        while (resultSet.next()) {
            developersDao = new DevelopersDao();
            developersDao.setDeveloperId(resultSet.getInt("developer_id"));
            developersDao.setName(resultSet.getString("name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setSalary(resultSet.getInt("salary"));

        }
        return Optional.ofNullable(developersDao);
    }
}
