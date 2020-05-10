package control;

public class Main {
    public static void main(String[] args) {        //TODO: size/player num selection
        System.out.println("Running");
        Control.getInstance().initializeGame(7, 7, 1);
    }
}
