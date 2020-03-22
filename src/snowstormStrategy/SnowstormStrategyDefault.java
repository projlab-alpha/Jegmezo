package snowstormStrategy;

import java.util.ArrayList;

/**
 * A hóvihar stratégia, abban az esetben, amikor nincsen iglu a kérdéses mezőn
 */
public class SnowstormStrategyDefault implements SnowstormStrategy {

    /**
     * Az összes listában szereplő karakter
     * testhőjét csökkenti 1 egységgel,
     * a mezőn lévő havat pedig növeli eggyel
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<Character> chars) {

    }
}
