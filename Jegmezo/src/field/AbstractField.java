package field;

import control.Direction;
import control.Skeleton;
import item.Item;
import snowstormStrategy.SnowstormStrategy;
import snowstormStrategy.SnowstormStrategyDefault;
import snowstormStrategy.SnowstormStrategyIgloo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A játékban előforduló mezőket reprezentáló osztályok absztrakt ősosztálya,
 * tárolja a szomszédjait, valamint a karaktereket, akik rajta állnak.
 */
public abstract class AbstractField {

    /**
     * Megmutatja, mennyi egység hó van a jégtáblán
     */
    private int snowCount;

    /**
     * A jégtáblák teherbírása
     */
    private int capacity;

    /**
     * A szomszédokat és irányokat tárolja
     */
    private HashMap<Direction, AbstractField> neighbours;

    /**
     * A jégtáblán tartózkodó karakterek listája.
     */
    protected ArrayList<character.Character> characters;

    /**
     * Megmutatja, milyen módon kell az adott mezőn eljárni hóvihar esetén
     */
    private SnowstormStrategy snowstormstrat;

    /**
     * A jégtáblába fagyott tárgy
     */
    protected Item item;

    /**
     * Konstruktor
     */
    public AbstractField() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
        Skeleton.indent();
        neighbours = new HashMap<>();
        characters = new ArrayList<>();
        snowstormstrat = new SnowstormStrategyDefault();
        Skeleton.returned();
    }

    /**
     * Átvesz egy karaktert egy másik mezőről
     * @param c Az átvett karakter
     */
    public abstract void Accept(character.Character c);

    /**
     * Beállítja, milyen tárgy van a jégtáblába fagyva
     * @param i A beállítandó tárgy
     */
    public void setItem(Item i) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "setItem()");
        item = i;
    }

    /**
     * Elmozgat egy c karaktert a d irányban lévő jégtáblára
     * @param d Mozgatás iránya
     * @param c Mozgatott karakter
     */
    public void MoveChar(Direction d, character.Character c){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "MoveChar()");
        this.neighbours.get(d).Accept(c);
    }

    /**
     * Bealítja a d irányban lévő jégtáblát
     * @param d A beállítandó irány
     * @param neighbour A beállított jégtábla
     */
    public void setNeighbour(Direction d, AbstractField neighbour) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "setNeighbour()");
        neighbours.put(d, neighbour);
    }

    /**
     * Megváltoztatja a hó menyiségét a jégtáblán
     * @param i A változtatás mennyisége
     */
    public void ChangeSnow(int i){
        Skeleton.indent();
        Skeleton.methodCalled(this.getClass().getSimpleName(), "ChangeSnow()");
        Skeleton.returned();
    }

    /**
     * Visszatér a táblán lévő tárgyal
     * @return A táblán lévő tárgy
     */
    public Item RequestItem(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "RequestItem()");
        return this.item;
    }

    /**
     * Visszatér egy szomszédos jégtábla teherbírásával
     * @param d A keresett irány
     * @return A jégtábla kapacitása
     */
    public int FindCapacity(Direction d){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "FindCapacity()");
        return this.neighbours.get(d).capacity;
    }

    /**
     * Eléri a jégtáblát egy vihar
     */
    public void SnowStormHit(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "SnowStormShit()");
        boolean res = Skeleton.askQuestion("Van a mezon iglu?");
        if(res)
            this.snowstormstrat = new SnowstormStrategyIgloo();
        else
            this.snowstormstrat = new SnowstormStrategyDefault();
        this.snowstormstrat.execute(this.characters);
    }

    /**
     * Megvizsgálja,
     * hogy a jégtáblán lévő karaktereknél van-e az összes jelzőrakéta alkatrész
     * @return A jégtáblán van-e
     */
    public boolean CheckFlareGun(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "CheckFlareGun()");
        boolean flare = false, pistol = false, cart = false;
        for (character.Character c : this.characters) {
            if(c.HasItem("Flare")) flare = true;
            if(c.HasItem("Pistol")) pistol = true;
            if(c.HasItem("Cartridge")) cart = true;
        }
        return (flare && pistol && cart);
    }

    /**
     * Kiment egy bajba jutott játékost
     */
    public void Rescue(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Rescue()");
        for (Direction d : Direction.values()) {
            if(this.neighbours.containsKey(d))
                this.neighbours.get(d).RescueChars(d);
        }
    }

    /**
     * Kiment egy bajba jutott játékost
     * @param d a mentés iránya
     */
    public void RescueChars(Direction d){ //TODO: Nincs dokumentumba specifikálva.
        Skeleton.methodCalled(this.getClass().getSimpleName(), "RescueChars()");
        boolean res = Skeleton.askQuestion("Van a szomszedos mezon fulldoklo karakter?");
        if(res) {
            this.characters.get(0).Move(Direction.SOUTH);
        }
    }

    /**
     * Megváltoztatja a viharkor bekövetkező eseményeket
     * @param s Az új viharkor bekövetkező esemény
     */
    public void ChangeStrategy(SnowstormStrategy s){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "ChangeStrategy()");
    }
}
