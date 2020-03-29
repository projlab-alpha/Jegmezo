package character;

import control.Control;
import control.Direction;
import control.Skeleton;
import field.AbstractField;
import item.Item;
import waterStrategy.WaterStrategy;
import waterStrategy.WaterStrategyDefault;
import waterStrategy.WaterStrategySuit;

import java.util.ArrayList;

/**
 * A character osztály az ősosztálya a játszható karaktereknek.
 */
public abstract class Character {

    /**
     * A karakterek testhője
     */
    private int warmth;

    /**
     * A karakter képességpontjai, mutatja, hogy még mennyi munkát végezhet
     */
    private int actionpoint;

    /**
     * A karekter éppen fuldoklik-e
     */
    private boolean drowning;

    /**
     * A karakter tárgyainak listája
     */
    private ArrayList<Item> inventory;

    /**
     * Megmutatja, hogy mi történik a karakterrel vízbeeséskor
     */
    private WaterStrategy waterstrat;

    /**
     * A mező ahol a karakter áll
     */
    protected AbstractField field;

    /**
     * Konstruktor
     */
    public Character() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
        actionpoint = 5;
        warmth = 5;
        drowning = false;
        inventory = new ArrayList<>();
        waterstrat = new WaterStrategyDefault();
    }

    /**
     * Visszatér a mezővel, amin a karakter jelenleg áll
     * @return A mező, amin a karakter áll
     */
    public AbstractField getField() {
        return this.field;
    }

    /**
     * Beállítja a mezőt, amin a karakter áll
     * @param f A mező, amin a karakter áll
     */
    public void setField(AbstractField f) { this.field = f; }

    /**
     * Hozzáad egy itemet a karakter inventory-ához
     * @param i A hozzáadandó item
     */
    public void addItem(Item i) { this.inventory.add(i); }
    /**
     * A karakter a d irányban lévő jégtáblára mozog
     * @param d A mozgás iránya
     */
    public void Move(Direction d){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Move()");
        this.field.MoveChar(d, this);
    }

    /**
     * A karakter eltakarít egy egység havat a jégtábláról, amin áll
     */
    public void Dig(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Dig()");
        this.field.ChangeSnow(-1);
    }

    /**
     * A karakter felvesz egy tárgyat
     */
    public void PickUpItem(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "PickUpItem()");
        Item i = this.field.RequestItem();
        if (i != null) {
            this.inventory.add(i);
        }
    }

    /**
     * A karakter használ egy nála lévő tárgyat
     * @param n A használni kivánt tárgy indexe az inventory-ban
     */
    public void UseItem(int n){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        this.inventory.get(n).UseItem(this);
    }

    /**
     * Megváltoztatja a karakter testhőjét
     * @param i ennyivel változtatja a testhőt
     */
    public void ChangeWarmth(int i){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "ChangeWarmth()");
        boolean res = Skeleton.askQuestion("Elfogyott a karakter melegsége?");
        if(res)
            Control.getInstance().CharacterDied();
    }

    /**
     * A karakter használja a speciális képességét
     * @param d Az irány amerre használja képességét.
     * @return a képesség használatának eredménye; a konkrét képességtől függ, mi ez
     */
    public abstract int UseAbility(Direction d);

    /**
     * A megfelelő Waterstrat alapján kezeli a karakter vízbeesését attól függően,
     * hogy van-e rajta búvárruha
     */
    public void FellInWater(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "FellInWater()");
        boolean res = Skeleton.askQuestion("Van a karakteren buvarruha?");
        if(res)
            this.waterstrat = new WaterStrategySuit();
        else
            this.waterstrat = new WaterStrategyDefault();
        this.waterstrat.execute(this);
    }

    /**
     * Beállítja a játékost fuldokló állapotba
     */
    public void Drown(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Drown()");
    }

    /**
     * Megváltoztatja a waterstrategy-t,
     * lényegében a búvárruha felvételét hivatott reprezentálni
     * @param w az új stratégia
     */
    public void ChangeStrategy(WaterStrategy w){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "ChangeStrategy()");
    }

    /**
     * Megmutatja, hogy a karakter inventoryjában van-e s Item
     * @param s a kérdéses Item
     * @return Van-e
     */
    public boolean HasItem(String s){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "HasItem()");
        return true;
    }
}
