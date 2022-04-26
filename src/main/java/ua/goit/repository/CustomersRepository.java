package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CustomersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao> {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO customers (name, location) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM customers c WHERE c.customer_id = ?;";
    private static final String UPDATE = "UPDATE customers SET name = ?, location = ? WHERE customer_id = ?;";
    private static final String REMOVE_BY_ID = "DELETE FROM customers c WHERE c.customer_id = ?;";

    public CustomersRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(CustomersDao customersDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, customersDao.getName());
            preparedStatement.setString(2, customersDao.getLocation());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomersDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int update(CustomersDao customersDao) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, customersDao.getName());
            preparedStatement.setString(2, customersDao.getLocation());
            preparedStatement.setInt(3, customersDao.getCustomerId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void remove(CustomersDao customersDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, customersDao.getCustomerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<CustomersDao> mapToCustomersDao(ResultSet resultSet) throws SQLException {
        CustomersDao customersDao = null;
        while (resultSet.next()) {
            customersDao = new CustomersDao();
            customersDao.setCustomerId(resultSet.getInt("customer_id"));
            customersDao.setName(resultSet.getString("name"));
            customersDao.setLocation(resultSet.getString("location"));
        }
        return Optional.ofNullable(customersDao);
    }
}
