package waterStrategy;

import control.Skeleton;

/**
 * A vízbeesési stratégia bóvárruhában
 */
public class WaterStrategySuit implements WaterStrategy {

    /**
     * A karakter akciópontjai lenullázódnak,
     * de a fulldoklás veszélye nem fenyegeti
     * @param c Az érintett karakter
     */
    @Override
    public void execute(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "execute()");
    }
}
