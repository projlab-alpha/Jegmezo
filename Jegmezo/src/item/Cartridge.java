package item;

/**
 * A játék megnyeréséhez szükséges tárgyat lehet belőle összeépíteni,
 * ha a másik kettő tárgy, a pisztoly, és a patron is azon a mezőn vannak,
 * játékosok birtokában, nem pedig odafagyva.
 * Nem szükséges, hogy minden tárgy ugyanannál a játékosnál legyen
 */
public class Cartridge implements Item {
    /**
     * Használatkor ellenőrzi, hogy a tárgy másik két társa
     * azon a mezőn van-e amin ő és amennyiben igen akkor a játék véget ér.
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(Character c) {
        
    }
}
