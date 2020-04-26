package control;

import character.Eskimo;
import character.PolarBear;
import character.Researcher;
import field.*;
import item.*;
import snowstormStrategy.SnowstormStrategy;
import snowstormStrategy.SnowstormStrategyDefault;
import snowstormStrategy.SnowstormStrategyIgloo;
import snowstormStrategy.SnowstormStrategyTent;
import waterStrategy.WaterStrategy;
import waterStrategy.WaterStrategyDefault;
import waterStrategy.WaterStrategySuit;

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

        for(int i = 0; i < size * size; ++i) {
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

    public static int AddChar(String chartype, String floename) {
        character.Character ch;

        if(chartype.equalsIgnoreCase("Eskimo")) {
            ch = new Eskimo();
        } else if (chartype.equalsIgnoreCase("Researcher")) {
            ch = new Researcher();
        } else return -1;
        try {
            int idx = floename.charAt(4);
            Control.getInstance().characters.add(ch);
            Control.getInstance().PlayerCount += 1;
            Control.getInstance().gameField.getFloeAt(idx).Accept(ch);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }


    public static int MoveChar(int chara, String floename) {        //TODO: 08 - Részletes tervek: 8.2.7 - 'Down' argumentum helyett floex kell
        try {
            int idx = floename.charAt(4);
            AbstractField f = Control.getInstance().gameField.getFloeAt(idx);
            character.Character c = Control.getInstance().characters.get(chara);
            c.setField(f);
            f.Accept(c);
        } catch(Exception e) {
            return -1;
        }
        return 0;
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

    public static int UseAbility(int chara, String direction) {     //TODO: milyen irányok vannak?

    }

    public static int SnowStorm(String floename) {
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloeAt(idx).SnowStormHit();
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

    public static int FloeAddItem(String floename, String item) {
        Item i = convertItem(item);
        if(i == null)
            return -1;
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloeAt(idx).setItem(i);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public static int CharWaterStrategy(int chara, String ws) {
        WaterStrategy w;
        if(ws.equalsIgnoreCase("Default"))
            w = new WaterStrategyDefault();
        else if(ws.equalsIgnoreCase("DivingSuit") || ws.equalsIgnoreCase("Suit"))
            w = new WaterStrategySuit();
        else return -1;
        try {
            Control.getInstance().characters.get(chara).ChangeStrategy(w);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int FloeSnowStormStrategy(String floename, String sss) {
        SnowstormStrategy s;
        if(sss.equalsIgnoreCase("default"))
            s = new SnowstormStrategyDefault();
        else if(sss.equalsIgnoreCase("igloo"))
            s = new SnowstormStrategyIgloo();
        else if(sss.equalsIgnoreCase("tent"))
            s = new SnowstormStrategyTent();
        else return -1;
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloeAt(idx).ChangeSnowStrategy(s);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int CharActionPoints(int chara, int ap) {

    }

    public static int CharWarmth(int chara, int warmth) {       //TODO: Miért? Karakterek konstruktorban megkapják alapból a testhőjüket...

    }

    public static int AddUnstableFloe(String floename, int capacity) {
        int rand_snowcount = new Random().nextInt(4) + 1;
        UnstableFloe uf = new UnstableFloe(null, capacity, rand_snowcount);
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloes().set(idx, uf);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int AddHole(String floename) {
        int rand_snowcount = new Random().nextInt(4) + 1;
        Hole h = new Hole(null, rand_snowcount);
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloes().set(idx, h);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public static int SetSnow(String floename, int snow) {
        try {
            int idx = floename.charAt(4);
            Control.getInstance().gameField.getFloeAt(idx).setSnowCount(snow);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }
}
