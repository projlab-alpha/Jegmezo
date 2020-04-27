package item;

import control.Control;

/**
 * A játék megnyeréséhez szükséges három tárgy egyike. Használtkor felelős a győzelmi
 * kondíció vizsgálatáért, és a győzelem jelzéséért, ha bizonyos feltételek teljesülnek.
 */
public class Pistol implements Item {

    /**
     * Ellenőrzi, hogy van-e lehetőség a játék megnyerésére.
     * Ehhez először meghívja az argumentumban kapott karakter GetField() metódusát,
     * majd a kapott mező CheckFlareGun() metódusát. Ha ez utóbbi igazzal tér vissza,
     * akkor a Control Win() metódusának meghívásával jelzi, hogy a játék győzelemmel
     * zárult. A feladatai elvégzése után hamissal tér vissza.
     * @param c A karakter aki használja
     * @return False
     */
    @Override
    public boolean UseItem(character.Character c) {
        if(c.getField().CheckFlareGun())
            Control.getInstance().Win();
        return false;
    }


}

