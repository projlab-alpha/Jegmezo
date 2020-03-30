package control;

import field.*;
import character.*;
import item.*;

import java.util.Scanner;
/**
 * A tesztesetek lefutásához szükséges metódusokat tartalmazó osztály
 * A jenlegi iterációban ez a program belépési pontja is
 */
public class Skeleton {
    private static int indentLevel = 0;

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("Kerlek valaszd ki a teszt esetet:\n" +
                    "----------------------------------\n" +
                    "1  - Character digs\n" +
                    "2  - Character steps on Floe\n" +
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
                    "0  - Kilep");
            try {
                int result = Integer.parseInt(scanner.nextLine());
                if (result == 0) {
                    exit = true;
                    System.out.println("Goodbye");
                } else if (0 < result && result <= 15) {
                    System.out.println("----------------------------------");
                    runTest(result);
                    System.out.println("----------------------------------\n" +
                                        "Nyomj barmely gombot a folytatashoz");
                    scanner.nextLine();
                } else {
                    System.out.println("\nHibas input! Az inputnak 0 es 15 koze kell esnie!");
                    System.out.println("Nyomj barmely gombot a folytatashoz");
                    scanner.nextLine();
                }
            } catch(java.lang.NumberFormatException e) {
                System.out.println("\nHibas input! Az input csak egesz szam lehet!");
                System.out.println("Nyomj barmely gombot a folytatashoz");
                scanner.nextLine();
            }
        }
    }

    private static void runTest(int n) {
        switch(n) {
            case 1: {
                System.out.println("//// INIT ////");
                Floe f1 = new Floe();
                Eskimo c = new Eskimo();
                f1.Accept(c);
                System.out.println("//// RUN ////");
                c.Dig();
                break;
            }
            case 2: {
                System.out.println("//// INIT ////");
                Floe f1 = new Floe();
                Floe f2 = new Floe();
                Eskimo c = new Eskimo();
                f1.setNeighbour(Direction.NORTH, f2);
                f1.Accept(c);
                System.out.println("//// RUN ////");
                c.Move(Direction.NORTH);
                break;
            }
            case 3: {
                System.out.println("//// INIT ////");
                Floe f1 = new Floe();
                UnstableFloe uf = new UnstableFloe();
                Eskimo c = new Eskimo();
                f1.setNeighbour(Direction.NORTH, uf);
                f1.Accept(c);
                System.out.println("//// RUN ////");
                c.Move(Direction.NORTH);
                break;
            }
            case 4: {
                System.out.println("//// INIT ////");
                Floe f1 = new Floe();
                Hole h = new Hole();
                Eskimo c = new Eskimo();
                f1.setNeighbour(Direction.NORTH, h);
                f1.Accept(c);
                System.out.println("//// RUN ////");
                c.Move(Direction.NORTH);
                break;
            }
            case 5: {
                System.out.println("//// INIT ////");
                GameField gf = new GameField();
                Floe f1 = new Floe();
                Eskimo c = new Eskimo();
                f1.Accept(c);
                gf.addField(f1);
                System.out.println("//// RUN ////");
                gf.SnowStorm();
                break;
            }
            case 6: {
                System.out.println("//// INIT ////");
                Floe f1 = new Floe();
                Eskimo c = new Eskimo();
                f1.Accept(c);
                System.out.println("//// RUN ////");
                f1.SnowStormHit();
                break;
            }
            case 7: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                System.out.println("//// RUN ////");
                c.FellInWater();
                break;
            }
            case 8: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Floe f1 = new Floe();
                Flare f = new Flare();
                f1.Accept(c);
                c.addItem(f);
                System.out.println("//// RUN ////");
                c.UseItem(0);
                break;
            }
            case 9: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Food f = new Food();
                c.addItem(f);
                System.out.println("//// RUN ////");
                c.UseItem(0);
                break;
            }
            case 10: {
                System.out.println("//// INIT ////");
                Eskimo c1 = new Eskimo();
                Eskimo c2 = new Eskimo();
                Rope r = new Rope();
                Floe f1 = new Floe();
                Floe f2 = new Floe();
                f1.setNeighbour(Direction.NORTH, f2);
                f2.setNeighbour(Direction.SOUTH, f1);
                f1.Accept(c1);
                f2.Accept(c2);
                c1.addItem(r);
                System.out.println("//// RUN ////");
                c1.UseItem(0);
                break;
            }
            case 11: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Divingsuit d = new Divingsuit();
                c.addItem(d);
                System.out.println("//// RUN ////");
                c.UseItem(0);
                break;
            }
            case 12: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Floe f1 = new Floe();
                Shovel s = new Shovel();
                f1.Accept(c);
                c.addItem(s);
                System.out.println("//// RUN ////");
                c.UseItem(0);
                break;
            }
            case 13: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Floe f1 = new Floe();
                Shovel s = new Shovel();
                f1.Accept(c);
                f1.setItem(s);
                System.out.println("//// RUN ////");
                c.PickUpItem();
                break;
            }
            case 14: {
                System.out.println("//// INIT ////");
                Eskimo c = new Eskimo();
                Floe f1 = new Floe();
                f1.Accept(c);
                System.out.println("//// RUN ////");
                c.UseAbility(Direction.NORTH);
                break;
            }
            case 15: {
                System.out.println("//// INIT ////");
                Researcher c = new Researcher();
                Floe f1 = new Floe();
                Floe f2 = new Floe();
                f1.Accept(c);
                f1.setNeighbour(Direction.NORTH, f2);
                System.out.println("//// RUN ////");
                c.UseAbility(Direction.NORTH);
                break;
            }
            default:    //?
                break;
        }
    }

    public static void methodCalled(String classname, String methodname) {
        System.out.println(getIndent()+classname +"."+ methodname);
    }

    private static String getIndent() {
        String indent = "";
        for(int i = 0; i < indentLevel; i++) {
            indent = indent + "    ";
        }
        return indent;
    }

    public static void indent() {
        indentLevel++;
    }

    public static void returned() {
        indentLevel--;
    }

    public static void ctorCalled(String classname) {
        System.out.println(getIndent()+classname+"."+classname+"()");
    }


    public static boolean askQuestion(String s) {
        System.out.println(getIndent()+"---"+s+"(I/N)---");
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
