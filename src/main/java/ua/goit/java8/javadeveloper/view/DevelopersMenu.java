package ua.goit.java8.javadeveloper.view;

import ua.goit.java8.javadeveloper.dao.jdbc.JdbcDeveloperDAOImpl;
import ua.goit.java8.javadeveloper.model.Developer;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Taras on 10.11.2017.
 */

class DevelopersMenu extends AbstractMenu {

    private static JdbcDeveloperDAOImpl jdbcDeveloperDAO = new JdbcDeveloperDAOImpl();

    @Override
    void menu() {
        System.out.println("Меню Developers");
        System.out.println("Які дії виконуєм? (" +
                "1 - Вивести всіх девелоперів, " +
                "2 - Вивести девелопера по id, " +
                "3 - Створити девелопера, " +
                "4 - Оновити девелопера, " +
                "5 - Вилучити девелопера, " +
                "інший символ - Повернутись у Головне меню)");
    }

    @Override
    void getAll(){
        List<Developer> developers = jdbcDeveloperDAO.getAll();

        System.out.println("********** Developers ************");
        if (developers != null){
            for (Developer developer: developers){
                System.out.println(developer);
            }
        } else {
            System.out.println("Девелопери відсутні.");
        }
        System.out.println("**********************************");
    }

    @Override
    void getById() {
        System.out.print("Введіть id девелопера: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Developer developer = jdbcDeveloperDAO.getById(id);

        System.out.println("********** Developer ************");
        if (developer != null){
            System.out.println(developer);
        } else {
            System.out.println("Девелопер з id = " + id + " відсутній.");
        }
        System.out.println("**********************************");
    }

    @Override
    void create() {
        System.out.println("Введіть через пробіл наступні значення: ");
        System.out.println("Firstname Lastname Company_Id Salary");
        String delims = "[ ]";
        String[] line;
        line = sc.nextLine().split(delims);
        String firstName = line[0];
        String lastName = line[1];
        Long company_id = Long.parseLong(line[2]);
        BigDecimal salary = new BigDecimal(line[3]);
        Developer developer = new Developer();
        developer.withFirstName(firstName)
                .withLastName(lastName)
                .withCompany_id(company_id)
                .withSalary(salary);

        jdbcDeveloperDAO.create(developer);
    }

    @Override
    void update() {
        System.out.print("Введіть id девелопера: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Developer developer = jdbcDeveloperDAO.getById(id);  //перевірка чи девелопер з таким id існує

        if (developer != null){
            System.out.println("Введіть через пробіл наступні значення: ");
            System.out.println("Firstname Lastname Company_Id Salary");
            String delims = "[ ]";
            String[] line;
            line = sc.nextLine().split(delims);
            String firstName = line[0];
            String lastName = line[1];
            Long company_id = Long.parseLong(line[2]);
            BigDecimal salary = new BigDecimal(line[3]);
            developer.withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withCompany_id(company_id)
                    .withSalary(salary);
            jdbcDeveloperDAO.update(developer);
        } else {
            System.out.println("Девелопер з id = " + id + " відсутній.");
        }
    }

    @Override
    void delete() {
        System.out.print("Введіть id девелопера: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Developer developer = jdbcDeveloperDAO.getById(id);  //перевірка чи девелопер з таким id існує

        if (developer != null){
            jdbcDeveloperDAO.delete(developer);
        } else {
            System.out.println("Девелопер з id = " + id + " відсутній.");
        }
    }
}
