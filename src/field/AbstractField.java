package field;

import control.Direction;
import item.Item;
import snowstormStrategy.SnowstormStrategy;

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
    private HashMap<Direction, AbstractField> neighbours; //TODO: szerintem így jobb, de írjátok át nyugodtan.

    /**
     * A jégtáblán tartózkodó karakterek listája.
     */
    private ArrayList<Character> characters;

    /**
     * Megmutatja, milyen módon kell az adott mezőn eljárni hóvihar esetén
     */
    private SnowstormStrategy snowstormstrat;

    /**
     * A jégtáblába fagyott tárgy
     */
    protected Item item;

    /**
     * Átvesz egy karaktert egy másik mezőről
     * @param c Az átvett karakter
     */
    public void Accept(Character c){ //TODO: Mire gondoltatok? (Java-ban minden virtual alapból)

    }

    /**
     * Elmozgat egy c karaktert a d irányban lévő jégtáblára
     * @param d Mozgatás iránya
     * @param c Mozgatott karakter
     */
    public void MoveChar(Direction d, Character c){

    }

    /**
     * Bealítja a d irányban lévő jégtáblát
     * @param d A beállítandó irány
     * @param neighbour A beállított jégtábla
     */
    public void setNeighbour(Direction d, AbstractField neighbour) {
        neighbours.put(d, neighbour);
    }

    /**
     * Megváltoztatja a hó menyiségét a jégtáblán
     * @param i
     */
    public void ChangeSnow(int i){

    }

    /**
     * Épít egy iglut a jégtáblára
     * @return az építés sikeressége
     */
    public boolean BuildIgloo(){ //TODO: Nincs osztálydiagramon

    }

    /**
     * Megvizsgálja, hogy a karakternek van-e szükséges tárgya
     * @return A karakter tárgya
     */
    public Item RequestItem(){

    }

    /**
     * Visszatér a jégtábla teherbírásával
     * @param d A keresett irány
     * @return A jégtábla kapacitása
     */
    public int FindCapacity(Direction d){

    }

    /**
     * Eléri a jégtáblát egy vihar
     */
    public void SnowStormHit(){

    }

    /**
     * Megvizsgálja,
     * hogy a jégtáblán lévő karaktereknél van-e az összes jelzőrakéta alkatrész
     * @return A jégtáblán van-e
     */
    public boolean CheckFlareGun(){

    }

    /**
     * Kiment egy bajba jutott játékost
     */
    public void Rescue(){

    }

    /**
     * Kiment egy bajba jutott játékost
     * @param d a mentés iránya
     */
    public void RescueChars(Direction d){ //TODO: Nincs dokumentumba specifikálva.

    }

    /**
     * Megváltoztatja a viharkor bekövetkező eseményeket
     * @param s Az új viharkor bekövetkező esemény
     */
    public void ChangeStrategy(SnowstormStrategy s){

    }

}
