package control;

import field.*;
import character.*;

import java.util.Scanner;

public class Skeleton {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("\nKerlek valaszd ki a teszt esetet:\n" +
                    "----------------------------------\n" +
                    "1  - Character digs\n" +
                    "2  - Character steps of Floe\n" +
                    "3  - Character steps on UnstableFloe\n" +
                    "4  - Character steps on Hole\n" +
                    "5  - Snowstorm summoned on Game Field\n" +
                    "6  - Snowstorm hits Floe\n" +
                    "7  - Character falls in water\n" +
                    "8  - Character uses Flare Gun part\n" +
                    "9  - Character uses Food\n" +
                    "10 - Character uses Rope\n" +
                    "11 - Character uses Diving Suit\n" +
                    "12 - Character uses Shovel\n" +
                    "13 - Character picks up item\n" +
                    "14 - Eskimo builds Igloo\n" +
                    "15 - Researcher researches neighbour\n" +
                    "----------------------------------\n" +
                    "0  - Kilep\n");
            try {
                int result = Integer.parseInt(scanner.nextLine());
                if (result == 0) {
                    exit = true;
                } else if (0 < result && result <= 15) {
                    runTest(result);
                    System.out.println("\nNyomj barmely gombot a folytatashoz\n");
                    scanner.nextLine();
                } else {
                    System.out.println("\nHibas input! Az inputnak 0 es 15 koze kell esnie!\n");
                    System.out.println("\nNyomj barmely gombot a folytatashoz\n");
                    scanner.nextLine();
                }
            } catch(java.lang.NumberFormatException e) {
                System.out.println("\nHibas input! Az input csak egesz szam lehet!\n");
                System.out.println("\nNyomj barmely gombot a folytatashoz\n");
                scanner.nextLine();
            }
        }
    }

    private static void runTest(int n) {
        switch(n) {
            case 1:
                Floe f1 = new Floe();
                Eskimo c = new Eskimo();
                c.setField(f1);
                c.Dig();
                break;
            case 2:
                /*Teszteset 2 ide*/
                break;
            default:
                break;
        }
    }

    public static void methodCalled(String classname, String methodname) {
        System.out.println(classname +"."+ methodname);
    }

    public static boolean askQuestion(String s) {
        System.out.println(s+" (I/N)");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            char answer = scanner.nextLine().charAt(0);
            switch(answer) {
                case 'I':
                case 'i':
                    return true;
                case 'N':
                case 'n':
                    return false;
                default:
                    System.out.println("Hibas valasz! A valasz csak I/N lehet!\n"+s+" (I/N)");
                    break;
            }
        }
    }
}
