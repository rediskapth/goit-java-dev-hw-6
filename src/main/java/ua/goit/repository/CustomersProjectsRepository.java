package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CustomersProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomersProjectsRepository {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO customers_projects (customer_id, project_id) VALUES (?, ?);";
    private static final String FIND_BY_CUSTOMER_ID = "SELECT * FROM customers_projects WHERE customer_id = ?;";
    private static final String FIND_BY_PROJECT_ID = "SELECT * FROM customers_projects WHERE project_id = ?;";
    private static final String UPDATE = "UPDATE customers_projects SET customer_id = ?, project_id = ? " +
            "WHERE customer_id = ? AND project_id = ?;";
    private static final String REMOVE = "DELETE FROM customers_projects WHERE customer_id = ? AND project_id = ?;";

    public CustomersProjectsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    public void save(CustomersProjectsDao customersProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, customersProjectsDao.getCustomerId());
            preparedStatement.setInt(2, customersProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<CustomersProjectsDao>> findByCustomerId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CUSTOMER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomersProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<CustomersProjectsDao>> findByProjectId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PROJECT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomersProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int update(CustomersProjectsDao customersProjectsDaoNew, CustomersProjectsDao customersProjectsDaoOld) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, customersProjectsDaoNew.getCustomerId());
            preparedStatement.setInt(2, customersProjectsDaoNew.getProjectId());
            preparedStatement.setInt(3, customersProjectsDaoOld.getCustomerId());
            preparedStatement.setInt(4, customersProjectsDaoOld.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    public void remove(CustomersProjectsDao customersProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setInt(1, customersProjectsDao.getCustomerId());
            preparedStatement.setInt(2, customersProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<List<CustomersProjectsDao>> mapToCustomersProjectsDao(ResultSet resultSet) throws SQLException {
        CustomersProjectsDao customersProjectsDao = null;
        List<CustomersProjectsDao> customersProjectsDaos = new ArrayList<>();
        while (resultSet.next()) {
            customersProjectsDao = new CustomersProjectsDao();
            customersProjectsDao.setCustomerId(resultSet.getInt("customer_id"));
            customersProjectsDao.setProjectId(resultSet.getInt("project_id"));
            customersProjectsDaos.add(customersProjectsDao);
        }
        return Optional.ofNullable(customersProjectsDaos);
    }
}
