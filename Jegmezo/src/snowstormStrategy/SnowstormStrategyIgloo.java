package snowstormStrategy;

import java.util.ArrayList;

/**
 * Ez a hóvihar stratégia reprezentálja azt az esetet, amikor egy olyan mezőt érint hóvihar,
 * amelyen van iglu. Ebben az esetben a karakterekkel nem történik semmi.
 */
public class SnowstormStrategyIgloo implements SnowstormStrategy {

    /**
     *  A metódus érdemi működés nélkül visszatér.
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<character.Character> chars) {
        return;
    }
}
