package item;

/**
 * Ez az osztály kötelet reprezentál, ami egy olyan használható tárgy, amely használatkor
 * megkísérel kimenteni minden szomszédos táblán fuldokló karaktert.
 */
public class Rope implements Item {

    /**
     * Meghívja az argumentumban kapott karakter GetField()
     * metódusát, majd a kapott mező Rescue() metódusát, majd hamissal tér vissza.
     * @param c A karakter aki használja
     * @return False
     */
    @Override
    public boolean UseItem(character.Character c) {
        c.getField().Rescue();
        return false;
    }
}
