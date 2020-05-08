package control;

import Display.DisplayWindow;

public class Main {
    public static void main(String[] args) {        //TODO: size/player num selection
        System.out.println("Running");
        DisplayWindow dw = new DisplayWindow(5, 5);
        dw.setVisible(true);
    }
}
