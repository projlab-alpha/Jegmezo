package control;

/**
 * Prototípus IO/parancsokat megvalósító osztály.
 */
public class Commands {
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
