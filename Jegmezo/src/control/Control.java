package control;

import character.Eskimo;
import character.PolarBear;
import character.Researcher;
import field.AbstractField;
import field.Floe;
import field.GameField;
import item.*;

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

    /**
     * Pálya szélessége és magassága
     */
    private static int map_size = -1;

    private static int convertCoords(int x, int y) {
        if (x < 0 || x >= map_size || y < 0 || y >= map_size)
            return -1;
        return y * map_size + x;
    }

    private static Item convertItem(String item) {
        Item i;
        if(item.equalsIgnoreCase("Cartridge"))
            i = new Cartridge();
        else if (item.equalsIgnoreCase("Divingsuit"))
            i = new Divingsuit();
        else if (item.equalsIgnoreCase("Flare"))
            i = new Flare();
        else if (item.equalsIgnoreCase("Food"))
            i = new Food();
        else if (item.equalsIgnoreCase("FragileShovel"))
            i = new FragileShovel();
        else if (item.equalsIgnoreCase("Pistol"))
            i = new Pistol();
        else if (item.equalsIgnoreCase("Rope"))
            i = new Rope();
        else if (item.equalsIgnoreCase("Shovel"))
            i = new Shovel();
        else if (item.equalsIgnoreCase("Tent"))
            i = new Tent();
        else i = null;
        return i;
    }


    public static int Load(String filename) {

    }

    public static int Save(String filename) {

    }

    public static int Init() {
        return Init(5);
    }

    public static int Init(int size) {
        if(size <= 0)
            return -1;

        map_size = size;

        for(int i = 0; i < map_size * map_size; ++i) {
            int rand_snowcount = new Random().nextInt(4) + 1;     //random int between 1 and 4 (inclusive)
            Floe f = new Floe(null, 10, rand_snowcount);
            Control.getInstance().gameField.addField(f);
        }
        return 0;
    }

    public static int SetNeighbor(String floename1, String floename2) {                 //inkább így, nem?
        int index1 = floename1.charAt(4);
        int index2 = floename1.charAt(4);
        AbstractField floe1 = Control.getInstance().gameField.getFloeAt(index1);
        AbstractField floe2 = Control.getInstance().gameField.getFloeAt(index2);
        //TODO: Honnan tudjuk, melyik irányban szomszédosak?
        return -1;
    }

    public static int AddChar(String chartype, int width, int height) {
        character.Character ch;

        if(chartype.equalsIgnoreCase("Eskimo")) {
            ch = new Eskimo();
        } else if (chartype.equalsIgnoreCase("Researcher")) {
            ch = new Researcher();
        } else return -1;
        try {
            Control.getInstance().characters.add(ch);
            Control.getInstance().PlayerCount += 1;
            Control.getInstance().gameField.getFloeAt(convertCoords(width, height)).Accept(ch);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int MoveChar(int chara, String direction) {

    }

    public static int MoveChar(int chara, int width, int height) {

    }

    public static int ShowCharDetails(int chara, String attr) {

    }

    public static int Dig(int chara) {
        try {
            Control.getInstance().characters.get(chara).Dig();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int Pickup(int chara) {
        try {
            Control.getInstance().characters.get(chara).PickUpItem();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int UseItem(int chara, String item) {
        try {
            Control.getInstance().characters.get(chara).UseItem();      //TODO: item névből index
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int UseAbility(int chara, String direction) {

    }

    public static int SnowStorm(int width, int height) {
        try {
            Control.getInstance().gameField.getFloeAt(convertCoords(width, height)).SnowStormHit();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int Push(String filename) {

    }

    public static int CharAddItem(int chara, String item) {         //TODO: Prototípus concepcióban az argumentumnak Item helyett nem Stringnek kéne lennie?
        try {
            character.Character ch = Control.getInstance().characters.get(chara);
            Item i = convertItem(item);
            if(i == null)
                return -1;
            ch.addItem(i);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public static int FloeAddItem(int width, int height, String item) {
        Item i = convertItem(item);
        if(i == null)
            return -1;
        try {
            Control.getInstance().gameField.getFloeAt(convertCoords(width, height)).setItem(i);
        } catch (Exception e) {
            return -1;
        }
        return 0;
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
