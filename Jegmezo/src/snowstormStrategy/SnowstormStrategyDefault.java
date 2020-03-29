package snowstormStrategy;

import control.Skeleton;

import java.util.ArrayList;

/**
 * A hóvihar stratégia, abban az esetben, amikor nincsen iglu a kérdéses mezőn
 */
public class SnowstormStrategyDefault implements SnowstormStrategy {
    /**
     * Konstruktor
     */
    public SnowstormStrategyDefault() {
        Skeleton.ctorCalled(this.getClass().getSimpleName());
    }
    /**
     * Az összes listában szereplő karakter
     * testhőjét csökkenti 1 egységgel,
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<character.Character> chars) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "execute()");
        for (character.Character c : chars)
            c.ChangeWarmth(-1);
    }
}
