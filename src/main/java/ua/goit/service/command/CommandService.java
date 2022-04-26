package ua.goit.service.command;

import ua.goit.config.DatabaseManager;
import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandService {
    private final DatabaseManager manager;
    private final DevelopersConverter converter;
    private static final String FIND_SUM_SALARY_BY_PROJECT_ID = "SELECT SUM(d.salary) AS sum FROM developers d\n" +
            "INNER JOIN developers_projects dp\n" +
            "ON d.developer_id = dp.developer_id\n" +
            "WHERE dp.project_id = ?;";
    private static final String FIND_ALL_DEVELOPERS_AT_PROJECT_BY_ID = "SELECT d.* FROM developers d\n" +
            "INNER JOIN developers_projects dp\n" +
            "ON d.developer_id = dp.developer_id\n" +
            "WHERE dp.project_id = ?;";
    private static final String FIND_ALL_JAVA_DEVELOPERS = "SELECT d.* FROM developers d\n" +
            "INNER JOIN developers_skills ds\n" +
            "ON ds.developer_id = d.developer_id\n" +
            "INNER JOIN skills s\n" +
            "ON ds.skill_id = s.skill_id\n" +
            "WHERE s.language = 'Java';";
    private static final String FIND_ALL_MIDDLE_DEVELOPERS = "SELECT d.* FROM developers d\n" +
            "INNER JOIN developers_skills ds\n" +
            "ON ds.developer_id = d.developer_id\n" +
            "INNER JOIN skills s\n" +
            "ON ds.skill_id = s.skill_id\n" +
            "WHERE s.skill = 'Middle';";
    private static final String FIND_PROJECTS_WITH_CREATION_DATE_AND_NUMBER_OF_DEVELOPERS =
            "SELECT p.creation_date, p.name, COUNT(*) FROM developers d\n" +
                    "INNER JOIN developers_projects dp\n" +
                    "ON d.developer_id = dp.developer_id\n" +
                    "INNER JOIN projects p\n" +
                    "ON p.project_id = dp.project_id\n" +
                    "GROUP BY p.name, p.creation_date;";

    public CommandService(DatabaseManager manager, DevelopersConverter converter) {
        this.manager = manager;
        this.converter = converter;
    }

    public List<DevelopersDto> findAllDev(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DEVELOPERS_AT_PROJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public Integer findSumSalary(int id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUM_SALARY_BY_PROJECT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("sum");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DevelopersDto> findJavaDevelopers() {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_JAVA_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<DevelopersDto> findMiddleDevelopers() {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_MIDDLE_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDevDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<StringBuilder> findProjectsDateWithNumberOfDevelopers() {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PROJECTS_WITH_CREATION_DATE_AND_NUMBER_OF_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StringBuilder> resultsList = new ArrayList<>();
            StringBuilder stringBuilder = null;
            while (resultSet.next()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(resultSet.getDate("creation_date"))
                        .append(" - ")
                        .append(resultSet.getString("name"))
                        .append(" - ")
                        .append(resultSet.getInt("count"));
                resultsList.add(stringBuilder);
            }
            return resultsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<DevelopersDto> mapToDevDto(ResultSet resultSet) throws SQLException {
        DevelopersDao developersDao = null;
        List<DevelopersDto> developersDtos = new ArrayList<>();
        while (resultSet.next()) {
            developersDao = new DevelopersDao();
            developersDao.setDeveloperId(resultSet.getInt("developer_id"));
            developersDao.setName(resultSet.getString("name"));
            developersDao.setAge(resultSet.getInt("age"));
            developersDao.setSalary(resultSet.getInt("salary"));
            developersDtos.add(converter.convert(developersDao));
        }
        return developersDtos;
    }
}
