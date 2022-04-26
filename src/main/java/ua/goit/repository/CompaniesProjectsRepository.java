package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CompaniesProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompaniesProjectsRepository {

    private final DatabaseManager manager;
    private static final String INSERT = "INSERT INTO companies_projects (company_id, project_id) VALUES (?, ?);";
    private static final String FIND_BY_COMPANY_ID = "SELECT * FROM companies_projects WHERE company_id = ?;";
    private static final String FIND_BY_PROJECT_ID = "SELECT * FROM companies_projects WHERE project_id = ?;";
    private static final String UPDATE = "UPDATE companies_projects SET company_id = ?, project_id = ? " +
            "WHERE company_id = ? AND project_id = ?;";
    private static final String REMOVE = "DELETE FROM companies_projects WHERE company_id = ? AND project_id = ?;";

    public CompaniesProjectsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    public void save(CompaniesProjectsDao companiesProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, companiesProjectsDao.getCompanyId());
            preparedStatement.setInt(2, companiesProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<CompaniesProjectsDao>> findByCompanyId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_COMPANY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCompaniesProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<CompaniesProjectsDao>> findByProjectId(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PROJECT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCompaniesProjectsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int update(CompaniesProjectsDao companiesProjectsDaoNew, CompaniesProjectsDao companiesProjectsDaoOld) {
        int columnsUpdated = 0;
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, companiesProjectsDaoNew.getCompanyId());
            preparedStatement.setInt(2, companiesProjectsDaoNew.getProjectId());
            preparedStatement.setInt(3, companiesProjectsDaoOld.getCompanyId());
            preparedStatement.setInt(4, companiesProjectsDaoOld.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    public void remove(CompaniesProjectsDao companiesProjectsDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setInt(1, companiesProjectsDao.getCompanyId());
            preparedStatement.setInt(2, companiesProjectsDao.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<List<CompaniesProjectsDao>> mapToCompaniesProjectsDao(ResultSet resultSet) throws SQLException {
        CompaniesProjectsDao companiesProjectsDao = null;
        List<CompaniesProjectsDao> companiesProjectsDaos = new ArrayList<>();
        while (resultSet.next()) {
            companiesProjectsDao = new CompaniesProjectsDao();
            companiesProjectsDao.setCompanyId(resultSet.getInt("company_id"));
            companiesProjectsDao.setProjectId(resultSet.getInt("project_id"));
            companiesProjectsDaos.add(companiesProjectsDao);
        }
        return Optional.ofNullable(companiesProjectsDaos);
    }
}
