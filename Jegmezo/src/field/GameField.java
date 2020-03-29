package field;

import control.Skeleton;

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

    public GameField() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
        floes = new ArrayList<>();
    }

    public void addField(AbstractField f) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "addField()");
        floes.add(f);
    }

    /**
     * Kiválaszt néhány jégtáblát, és azokon megnöveli a hó
     * mennyiségét egy egységgel, valamint a kiválasztott mezőn lévő játékosok testhőjét
     * csökkenti egy egységgel vagy változatlanul hagyja, annak függvényében, hogy
     * az adott mező milyen tulajdonságokkal rendelkezik
     */
    public void SnowStorm(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "SnowStorm()");
        floes.get(0).SnowStormHit();        //Egyelőre mindig ugyanarra az egy mezőre jut el a jégvihar, késöbb véletlen darab mezőre
    }
}
