package character;

import control.Direction;

/**
 * Ez az osztály a játék egyik főbb veszélyforrását, a jegesmedvét reprezentálja, aki egy nem
 * játékos által irányított karakter. Az osztály a kompatibilitás érdekében a karakter
 * leszármazottja, de mivel a jegesmedve kevesebbre képes mint a játszható karakterek, ezért az
 * ősosztályának több függvényét felülírja.
 */
public class PolarBear extends Character {

    /**
     *A metódus érdemi működés nélkül visszatér.
     * A medvét nem bántja a hóvihar, ezért ezt a metódust felülírja, úgy, hogy ne csináljon
     * semmit.
     * @param i Használatlan.
     */ 
    @Override
    public void ChangeWarmth(int i) {
        return;
    }

    /**
     *  A metódus érdemi működés nélkül visszatér. A medve
     * tud úszni, a vízbe esés nem befolyásolja, ezért ezt a metódust felülírja, úgy, hogy ne
     * csináljon semmit.
     */
    @Override
    public void FellInWater() {
        return;
    }

    /**
     * Override. Működése megegyezik a Charakterben leírtakkal,
     * azzal kiegészítve, hogy mozgás után a metódus meghívja a lépett mező BearAttack()
     * metódusát.
     * @param d A mozgás iránya
     */
    @Override
    public void Move(Direction d) {
        super.Move(d);
        this.field.BearAttack();
    }

    /**
     * A metódus érdemi működés nélkül visszatér -1 -el
     * @param d Használatlan.
     * @return -1
     */
    @Override
    public int UseAbility(Direction d) {
        return -1;
    }
}
