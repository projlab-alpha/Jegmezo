package character;

import control.Direction;

/**
 * Ez az osztály reprezentálja az egyik játszható karaktert, a sarkkutatót. Leszármazottja a
 * Character osztálynak, megvalósítja a UseAbility metódusát.
 */
public class Researcher extends Character {

    /**
     * Beállítja a testhőt 4-re.
     */
    public Researcher() {
        warmth = 4;
    }

    /**
     * Használja a sarkkutató különleges képességét, mely
     * során az argumentumban megadott irányban lévő szomszédos jégmezőről
     * megállapítja, hány karaktert bír el átfordulás nélkül. Ehhez meghívja a jelenlegi mező
     * FindCapacity metódusát, átadva neki a d Direction-t, eggyel csö kkenti a sarkkutató
     * hátralévő munkáinak számát., majd visszatér a kapott értékkel.
     * @param d Az irány amerre használja képességét.
     * @return A kutatott tábla kapacitása.
     */
    @Override
    public int UseAbility(Direction d) {
        --actionpoint;
        return field.FindCapacity(d);
    }
}
