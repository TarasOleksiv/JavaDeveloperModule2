package ua.goit.java8.javadeveloper.view;

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
        System.out.println("Які дії виконуєм? (1 - Вивести всіх девелоперів, інший символ - Повернутись у Головне меню)");
        System.out.print("Введіть символ: ");
        String n = sc.nextLine().trim();
        switch (n) {
            case "1":
                //chooseCarTask();
                break;
            default:
                System.out.println("Повернення у Головне меню");
                return;
        }
        show();
    }
}
