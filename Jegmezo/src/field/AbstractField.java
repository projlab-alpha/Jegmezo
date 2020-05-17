package field;

import bearAttackStrategy.BearAttackStrategy;
import bearAttackStrategy.BearAttackStrategyDefault;
import character.Eskimo;
import character.PolarBear;
import character.Researcher;
import control.Direction;
import item.Item;
import snowstormStrategy.SnowstormStrategy;
import snowstormStrategy.SnowstormStrategyDefault;
import snowstormStrategy.SnowstormStrategyIgloo;
import snowstormStrategy.SnowstormStrategyTent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A jĂˇtĂ©kban elĹ‘fordulĂł mezĹ‘ket reprezentĂˇlĂł osztĂˇlyok absztrakt Ĺ‘sosztĂˇlya,
 * tĂˇrolja a szomszĂ©djait, valamint a karaktereket, akik rajta Ăˇllnak.
 */
public abstract class AbstractField {

    /**
     * A mezĹ‘t fedĹ‘ hĂł mennyisĂ©ge.
     */
    private int SnowCount;

    /**
     * A mezĹ‘ teherbĂ­rĂł kĂ©pessĂ©ge, megadja, egyszerre hĂˇny karakter fĂ©r el
     * rajta vĂ­zbe esĂ©s veszĂ©lye nĂ©lkĂĽl.
     */
    protected int Capacity;

    /**
     * A stratĂ©gia, amely meghatĂˇrozza a mĹ±kĂ¶dĂ©st abban az esetben, ha
     * egy medve lĂ©p a mezĹ‘re.
     */
    private BearAttackStrategy bearattackstrat;

    /**
     * A stratĂ©gia, amely meghatĂˇrozza a mĹ±kĂ¶dĂ©st abban az esetben, ha
     * a mezĹ‘t jĂ©gvihar Ă©rint.
     */
    private SnowstormStrategy snowstormstrat;

    /**
     * A mezĹ‘n talĂˇlhatĂł item. Ha a mezĹ‘n nincsen item, akkor ennek Ă©rtĂ©ke null.
     */
    private Item item;

    /**
     * KollekciĂł, amely a mezĹ‘vel szomszĂ©dos mezĹ‘k referenciĂˇjĂˇt tĂˇrolja, a lehetsĂ©ges irĂˇnyokban.
     */
    private HashMap<Direction, AbstractField> neighbours;

    /**
     * KollekciĂł a mezĹ‘n jelenleg elhelyezkedĹ‘ karakterekrĹ‘l.
     */
    protected ArrayList<character.Character> characters;


    /**
     * BeĂˇllĂ­tja a jĂ©gtĂˇblĂˇba fagyott tĂˇrgyat, a tĂˇbla kapacitĂˇsĂˇt, Ă©s a tĂˇblĂˇn lĂ©vĹ‘ hĂł mennyisĂ©gĂ©t.
     * @param item a jĂ©gtĂˇblĂˇba fagyott tĂˇrgy
     * @param capacity a tĂˇbla kapacitĂˇsa
     * @param snowcount a tĂˇblĂˇn lĂ©vĹ‘ hĂł mennyisĂ©ge
     */
    public AbstractField(Item item, int capacity, int snowcount) {
        this.item = item;
        this.Capacity = capacity;
        this.SnowCount = snowcount;
        neighbours = new HashMap<>();
        characters = new ArrayList<>();
        snowstormstrat = new SnowstormStrategyDefault();
        bearattackstrat = new BearAttackStrategyDefault();
    }

    /**
     * Absztrakt metĂłdus, elfogad egy karaktert, amely a mezĹ‘re prĂłbĂˇl mozogni.
     * @param c Az Ăˇtvett karakter
     */
    public abstract void Accept(character.Character c);

    /**
     * Ă�tlĂ©pteti az argumentumkĂ©nt Ăˇtadott
     * karaktert a megfelelĹ‘ irĂˇnyban lĂ©vĹ‘ szomszĂ©dos mezĹ‘re, Ăşgy, hogy meghĂ­vja a
     * szomszĂ©dos mezĹ‘ Accept metĂłdusĂˇt az Ăˇtadott karakterrel.
     * @param d A mozgĂˇs irĂˇnya
     * @param c A mozgatandĂł karakter
     */
    public boolean MoveChar(Direction d, character.Character c){
        if(neighbours.containsKey(d)) {
            neighbours.get(d).Accept(c);
            characters.remove(c);
            return true;
        }
        return false;
    }

