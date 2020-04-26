package control;

import java.util.Scanner;

/**
 * A prototípus tesztelését végrehajtó osztály,
 * és a program jelenlegi belépési pontja.
 */
public class Prototype {
    static boolean exit = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(!exit) {
            String[] input = scanner.nextLine().split(" ");
            int res = parseInput(input);
            System.out.println(res);
        }
        scanner.close();
        System.out.println("Goodbye");
    }

    private static int parseInput(String[] input) {
        try {
            String command = input[0];
            int res = -1;
            if (command.equalsIgnoreCase("init")) {
                if (input.length > 1) {
                    int arg = Integer.parseInt(input[1]);
                    res = Control.getInstance().Init(arg);
                } else
                    res = Control.getInstance().Init();
            } else if (command.equalsIgnoreCase("Load"))
                res = Control.getInstance().Load(input[1]);
            else if (command.equalsIgnoreCase("Save"))
                res = Control.getInstance().Save(input[1]);
            else if (command.equalsIgnoreCase("AddChar"))
                res = Control.getInstance().AddChar(input[1], input[2]);
            else if (command.equalsIgnoreCase("MoveChar"))
                res = Control.getInstance().MoveChar(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("ShowCharDetails"))
                res = Control.getInstance().ShowCharDetails(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("Dig"))
                res = Control.getInstance().Dig(Integer.parseInt(input[1]));
            else if (command.equalsIgnoreCase("Pickup"))
                res = Control.getInstance().Pickup(Integer.parseInt(input[1]));
            else if (command.equalsIgnoreCase("UseItem"))
                res = Control.getInstance().UseItem(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("UseAbility"))
                res = Control.getInstance().UseAbility(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("Snowstorm"))
                res = Control.getInstance().SnowStorm(input[1]);
            else if (command.equalsIgnoreCase("Push"))
                res = Control.getInstance().Push(input[1]);
            else if (command.equalsIgnoreCase("CharAddItem"))
                res = Control.getInstance().CharAddItem(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("FloeAddItem"))
                res = Control.getInstance().FloeAddItem(input[1], input[2]);
            else if (command.equalsIgnoreCase("CharWaterStrategy"))
                res = Control.getInstance().CharWaterStrategy(Integer.parseInt(input[1]), input[2]);
            else if (command.equalsIgnoreCase("FloeSnowStormStrategy"))
                res = Control.getInstance().FloeSnowStormStrategy(input[1], input[2]);
            else if (command.equalsIgnoreCase("CharActionPoints"))
                res = Control.getInstance().CharActionPoints(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            else if (command.equalsIgnoreCase("CharWarmth"))
                res = Control.getInstance().CharWarmth(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            else if (command.equalsIgnoreCase("AddUnstableFloe"))
                res = Control.getInstance().AddUnstableFloe(input[1], Integer.parseInt(input[2]));
            else if (command.equalsIgnoreCase("Addhole"))
                res = Control.getInstance().AddHole(input[1]);
            else if (command.equalsIgnoreCase("SetSnow"))
                res = Control.getInstance().SetSnow(input[1], Integer.parseInt(input[2]));
            else if (command.equalsIgnoreCase("exit"))
                exit = true;
            else {
                System.out.println("Invalid command!");
                return -1;
            }
            return res;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid arguments!");
            return -1;
        }
    }
}
