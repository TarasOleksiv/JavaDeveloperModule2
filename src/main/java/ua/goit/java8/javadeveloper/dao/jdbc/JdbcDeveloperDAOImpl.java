package ua.goit.java8.javadeveloper.dao.jdbc;

import ua.goit.java8.javadeveloper.dao.DeveloperDAO;
import ua.goit.java8.javadeveloper.dao.utils.ConnectionUtil;
import ua.goit.java8.javadeveloper.model.Developer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by t.oleksiv on 09/11/2017.
 */
public class JdbcDeveloperDAOImpl extends JdbcAbstractDAO implements DeveloperDAO {

    @Override
    public Developer getById(Long aLong){

        String sql = "SELECT * FROM developers WHERE id = ?";
        Developer developer = null;
        try {
            connection = ConnectionUtil.getConnectionDB();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,aLong);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Long developerId = resultSet.getLong("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                Long company_id = resultSet.getLong("company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");

                developer = new Developer();
                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withCompany_id(company_id)
                        .withSalary(salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(false);
        }

        return developer;
    }

    @Override
    public List<Developer> getAll() {

        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers ORDER BY id";

        try {
            connection = ConnectionUtil.getConnectionDB();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long developerId = resultSet.getLong("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                Long company_id = resultSet.getLong("company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");

                Developer developer = new Developer();
                developer.withId(developerId)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withCompany_id(company_id)
                        .withSalary(salary);

                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(false);
        }

        return developers;

    }

    @Override
    public void create(Developer developer) {

        String sql = "INSERT INTO developers (firstname, lastname, company_id, salary) VALUES (?, ?, ?, ?)";
        String sqlCompanyCheck = "SELECT * FROM companies WHERE id = ?"; // перевірка чи компанія з таким id існує

        try {
            connection = ConnectionUtil.getConnectionDB();
            preparedStatement = connection.prepareStatement(sqlCompanyCheck);
            preparedStatement.setLong(1,developer.getCompany_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                try {
                    resultSet.close();
                    preparedStatement.close();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,developer.getFirstName());
                preparedStatement.setString(2,developer.getLastName());
                preparedStatement.setLong(3,developer.getCompany_id());
                preparedStatement.setBigDecimal(4,developer.getSalary());
                resultSetUpdate = preparedStatement.executeUpdate();
                if (resultSetUpdate > 0){
                    System.out.println("Девелопера створено успішно.");
                } else {
                    System.out.println("Неможливо створити девелопера.");
                }

            } else {
                System.out.println("Компанії з таким id не існує.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(true);
        }

    }

    @Override
    public void update(Developer developer) {
        String sql = "UPDATE developers SET firstname = ?, lastname = ?, company_id = ?, salary = ? WHERE id = ?";
        try {
            connection = ConnectionUtil.getConnectionDB();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,developer.getFirstName());
            preparedStatement.setString(2,developer.getLastName());
            preparedStatement.setLong(3,developer.getCompany_id());
            preparedStatement.setBigDecimal(4,developer.getSalary());
            preparedStatement.setLong(5,developer.getId());
            resultSetUpdate = preparedStatement.executeUpdate();
            System.out.println("**********************************");
            if (resultSetUpdate > 0){
                System.out.println("Девелопера змінено успішно.");
            } else {
                System.out.println("Неможливо змінити девелопера.");
            }
            System.out.println("**********************************");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(true);
        }
    }

    // каскадне видалення девелопера зі всіх пов'язаних таблиць
    @Override
    public void delete(Developer developer) {
        String sqlDevelopers = "DELETE FROM developers WHERE id = ?";
        String sqlSkills = "DELETE FROM developer_skills WHERE developer_id = ?";
        String sqlProjects = "DELETE FROM developer_projects WHERE developer_id = ?";

        try {
            connection = ConnectionUtil.getConnectionDB();

            // транзакція - каскадне видалення девелопера зі всіх таблиць
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sqlProjects);
            preparedStatement.setLong(1,developer.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(sqlSkills);
            preparedStatement.setLong(1,developer.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(sqlDevelopers);
            preparedStatement.setLong(1,developer.getId());
            resultSetUpdate = preparedStatement.executeUpdate();
            connection.commit();
            // кінець транзакції

            connection.setAutoCommit(true);
            System.out.println("**********************************");
            if (resultSetUpdate > 0){
                System.out.println("Девелопера видалено успішно.");
            } else {
                System.out.println("Неможливо видалити девелопера.");
            }
            System.out.println("**********************************");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(true);
        }
    }

}