    /**
     * Az inicializĂˇlĂˇshoz hasznĂˇlt
     * metĂłdus, amely elhelyezi a szomszĂ©dokat tĂˇrolĂł kollekciĂłban az argumentumkĂ©nt
     * megadott mezĹ‘t a megadott irĂˇnnyal pĂˇrosĂ­tva.
     * @param d A szomszĂ©d irĂˇnya
     * @param neighbour a szomszĂ©dos mezĹ‘
     */
    public void setNeighbour(Direction d, AbstractField neighbour) {
            neighbours.put(d, neighbour);
    }

    /**
     *  HozzĂˇadja az argumentumban megadott Ă©rtĂ©ket a mezĹ‘t
     * fedĹ‘ hĂł mennyisĂ©gĂ©hez. A hĂł mennyisĂ©ge a vĂˇltoztatĂˇs utĂˇn garantĂˇltan nem lesz
     * negatĂ­v.
     * @param i A vĂˇltoztatĂˇs mennyisĂ©ge
     */
    public int ChangeSnow(int i){
        SnowCount += i;
        if(SnowCount < 0)
            SnowCount = 0;
        return SnowCount;
    }

    /**
     * Ha a mezĹ‘n nincsen item, vagy, ha a mezĹ‘t tĂ¶bb, mint 0
     * egysĂ©gnyi hĂł borĂ­tja, akkor a metĂłdus null referenciĂˇval tĂ©r vissza. Ha a mezĹ‘n van
     * item, Ă©s 0 egysĂ©gnyi hĂł borĂ­tja, akkor ideiglenesen tĂˇrolja a mezĹ‘n lĂ©vĹ‘ item
     * referenciĂˇjĂˇt, tĂ¶rli a valĂłs referenciĂˇt a mezĹ‘ tagvĂˇltozĂłjĂˇbĂłl, majd visszatĂ©r a tĂˇrolt
     * item referenciĂˇjĂˇval.
     * @return A tĂˇblĂˇn lĂ©vĹ‘ tĂˇrgy, vagy null, ha nem elĂ©rhetĹ‘
     */
    public Item RequestItem(){
        if(item != null && SnowCount == 0) {
            Item tempItem = item;
            item = null;
            return tempItem;
        }
        else
            return null;
    }

    /**
     * VisszatĂ©r a megadott irĂˇnyban talĂˇlhatĂł szomszĂ©dos
     * mezĹ‘ Capacity tagvĂˇltozĂłjĂˇnak jelenlegi Ă©rtĂ©kĂ©vel.
     * @param d A keresett irĂˇny
     * @return A jĂ©gtĂˇbla kapacitĂˇsa
     */
    public int FindCapacity(Direction d){
        if(neighbours.containsKey(d))
            return neighbours.get(d).Capacity;
        else return -1;
    }
    
    public int FindCapacity() {
    	return Capacity;
    }

    /**
     * Ez a metĂłdus hĂ­vĂłdik meg, ha a mezĹ‘t jĂ©gvihar Ă©rint.
     * MeghĂ­vja a tĂˇrolt SnowStormStrategy execute() metĂłdusĂˇt, Ăˇtadva a characters
     * tagvĂˇltozĂł referenciĂˇjĂˇt.
     */
    public void SnowStormHit(){
        this.ChangeSnow(1);
        snowstormstrat.execute(characters);
    }

