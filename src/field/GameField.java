package field;

import java.util.ArrayList;

/**
 * Ebben az osztályban tárolja a játék az összes mezőt,
 * és ez az osztály felelős a hóviharokért.
 */
public class GameField {

    /**
     * Heterogén kollekció a mezőkről
     */
    private ArrayList<AbstractField> floes;

    /**
     * Kiválaszt néhány jégtáblát, és azokon megnöveli a hó
     * mennyiségét egy egységgel, valamint a kiválasztott mezőn lévő játékosok testhőjét
     * csökkenti egy egységgel vagy változatlanul hagyja, annak függvényében, hogy
     * az adott mező milyen tulajdonságokkal rendelkezik
     */
    public void SnowStorm(){

    }
}
