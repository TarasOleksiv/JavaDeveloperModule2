package ua.goit.java8.javadeveloper.view;

import ua.goit.java8.javadeveloper.dao.jdbc.JdbcProjectDAOImpl;
import ua.goit.java8.javadeveloper.model.Project;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Taras on 11.11.2017.
 */
public class ProjectsMenu extends AbstractMenu {

    private static JdbcProjectDAOImpl jdbcProjectDAO = new JdbcProjectDAOImpl();

    @Override
    void menu() {
        System.out.println("Меню Projects");
        System.out.println("Які дії виконуєм? (" +
                "1 - Вивести всі проекти, " +
                "2 - Вивести проект по id, " +
                "3 - Створити проект, " +
                "4 - Оновити проект, " +
                "5 - Вилучити проект, " +
                "інший символ - Повернутись у Головне меню)");
    }

    @Override
    void getAll() {
        List<Project> projects = jdbcProjectDAO.getAll();

        System.out.println("********** Projects ************");
        if (projects != null){
            for (Project project: projects){
                System.out.println(project);
            }
        } else {
            System.out.println("Проекти відсутні.");
        }
        System.out.println("**********************************");
    }

    @Override
    void getById() {
        System.out.print("Введіть id проекта: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Project project = jdbcProjectDAO.getById(id);

        System.out.println("********** Project ************");
        if (project != null){
            System.out.println(project);
        } else {
            System.out.println("Проект з id = " + id + " відсутній.");
        }
        System.out.println("**********************************");
    }

    @Override
    void create() {
        System.out.println("Введіть через пробіл наступні значення: ");
        System.out.println("Name Customer_Id Company_Id Costs");
        String delims = "[ ]";
        String[] line;
        line = sc.nextLine().split(delims);
        String name = line[0];
        Long customer_id = Long.parseLong(line[1]);
        Long company_id = Long.parseLong(line[2]);
        BigDecimal costs = new BigDecimal(line[3]);
        Project project = new Project();
        project.withName(name)
                .withCustomer_id(customer_id)
                .withCompany_id(company_id)
                .withCosts(costs);

        jdbcProjectDAO.create(project);
    }

    @Override
    void update() {
        System.out.print("Введіть id проекта: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Project project = jdbcProjectDAO.getById(id);  //перевірка чи проект з таким id існує

        if (project != null){
            System.out.println("Введіть через пробіл наступні значення: ");
            System.out.println("Name Customer_Id Company_Id Costs");
            String delims = "[ ]";
            String[] line;
            line = sc.nextLine().split(delims);
            String name = line[0];
            Long customer_id = Long.parseLong(line[1]);
            Long company_id = Long.parseLong(line[2]);
            BigDecimal costs = new BigDecimal(line[3]);
            project.withId(id)
                    .withName(name)
                    .withCustomer_id(customer_id)
                    .withCompany_id(company_id)
                    .withCosts(costs);
            jdbcProjectDAO.update(project);
        } else {
            System.out.println("Проект з id = " + id + " відсутній.");
        }
    }

    @Override
    void delete() {
        System.out.print("Введіть id проекта: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Project project = jdbcProjectDAO.getById(id);  //перевірка чи проект з таким id існує

        if (project != null){
            jdbcProjectDAO.delete(project);
        } else {
            System.out.println("Проект з id = " + id + " відсутній.");
        }
    }
}
