package item;

import control.Control;
import control.Skeleton;

/**
 * A játék megnyeréséhez szükséges tárgyat lehet belőle összeépíteni,
 * ha a másik kettő tárgy, a jelzőfény és a patron is azon a mezőn vannak,
 * játékosok birtokában, nem pedig odafagyva.
 * Nem szükséges, hogy minden tárgy ugyanannál a játékosnál legyen.
 */
public class Pistol implements Item {

    /**
     * Használatkor ellenőrzi, hogy a tárgy másik két társa
     * azon a mezőn van-e amin ő, és amennyiben igen akkor a játék véget ér.
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    @Override
    public boolean UseItem(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseItem()");
        c.getField().CheckFlareGun();
        boolean res = Skeleton.askQuestion("Sikerult osszerakni a jelzopisztolyt?");
        if(res) {
            Control.getInstance().Win();
            return true;
        } else return false;
    }
}
