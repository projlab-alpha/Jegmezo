package item;

import control.Skeleton;
import waterStrategy.WaterStrategySuit;

/**
 * Túlélővé teszi a karaktert a vízbe fulladással szemben.
 */
public class Divingsuit implements Item {
    /**
     * Konstruktor
     */
    public Divingsuit() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
    }

    /**
     * Ráadja a búvárruhát a c karakterre
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        WaterStrategySuit wss = new WaterStrategySuit();
        c.ChangeStrategy(wss);
        return true;
    }
}
