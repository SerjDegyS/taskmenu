import entity.Dish;
import sun.security.x509.CertAttrSet;

import java.util.Scanner;

import static entity.Dish.readDishFromConsole;

public class Main {
    public static void main(String[] args) {

//        System.out.println(readFromConsole().toString());

        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        try {
            while (true) {
                String select = selectOptionFromConsole();
                switch (select) {
                    case "1":
                        menu.addDish(readDishFromConsole());
                        System.out.println("Dish add!");
                        break;
                    case "2":
                        menu.getAllDish().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Enter price range : \n(lowest, highest)");
                        String[] lowAndHightPrice = scan.nextLine().trim().split(",", 2);
                        menu.priceFromTo(Double.parseDouble(lowAndHightPrice[0]),
                                Double.parseDouble(lowAndHightPrice[1]))
                                .forEach(System.out::println);
                        break;
                    case "4":
                        menu.hasDiscount().forEach(System.out::println);
                        break;
                    case "5":
                        menu.totalWeightUpToKg().forEach(System.out::println);
                        break;
                    default:
                        return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            menu.closeAllManager();
        }


    }

    public static String selectOptionFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select option:");
        System.out.println("1 - add dish");
        System.out.println("2 - select all dishes");
        System.out.println("3 - select dishes within price range");
        System.out.println("4 - select dishes with discount");
        System.out.println("5 - select dishes with total weight < 1 kg");
        return scanner.nextLine();
    }
}
