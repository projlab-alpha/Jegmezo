package item;

import control.Skeleton;

/**
 * Segít kihúzni egy vízbeesett karaktert.
 */
public class Rope implements Item { //TODO: Dokumentációban rossz a leírás
    /**
     * Konstruktor
     */
    public Rope() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
    }

    /**
     * Kimenti c karaktert
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        c.getField().Rescue();
        return true;
    }
}
