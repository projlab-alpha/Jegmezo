package snowstormStrategy;

import java.util.ArrayList;

/**
 * Ez az alapértelmezett hóvihar stratégia arra az esetre, ha olyan mezőt érint hóvihar, amelyen
 * nincsen se sátor, se iglu. Csökkenti az érintett karakterek testhőmérsékletét.
 */
public class SnowstormStrategyDefault implements SnowstormStrategy {

    /**
     * Az argumentumban kapott tömbben
     * található karakterre meghívja a karakterek ChangeWarmth() metódusait, -1-et átadva argumentumként.
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<character.Character> chars) {
        for (character.Character c : chars)
            c.ChangeWarmth(-1);
    }
}
