package control;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {        //TODO: size/player num selection
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
        Control.getInstance().initializeGame(widthInput, heightInput, playerCountInput);
    }
}
