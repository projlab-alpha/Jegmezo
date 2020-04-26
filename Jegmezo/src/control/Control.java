package control;

import character.PolarBear;
import field.GameField;

import java.util.ArrayList;

/**
 * Singleton osztály, amely a játék állapotának követésért, a játék lebonyolításáért felelős.
 */
public class Control {

    /**
     * A játékos karakterek száma a játékmezőn.
     */
    private int PlayerCount;

    /**
     * A játékos karakterek listája.
     */
    private ArrayList<character.Character> characters = new ArrayList<character.Character>();

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

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy
     * egy játékos karakter valamilyen okból meghalt, és így a játéknak véget kell vetni.
     */
    public void CharacterDied(){    //TODO

    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy sikerült
     * összerakni és elsütni a jelző pisztolyt, és így játéknak véget kell vetni.
     */
    public void Win(){      //TODO

    }


    //// COMMANDS ////

    private static int map_size = -1;

    public static int Load(String filename) {

    }

    public static int Save(String filename) {

    }

    public static int Init(int size) {

    }

    //public static int SetNeighbor(AbstractFloe floe1, AbstractFloe floe2)     //TODO: Mire gondoltatok? Hogyan lesz egy szöveges parancsból AbstractFloe példány?
    public static int SetNeighbor(String floe1, String floe2) {                 //      inkább így, nem?

    }

    public static int AddChar(String chartype, int width, int height) {

    }

    public static int MoveChar(int chara, String direction) {

    }

    public static int MoveChar(int chara, int width, int height) {

    }

    public static int ShowCharDetails(int chara, String attr) {

    }

    public static int Dig(int chara) {

    }

    public static int Pickup(int chara) {

    }

    public static int UseItem(int chara, String item) {

    }

    public static int UseAbility(int chara, String direction) {

    }

    public static int SnowStorm(int width, int height) {

    }

    public static int Push(String filename) {

    }

    public static int CharAddItem(int chara, String item) {         //TODO: Prototípus concepcióban az argumentumnak Item helyett nem Stringnek kéne lennie?

    }

    public static int FloeAddItem(int width, int height, String item) {

    }

    public static int CharWaterStrategy(int chara, String ws) {

    }

    public static int FlowSnowStormStrategy(int width, int height, String sss) {

    }

    public static int CharActionPoints(int chara, int ap) {

    }

    public static int CharWarmth(int chara, int warmth) {       //TODO: Miért? Karakterek konstruktorban megkapják alapból a testhőjüket...

    }

    public static int AddUnstableFloe(int width, int height, int capacity) {

    }

    public static int AddHole(int width, int height) {

    }

    public static int SetSnow(int width, int height, int snow) {

    }
}
