package character;

import control.Direction;
import control.Skeleton;

/**
 * Egy játszható karakter,
 * felülírja az ősosztálya UseAbility metódusát úgy,
 * hogy meg tudja vizsgálni a szomszédos mező teherbírását.
 */
public class Researcher extends Character {

    /**
     * Megvizsgálja a szomszédos mező teherbírását
     * @param d Az irány amerre használja képességét.
     * @return A kutatott tábla kapacitása
     */
    @Override
    public int UseAbility(Direction d) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseAbility()");
        return this.field.FindCapacity(d);
    }
}