    /**
     * A characters tagvĂˇltozĂł Ă¶sszes elemĂ©re meghĂ­vja fejenkĂ©nt
     * hĂˇromszor a karakter HasItem() metĂłdusĂˇt, sorban â€śFlareâ€ť, â€śPistolâ€ť, majd â€śCartridgeâ€ť
     * argumentumokkal. Ha, miutĂˇn az Ă¶sszes karakterre lefutott mindhĂˇrom hĂ­vĂˇs, igaz az,
     * hogy a hĂˇrom tĂ­pusĂş hĂ­vĂˇs kĂ¶zĂĽl mindegyik legalĂˇbb egyszer igazzal tĂ©rt vissza (tehĂˇt
     * pĂ©ldĂˇul ha volt olyan HasItem(â€śFlareâ€ť) hĂ­vĂˇs, amely igazzal tĂ©rt vissza, akkor a
     * â€śFlareâ€ť hĂ­vĂˇs tĂ­pusra ez a feltĂ©tel teljesĂĽlt), akkor a metĂłdus igazzal tĂ©r vissza,
     * ellenkezĹ‘ esetben hamissal.
     * @return Igaz, ha Ă¶sszerakhatĂł a Flaregun, kĂĽlĂ¶nben hamis
     */
    public boolean CheckFlareGun(){
        boolean flare = false, pistol = false, cart = false;
        for (character.Character c : characters) {
            if(c.HasItem("Flare")) flare = true;
            if(c.HasItem("Pistol")) pistol = true;
            if(c.HasItem("Cartridge")) cart = true;
        }
        return (flare && pistol && cart);
    }

    /**
     * MeghĂ­vja a mezĹ‘ Ă¶sszes szomszĂ©djĂˇnak RescueChars metĂłdusĂˇt.
     * ArgumentumkĂ©nt a sajĂˇt magĂˇra mutatĂł referenciĂˇt adja Ăˇt.
     */
    public void Rescue(){
        for (Direction d : Direction.values()) {
            if(neighbours.containsKey(d))
                neighbours.get(d).RescueChars(this);
        }
    }

    /**
     * A characters kollekciĂł Ă¶sszes elemĂ©re meghĂ­vja
     * elĹ‘szĂ¶r a karakter IsDrowning() metĂłdusĂˇt, majd ha ez igazzal tĂ©r vissza, akkor
     * meghĂ­vja a karakter Rescued() metĂłdusĂˇt, majd az argumentumban kapott mezĹ‘
     * Accept() metĂłdusĂˇt, argumentumkĂ©nt az Ă©ppen vizsgĂˇlt karaktert Ăˇtadva.
     * @param f A mezĹ‘, ahovĂˇ a karaktereket menteni kell
     */
    public void RescueChars(AbstractField f){
        for (character.Character c : characters) {
            if (c.isDrowning()) {
                c.Rescued();
                f.Accept(c);
            }
        }
    }

    /**
     * MegvĂˇltoztatja a hĂłvihar Ă©rintĂ©s
     * esetĂ©n vĂ©grehajtandĂł stratĂ©giĂˇt az argumentumban megadottra.
     * @param s Az Ăşj stratĂ©gia
     */
    public SnowstormStrategy ChangeSnowStrategy(SnowstormStrategy s){
        if (s == null) return snowstormstrat;
    	snowstormstrat = s;
        return s;
    } 

    /**
     * MegvĂˇltoztatja a medve
     * tĂˇmadĂˇskor vĂ©grehajtandĂł stratĂ©giĂˇt az argumentumban megadottra.
     * @param s Az Ăşj stratĂ©gia
     */
    public void ChangeBearStrategy(BearAttackStrategy s){
        bearattackstrat = s;
    }

    /**
     * Ez a metĂłdus reprezentĂˇlja a medvetĂˇmadĂˇst. MeghĂ­vja a tĂˇrolt
     * BearAttackStrategy execute() metĂłdusĂˇt, Ăˇtadva a characters tagvĂˇltozĂł referenciĂˇjĂˇt.
     */
    public void BearAttack() {
        bearattackstrat.execute(characters);
    }

    public FieldAppearance getAppearance() {
        FieldAppearance res = new FieldAppearance();
        res.hasSnow = (SnowCount > 0);
        res.hasItem = (item != null);
        res.hasIgloo = (snowstormstrat instanceof SnowstormStrategyIgloo);
        res.hasTent = (snowstormstrat instanceof SnowstormStrategyTent);

        if(!characters.isEmpty()) {
            for(character.Character c : characters) {
                if(c instanceof Eskimo)
                    res.hasEskimo = true;
                if(c instanceof Researcher)
                    res.hasResearcher = true;
                if(c instanceof PolarBear)
                    res.hasBear = true;
            }
        }

        return res;
    }

    public boolean hasItem() {
        return (item != null);
    }

    public void setItem(Item i) { this.item = i; }
}
