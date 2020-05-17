package control;

import Display.DisplayWindow;
import character.Character;
import character.PolarBear;
import field.AbstractField;
import field.GameField;
import snowstormStrategy.SnowstormStrategyDefault;
import snowstormStrategy.SnowstormStrategyTent;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Singleton osztály, amely a játék állapotának követésért, a játék lebonyolításáért felelős.
 */
public class Control {
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

    public void initializeGame(int width, int height, ArrayList<Character> chars) {
        PlayerCount = chars.size();
        currentplayer = 0;
        characters = chars;
        polarBear = new PolarBear();
        gameField = new GameField(width, height, characters, polarBear);
        window = new DisplayWindow(width, height, gameField);
    }

    public void keyPressed(KeyEvent e) {
        character.Character ch = characters.get(currentplayer);
        int keyCode = e.getKeyCode();
        int res;
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                if (currentplayer + 1 >= PlayerCount) {  //all players played this turn, control goes back to player 1, new turn begins
                    currentplayer = 0;
                    int ranDir = new Random().nextInt(4);   //move polar bear in a random direction
                    polarBear.Move(Direction.values()[ranDir]);
                    for (AbstractField f : gameField.getFloes()) {   //remove tents
                        if (f.ChangeSnowStrategy(null) instanceof SnowstormStrategyTent)
                            f.ChangeSnowStrategy(new SnowstormStrategyDefault());
                    }
                    gameField.SnowStorm();
                    turn++;
                } else currentplayer++;
                if (characters.get(currentplayer).isDrowning())  //end game if next player is drowning
                    CharacterDied();
                characters.get(currentplayer).resetAP();
                break;
            case KeyEvent.VK_LEFT: ch.Move(Direction.WEST); break;
            case KeyEvent.VK_RIGHT: ch.Move(Direction.EAST); break;
            case KeyEvent.VK_UP: ch.Move(Direction.NORTH); break;
            case KeyEvent.VK_DOWN: ch.Move(Direction.SOUTH); break;
            case KeyEvent.VK_A:
                res = ch.UseAbility(Direction.WEST);
                if(res > -1)
                    window.showActionResult("Reasearcher", Direction.WEST, res);
                break;
            case KeyEvent.VK_D:
                res = ch.UseAbility(Direction.EAST);
                if(res > -1)
                    window.showActionResult("Reasearcher", Direction.EAST, res);
                break;
            case KeyEvent.VK_W:
                res = ch.UseAbility(Direction.NORTH);
                if(res > -1)
                    window.showActionResult("Reasearcher", Direction.NORTH, res);
                break;
            case KeyEvent.VK_S:
                res = ch.UseAbility(Direction.SOUTH);
                if(res > -1)
                    window.showActionResult("Reasearcher", Direction.SOUTH, res);
                break;
            case KeyEvent.VK_SHIFT: ch.PickUpItem(); break;
            case KeyEvent.VK_CONTROL: ch.Dig(); break;
            default:                                      //handle number keys here to reduce clutter
                if (49 <= keyCode && keyCode <= 57) {    //49-57 are the key codes of 1-9 on the keyboard
                    ch.UseItem(keyCode - 48 - 1);   // keyCode - 48 gets an int between 1 and 9, subtract 1 to get valid array index
                }
                break;
        }
        window.redraw();
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy
     * egy játékos karakter valamilyen okból meghalt, és így a játéknak véget kell vetni.
     */
    public void CharacterDied(){
        window.showDefeat();
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy sikerült
     * összerakni és elsütni a jelző pisztolyt, és így játéknak véget kell vetni.
     */
    public void Win(){
        window.showVictory();
    }
}
