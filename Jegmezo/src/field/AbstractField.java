package field;

import bearAttackStrategy.BearAttackStrategy;
import control.Direction;
import item.Item;
import snowstormStrategy.SnowstormStrategy;
import snowstormStrategy.SnowstormStrategyDefault;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A játékban előforduló mezőket reprezentáló osztályok absztrakt ősosztálya,
 * tárolja a szomszédjait, valamint a karaktereket, akik rajta állnak.
 */
public abstract class AbstractField {

    /**
     * A mezőt fedő hó mennyisége.
     */
    private int SnowCount;

    /**
     * A mező teherbíró képessége, megadja, egyszerre hány karakter fér el
     * rajta vízbe esés veszélye nélkül.
     */
    protected int Capacity; //TODO: dokumentációban private helyett protected kellene

    /**
     * A stratégia, amely meghatározza a működést abban az esetben, ha
     * egy medve lép a mezőre.
     */
    private BearAttackStrategy bearattackstrat;

    /**
     * A stratégia, amely meghatározza a működést abban az esetben, ha
     * a mezőt jégvihar érint.
     */
    private SnowstormStrategy snowstormstrat;

    /**
     * A mezőn található item. Ha a mezőn nincsen item, akkor ennek értéke null.
     */
    private Item item;

    /**
     * Kollekció, amely a mezővel szomszédos mezők referenciáját tárolja, a lehetséges irányokban.
     */
    private HashMap<Direction, AbstractField> neighbours;

    /**
     * Kollekció a mezőn jelenleg elhelyezkedő karakterekről.
     */
    protected ArrayList<character.Character> characters;


    /**
     * Beállítja a jégtáblába fagyott tárgyat, a tábla kapacitását, és a táblán lévő hó mennyiségét.
     * @param item a jégtáblába fagyott tárgy
     * @param capacity a tábla kapacitása
     * @param snowcount a táblán lévő hó mennyisége
     */
    public AbstractField(Item item, int capacity, int snowcount) {
        this.item = item;
        this.Capacity = capacity;
        this.SnowCount = snowcount;
        neighbours = new HashMap<>();
        characters = new ArrayList<>();
        snowstormstrat = new SnowstormStrategyDefault();
    }

    /**
     * Absztrakt metódus, elfogad egy karaktert, amely a mezőre próbál mozogni.
     * @param c Az átvett karakter
     */
    public abstract void Accept(character.Character c);

    /**
     * Átlépteti az argumentumként átadott
     * karaktert a megfelelő irányban lévő szomszédos mezőre, úgy, hogy meghívja a
     * szomszédos mező Accept metódusát az átadott karakterrel.
     * @param d A mozgás iránya
     * @param c A mozgatandó karakter
     */
    public void MoveChar(Direction d, character.Character c){
        neighbours.get(d).Accept(c);
    }

    /**
     * Az inicializáláshoz használt
     * metódus, amely elhelyezi a szomszédokat tároló kollekcióban az argumentumként
     * megadott mezőt a megadott iránnyal párosítva.
     * @param d A szomszéd iránya
     * @param neighbour a szomszédos mező
     */
    public void setNeighbour(Direction d, AbstractField neighbour) {
        neighbours.put(d, neighbour);
    }

    /**
     *  Hozzáadja az argumentumban megadott értéket a mezőt
     * fedő hó mennyiségéhez. A hó mennyisége a változtatás után garantáltan nem lesz
     * negatív.
     * @param i A változtatás mennyisége
     */
    public void ChangeSnow(int i){
        SnowCount += i;
        if(SnowCount < 0)
            SnowCount = 0;
    }

    /**
     * Ha a mezőn nincsen item, vagy, ha a mezőt több, mint 0
     * egységnyi hó borítja, akkor a metódus null referenciával tér vissza. Ha a mezőn van
     * item, és 0 egységnyi hó borítja, akkor ideiglenesen tárolja a mezőn lévő item
     * referenciáját, törli a valós referenciát a mező tagváltozójából, majd visszatér a tárolt
     * item referenciájával.
     * @return A táblán lévő tárgy, vagy null, ha nem elérhető
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
     * Visszatér a megadott irányban található szomszédos
     * mező Capacity tagváltozójának jelenlegi értékével.
     * @param d A keresett irány
     * @return A jégtábla kapacitása
     */
    public int FindCapacity(Direction d){
        return neighbours.get(d).Capacity;
    }

    /**
     * Ez a metódus hívódik meg, ha a mezőt jégvihar érint.
     * Meghívja a tárolt SnowStormStrategy execute() metódusát, átadva a characters
     * tagváltozó referenciáját.
     */
    public void SnowStormHit(){
        snowstormstrat.execute(characters);
    }

    /**
     * A characters tagváltozó összes elemére meghívja fejenként
     * háromszor a karakter HasItem() metódusát, sorban “Flare”, “Pistol”, majd “Cartridge”
     * argumentumokkal. Ha, miután az összes karakterre lefutott mindhárom hívás, igaz az,
     * hogy a három típusú hívás közül mindegyik legalább egyszer igazzal tért vissza (tehát
     * például ha volt olyan HasItem(“Flare”) hívás, amely igazzal tért vissza, akkor a
     * “Flare” hívás típusra ez a feltétel teljesült), akkor a metódus igazzal tér vissza,
     * ellenkező esetben hamissal.
     * @return Igaz, ha összerakható a Flaregun, különben hamis
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
     * Meghívja a mező összes szomszédjának RescueChars metódusát.
     * Argumentumként a saját magára mutató referenciát adja át.
     */
    public void Rescue(){
        for (Direction d : Direction.values()) {
            if(neighbours.containsKey(d))
                neighbours.get(d).RescueChars(this);
        }
    }

    /**
     * A characters kollekció összes elemére meghívja
     * először a karakter IsDrowning() metódusát, majd ha ez igazzal tér vissza, akkor
     * meghívja a karakter Rescued() metódusát, majd az argumentumban kapott mező
     * Accept() metódusát, argumentumként az éppen vizsgált karaktert átadva.
     * @param f A mező, ahová a karaktereket menteni kell
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
     * Megváltoztatja a hóvihar érintés
     * esetén végrehajtandó stratégiát az argumentumban megadottra.
     * @param s Az új stratégia
     */
    public void ChangeSnowStrategy(SnowstormStrategy s){
        snowstormstrat = s;
    }

    /**
     * Megváltoztatja a medve
     * támadáskor végrehajtandó stratégiát az argumentumban megadottra.
     * @param s Az új stratégia
     */
    public void ChangeBearStrategy(BearAttackStrategy s){
        bearattackstrat = s;
    }

    /**
     * Ez a metódus reprezentálja a medvetámadást. Meghívja a tárolt
     * BearAttackStrategy execute() metódusát, átadva a characters tagváltozó referenciáját.
     */
    public void BearAttack() {
        bearattackstrat.execute(characters);
    }

    //// PROTOTÍPUS ////



    /**
     * Beállítja a táblán lévő itemet az arg-ban kapott itemre
     * A prototípus debuggolás érdekében
     * @param i beállítandó item
     */
    public void setItem(Item i) {
        this.item = i;
    }

    /**
     * Beállítja a táblát borító hó mennyiségét
     * A prototípus debuggolás érdekébe
     * @param i a beállítandó hó mennyisége
     */
    public void setSnowCount(int i) {
        if(i < 0)
            return;
        this.SnowCount = i;
    }
}
