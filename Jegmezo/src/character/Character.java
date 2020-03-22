package character;

import control.Direction;
import field.AbstractField;
import item.Item;
import waterStrategy.WaterStrategy;

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
    private Item inventory;

    /**
     * Megmutatja, hogy mi történik a karakterrel vízbeeséskor
     */
    private WaterStrategy waterstrat;

    /**
     * A mező ahol a karakter áll
     */
    private AbstractField field;

    /**
     * A karakter a d irányban lévő jégtáblára mozog
     * @param d A mozgás iránya
     */
    public void Move(Direction d){

    }

    /**
     * A karakter eltakarít egy egység havat a jégtábláról, amin áll
     */
    public void Dig(){

    }

    /**
     * A karakter felvesz egy tárgyat
     */
    public void PickUpItem(){

    }

    /**
     * A karakter összeépíti a jelzőrakétát
     */
    public void BuildFlareGun(){

    }

    /**
     * A karakter használ egy nála lévő tárgyat
     * @param n //TODO: Mi az n?
     */
    public void UseItem(int n){

    }

    /**
     * Megváltoztatja a karakter testhőjét
     * @param i ennyivel változtatja a testhőt
     */
    public void ChangeWarmth(int i){

    }

    /**
     * A karakter használja a speciális képességét
     * @param d Az irány amerre használja képességét.
     * @return //TODO: Milyen számot ad vissza?
     */
    public int UseAbility(Direction d){ //TODO: Mire gondoltatok? (Java-ban minden virtual alapból)

    }

    /**
     * A megfelelő Waterstrat alapján kezeli a karakter vízbeesését attól függően,
     * hogy van-e rajta búvárruha
     */
    public void FellInWater(){

    }

    /**
     * Beállítja a játékost fuldokló állapotba
     */
    public void Drown(){

    }

    /**
     * Megváltoztatja a waterstrategyt,
     * lényegében a búvárruha felvételét hivatott reprezentálni
     * @param w az új stratégia
     */
    public void ChangeStrategy(WaterStrategy w){

    }

    /**
     * Megmutatja, hogy a karakter inventoryjában van-e s Item
     * @param s a kérdéses Item
     * @return Van-e
     */
    public boolean HasItem(String s){

    }
}
