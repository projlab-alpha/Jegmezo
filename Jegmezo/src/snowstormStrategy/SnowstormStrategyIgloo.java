package snowstormStrategy;

import control.Skeleton;

import java.util.ArrayList;

/**
 * A hóvihar stratégia, abban az esetben, amikor van iglu a kérdéses mezőn
 */
public class SnowstormStrategyIgloo implements SnowstormStrategy { //TODO: Hiányos a dokumentációban

    /**
     * (nem csökken a mezőn lévő karakterek testhője)
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<character.Character> chars) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "execute()");
    }
}
