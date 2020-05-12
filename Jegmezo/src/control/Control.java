package control;

import Display.DisplayWindow;
import character.Character;
import character.PolarBear;
import field.GameField;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Singleton osztály, amely a játék állapotának követésért, a játék lebonyolításáért felelős.
 */
public class Control {
    //TODO: Cycle between players
    //TODO: Turn logic
    //TODO: Snowstorms
    //TODO: Remove tents after each turn
    //TODO: End game if any character has isDrowning == true


    /**
     * A játékos karakterek száma a játékmezőn.
     */
    private int PlayerCount;

    private int turn;
    private int currentplayer;
    private DisplayWindow window;

    /**
     * A játékos karakterek listája.
     */
    private ArrayList<character.Character> characters;

    /**
     * A játékban résztvevő jegesmedve referenciája.
     */
    private PolarBear polarBear;

    /**
     *  A játékmezőre mutató referencia.
     */
    private GameField gameField;

    /**
     *  Az egyetlen példánya az osztálynak a Singleton működés érdekében.
     */
    private static Control control = null;

    /**
     *   Privát konstruktor a Singleton működés érdekében.
     */
    private Control(){}

    /**
     * Visszatér az osztály egyetlen példányával ha van,
     * inicializálja a példányt és visszatér vele, ha nincs.
     * @return Az osztály példánya
     */
    public static Control getInstance(){
        if (control == null)
            control = new Control();
        return control;
    }

    public character.Character getCurrentChar() {
        return characters.get(currentplayer);
    }

    public int getTurn() {
        return turn;
    }

    public void requestRedraw() { window.redraw(); }


    public void initializeGame(int width, int height, ArrayList<Character> chars) {        //TODO: new method
        PlayerCount = chars.size();
        characters = chars;
        polarBear = new PolarBear();
        gameField = new GameField(width, height, characters, polarBear);
        window = new DisplayWindow(width, height);
        window.setVisible(true);
    }

    public void keyPressed(KeyEvent e) {        //disabled for debug
        //character.Character ch = characters.get(currentplayer);
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            //case KeyEvent.VK_SPACE:
            //    currentplayer = (currentplayer + 1 < PlayerCount) ? currentplayer + 1 : 0;
            //    //TODO: step polar bear, summon snowstorm, remove tents, increase turn count, end game if any character is drowning
            //    break;
            //case KeyEvent.VK_LEFT:
            //    ch.Move(Direction.WEST);
            //    break;
            //case KeyEvent.VK_RIGHT:
            //    ch.Move(Direction.EAST);
            //    break;
            //case KeyEvent.VK_UP:
            //    ch.Move(Direction.NORTH);
            //    break;
            //case KeyEvent.VK_DOWN:
            //    ch.Move(Direction.SOUTH);
            //    break;
            //case KeyEvent.VK_A:
            //    ch.UseAbility(Direction.WEST);
            //    break;
            //case KeyEvent.VK_D:
            //    ch.UseAbility(Direction.EAST);
            //    break;
            //case KeyEvent.VK_W:
            //    ch.UseAbility(Direction.NORTH);
            //    break;
            //case KeyEvent.VK_S:
            //    ch.UseAbility(Direction.SOUTH);
            //    break;
            default:                                    //handle number keys here to reduce clutter
                if(49 <= keyCode && keyCode <= 57) {    //49-57 are the key codes of 1-9 on the keyboard
                    //ch.UseItem(keyCode - 48 - 1);   // keyCode - 48 gets an int between 1 and 9, subtract 1 to get valid array index
                    //System.out.println("Keycode: "+keyCode+" Number pressed: "+(keyCode - 48)+" Array idx: "+(keyCode - 48 - 1));
                }
                break;
        }
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy
     * egy játékos karakter valamilyen okból meghalt, és így a játéknak véget kell vetni.
     */
    public void CharacterDied(){
        //TODO
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy sikerült
     * összerakni és elsütni a jelző pisztolyt, és így játéknak véget kell vetni.
     */
    public void Win(){
        //TODO
    }
}
