package item;

/**
 * Ez az osztály ásót reprezentál, ami egy olyan használható tárgy, amely használatkor csökkenti
 * a használó karakter alatti mezőn lévő hó mennyiségét.
 */
public class Shovel implements Item {

    /**
     * Meghívja az argumentumban kapott karakter GetField()
     * metódusát, majd a kapott mező ChangeSnow() metódusát, argumentumként -2-t
     * átadva, majd hamissal tér vissza.
     * @param c A karakter aki használja
     * @return False
     */
    @Override
    public boolean UseItem(character.Character c) {
        c.getField().ChangeSnow(-2);
        return false;
    }



}
 