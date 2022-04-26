package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CompaniesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CompaniesRepository implements Repository<CompaniesDao> {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO companies (name, location) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM companies c WHERE c.company_id = ?;";
    private static final String UPDATE = "UPDATE companies SET name = ?, location = ? WHERE company_id = ?;";
    private static final String REMOVE_BY_ID = "DELETE FROM companies c WHERE c.company_id = ?;";

    public CompaniesRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(CompaniesDao companiesDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, companiesDao.getName());
            preparedStatement.setString(2, companiesDao.getLocation());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CompaniesDao> findById(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCompaniesDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int update(CompaniesDao companiesDao) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, companiesDao.getName());
            preparedStatement.setString(2, companiesDao.getLocation());
            preparedStatement.setInt(3, companiesDao.getCompanyId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    @Override
    public void remove(CompaniesDao companiesDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setInt(1, companiesDao.getCompanyId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<CompaniesDao> mapToCompaniesDao(ResultSet resultSet) throws SQLException {
        CompaniesDao companiesDao = null;
        while (resultSet.next()) {
            companiesDao = new CompaniesDao();
            companiesDao.setCompanyId(resultSet.getInt("company_id"));
            companiesDao.setName(resultSet.getString("name"));
            companiesDao.setLocation(resultSet.getString("location"));
        }
        return Optional.ofNullable(companiesDao);
    }
}
