package item;

import control.Skeleton;

/**
 * Megnöveli eggyel a játékos testhőjét, majd eltűnik az a birtokából.
 */
public class Food implements Item {

    /**
     * Megnöveli a c karakter testhőjét eggyel
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        c.ChangeWarmth(1);
        return true;
    }
}
