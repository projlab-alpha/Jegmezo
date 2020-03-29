package waterStrategy;

import control.Skeleton;

/**
 * A vízbeesési stratégia búvárruha nélkül
 */
public class WaterStrategyDefault implements WaterStrategy {

    /**
     * A karakter elkezd fuldokolni,
     * ha legközelebb rákerül a sor a játékban megfullad és a játék véget ér
     * @param c Az érintett karakter
     */
    @Override
    public void execute(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "execute()");
        c.Drown();
    }
}
