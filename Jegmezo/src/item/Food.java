package item;

/**
 * Ez az osztály ételt reprezentál, ami egy olyan használható tárgy, amely használatkor növeli a
 * használó karakter testhőjét.
 */
public class Food implements Item {

    /**
     * Meghívja az argumentumban kapott karakter
     * ChangeWarmth() metódusát, argumentumként 1-et átadva, majd igazzal tér vissza.
     * @param c A karakter aki használja
     * @return True
     */
    @Override
    public boolean UseItem(character.Character c) {
        c.ChangeWarmth(1);
        return true;
    }


}
