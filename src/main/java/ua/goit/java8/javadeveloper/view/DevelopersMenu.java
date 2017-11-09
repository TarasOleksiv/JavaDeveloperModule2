package ua.goit.java8.javadeveloper.view;

import ua.goit.java8.javadeveloper.dao.jdbc.JdbcDeveloperDAOImpl;
import ua.goit.java8.javadeveloper.model.Developer;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Taras on 09.11.2017.
 */
public class DevelopersMenu {
    private Scanner sc = new Scanner(System.in);

    public DevelopersMenu(){
        show();
    }

    private void show(){
        System.out.println();
        System.out.println("Меню Developers");
        System.out.println("Які дії виконуєм? (" +
                "1 - Вивести всіх девелоперів, " +
                "2 - Вивести девелопера по id, " +
                "3 - Створити девелопера, " +
                "4 - Оновити девелопера, " +
                "5 - Вилучити девелопера, " +
                "інший символ - Повернутись у Головне меню)");
        System.out.print("Введіть символ: ");
        String n = sc.nextLine().trim();
        switch (n) {
            case "1":
                getAll();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            default:
                System.out.println("Повернення у Головне меню");
                return;
        }
        show();
    }

    private void getAll(){
        JdbcDeveloperDAOImpl jdbcDeveloperDAO = new JdbcDeveloperDAOImpl();
        List<Developer> developers = jdbcDeveloperDAO.getAll();

    }
}
