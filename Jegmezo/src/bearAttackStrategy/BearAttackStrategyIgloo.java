package bearAttackStrategy;

import character.Character;

import java.util.ArrayList;

/**
 * Ez a medvetámadási stratégia reprezentálja azt az esetet, amikor egy olyan mezőn támad medve,
 * amelyen van iglu. Ebben az esetben a medve nem csinál semmit.
 */
public class BearAttackStrategyIgloo implements BearAttackStrategy {
    /**
     * A metódus érdemi működés nélkül visszatér.
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<Character> chars) {
        return;
    }
}
