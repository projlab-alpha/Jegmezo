package snowstormStrategy;

import java.util.ArrayList;

/**
 * A hóvihar stratégiákat megkötő interfész.
 */
public interface SnowstormStrategy {

    /**
     * A hóviharkor bekövetkező stratégiát végrehajtó metódus
     * @param chars Az érintett karakterek listája
     */
    public void execute(ArrayList<Character> chars);
}
