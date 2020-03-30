package item;

import control.Skeleton;

/**
 * Azonos mennyiségű munkával kettő egység havat takarít el egy mezőről.
 */
public class Shovel implements Item {
    /**
     * Konstruktor
     */
    public Shovel() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
    }

    /**
     * Kettő egység havat eltakarít c karakter mezőjéről
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        Skeleton.indent();
        c.getField().ChangeSnow(-2);
        Skeleton.returned();
        return false;
    }
}
