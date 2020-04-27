package item;

import waterStrategy.WaterStrategySuit;

/**
 * Ez az osztály búvárruhát reprezentál, ami egy olyan használható tárgy, amely használat után
 * megvédi a használó karaktert a vízbe fulladástól.
 */
public class Divingsuit implements Item {

    /**
     * Meghívja az argumentumban kapott karakter
     * ChangeStrategy() metódusát, argumentumként egy új WaterStrategySuit objektumot
     * átadva, majd igazzal tér vissza.
     * @param c A karakter aki használja
     * @return True
     */
    @Override
    public boolean UseItem(character.Character c) {
        c.ChangeStrategy(new WaterStrategySuit());
        return true;
    }


}
