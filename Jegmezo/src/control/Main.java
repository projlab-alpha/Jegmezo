package control;

import character.Eskimo;
import character.Researcher;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Ez az osztály a program belépési pontja, innen indul a játék.
 */
public class Main {
    /**
     * A program belépési pontja, lekérdezi az indítandó játék kívánt beállításait, avagy
     * - a játéktábla szélessége és magassága
     * - a játékosok száma
     * - melyik játékos milyen karaktert kíván irányítani.
     * Ez után elindítja a játék inicializálását.
     * @param args  Nem használt.
     */
    public static void main(String[] args) {
        System.out.println("Running");
        int widthInput = -1, heightInput = -1;
        while(widthInput < 0) {
            try {
                widthInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width:", "New game", JOptionPane.INFORMATION_MESSAGE));
                heightInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height:", "New game", JOptionPane.INFORMATION_MESSAGE));
                if(widthInput < 5 || widthInput > 20 || heightInput < 5 || heightInput > 20) {
                    JOptionPane.showMessageDialog(null, "Illegal table size! Width/Height must be between 5 and 20!");
                    widthInput = heightInput = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input must be a number!");
                widthInput = heightInput = -1;
            }
        }

        int playerCountInput = -1;
        while(playerCountInput < 0) {
            try {
                playerCountInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter player count:", "New game", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input must be a number!");
                playerCountInput = -1;
            }
            if(playerCountInput < 3 || playerCountInput > widthInput * heightInput) {
                JOptionPane.showMessageDialog(null, "Input must be between 3 and "+ (widthInput * heightInput) +"!");
                playerCountInput = -1;
            }
        }
        ArrayList<character.Character> chars = new ArrayList<>(playerCountInput);
        Object[] characterSelectionPossibilities = {"Eskimo", "Researcher"};
        for(int i = 0; i < playerCountInput; ++i) {
            String s = (String) JOptionPane.showInputDialog(null, "Choose character (Player "+(i + 1)+"):",
                    "New Game", JOptionPane.PLAIN_MESSAGE, null, characterSelectionPossibilities, "Eskimo");
            switch(s) {
                case "Eskimo":
                    chars.add(new Eskimo());
                    break;
                case "Researcher":
                    chars.add(new Researcher());
                    break;
                default:
                    chars.add(new Eskimo());
                    break;
            }
        }
        Control.getInstance().initializeGame(widthInput, heightInput, chars);
    }
}
