package character;

import control.Control;
import control.Direction;
import field.AbstractField;
import item.Item;
import waterStrategy.WaterStrategy;
import waterStrategy.WaterStrategyDefault;

import java.util.ArrayList;

/**
 * Ez az absztrakt osztály reprezentálja a pályán lévő karaktereket. Felelős a karakterek főbb
 * tulajdonságainak tárolásáért és kezeléséért, a velük kapcsolatos parancsok (pl. mozgás) és
 * egyéb események (pl. vízbe esés) végrehajtásáért.
 */
public abstract class Character {

    /**
     * A karakter teshőjének szintje.
     */
    protected int warmth;       //TODO: dokumentációban private helyett protected kellene

    /**
     * A karakter maradék munkáinak száma.
     */
    protected int actionpoint;      //TODO: dokumentációban private helyett protected kellene

    /**
     * Jelzi, hogy fuldoklik-e éppen a karakter.
     */
    private boolean drowning;

    /**
     * A karakter tárgyainak listája.
     */
    private ArrayList<Item> inventory;

    /**
     * Referencia arra mezőre, amelyen a karakter jelenleg áll.
     */
    protected AbstractField field;      //TODO: dokumentációban private helyett protected kellene

    /**
     * A stratégia, amely végrehajtódik vízbe eséskor.
     */
    private WaterStrategy waterstrat;

    /**
     * Konstruktor.
     */
    public Character() {
        actionpoint = 4;
        warmth = 0;
        drowning = false;
        inventory = new ArrayList<>();
        waterstrat = new WaterStrategyDefault();
    }

    /**
     * Megkísérli mozgatni a karaktert az adott irányba, úgy, hogy
     * meghívja a jelenlegi mező MoveChar metódusát. Argumentumnak a d Direction-t, és a
     * saját magára mutató referenciát adja át. Mozgás után csökkenti a karakter hátralévő
     * munkáinak számát.
     * @param d A mozgás iránya
     */
    public void Move(Direction d){
        if(actionpoint > 0 && field.MoveChar(d, this))
            --actionpoint;
    }

    /**
     * Meghíváskor eggyel csökkenti a jelenlegi mezőn lévő hó mennyiségét,
     * úgy, hogy meghívja a jelenlegi mező ChangeSnow metódusát (-1)-es argumentummal,
     * majd csökkenti a karakter hátralévő munkáinak számát.
     */
    public void Dig(){
        if(actionpoint > 0) {
            this.field.ChangeSnow(-1);
            --actionpoint;
        }
    }

    /**
     * Felveszi a jelenlegi mezőn lévő tárgyat, ha van rajta, a mező
     * RequestItem metódusával, majd, ha sikerült tárgyat felvenni, csökkenti a karakter
     * hátralévő munkáinak számát.
     */
    public void PickUpItem(){
        if(actionpoint > 0 && inventory.size() < 9) {
            Item i = field.RequestItem();
            if (i != null) {
                inventory.add(i);
                --actionpoint;
            }
        }
    }

    /**
     * Használja az Inventory-ban az argumentumban megadott helyen
     * álló tárgyat (meghívja a tárgy UseItem metódusát, argumentumként az önmagára
     * mutató referenciát megadva), és eggyel csökkenti a karakter hátralévő munkáinak
     * számát, ha sikerült használni tárgyat. Ha a UseItem igazzal tér vissza, akkor az
     * esedékes tárgyat ez a metódus megsemmisíti.
     * @param n A használni kivánt tárgy indexe az inventory-ban
     */
    public void UseItem(int n){
        if(actionpoint > 0 && n < inventory.size()) {
            if (this.inventory.get(n).UseItem(this))
                this.inventory.remove(n);
            --actionpoint;
        }
    }

    /**
     * Hozzáadja az argumentumban megadott értéket a
     * karakter testhőjéhez. Ha a változtatás után a testhő nulla vagy kisebb, akkor a Control
     * CharacterDied() metódusának meghívásával jelzi, hogy a karakter meghalt.
     * @param i ennyivel változtatja a testhőt
     */
    public void ChangeWarmth(int i){
        warmth += i;
        if (warmth <= 0)
            Control.getInstance().CharacterDied();
    }

    /**
     * Absztrakt metódus, használja a karakter különleges
     * képességét az adott irányban.
     * @param d Az irány amerre használja képességét.
     * @return A képesség használatának eredménye; a konkrét képességtől függ, mi ez.
     */
    public abstract int UseAbility(Direction d);

    public void resetAP() { actionpoint = 4; }

    /**
     * Ez a metódus reprezentálja a vízbe esést. Meghívja a tárolt
     * WaterStrategy execute() metódusát, átadva az önmagára mutató referenciát.
     */
    public void FellInWater(){
        waterstrat.execute(this);
    }

    /**
     * Ez a metódus reprezentálja a fulladást. Beállítja a megfelelő flag-et,
     * majd lenullázza a karakter hátralévő munkáinak számát.
     */
    public void Drown() {
        drowning = true;
        actionpoint = 0;
    }

    /**
     * Ezzel a metódussal lehet jelezni, hogy a karaktert kimentették a
     * vízből. Hamisra állítja a drowning tagváltozó értékét.
     */
    public void Rescued() {
        drowning = false;
    }

    /**
     * Visszatér a drowning tagváltozó jelenlegi értékével.
     * @return Drowning?
     */
    public boolean isDrowning() {
        return drowning;
    }

    /**
     * Megváltoztatja a vízbe eséskor
     * végrehajtandó stratégiát az argumentumban megadottra.
     * @param w az új stratégia
     */
    public void ChangeStrategy(WaterStrategy w){
        waterstrat = w;
    }

    /**
     *  Ha a kért nevű item megtalálható a karakter invetoryjában, akkor igazzal tér vissza, különben hammissal.
     * @param s a kérdéses Item neve
     * @return Igaz, ha megtalálható az Item az Inventory-ban, különben hamis
     */
    public boolean HasItem(String s){
        for (Item i : inventory) {
            if (i.getClass().getSimpleName().equalsIgnoreCase(s))
                return true;
        } 
        return false;
    }

    /**
     * Visszatér a field tagváltozóban tárolt referenciával.
     * @return A mező, amin a karakter áll
     */
    public AbstractField getField() {
        return this.field;
    }

    /**
     * Beállítja a karakter mezőjét (field tagváltozó) az
     * argumentumban kapottra.
     * @param f A mező, amin a karakter állni fog.
     */
    public void setField(AbstractField f) {
        this.field = f;
    }

    public ArrayList<Item> getInventory() { return inventory; }

    public int getWarmth() { return warmth; }
    public int getAP() { return actionpoint; }
}
