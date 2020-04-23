package snowstormStrategy;

import character.Character;

import java.util.ArrayList;

/**
 * Ez a hóvihar stratégia reprezentálja azt az esetet, amikor egy olyan mezőt érint hóvihar,
 * amelyen sátor van. Ebben az esetben a karakterekkel nem történik semmi.
 * Megkülönböztetendő az iglutól, hiszen a sátor egy forduló után eltűnik.
 */
public class SnowstormStrategyTent implements SnowstormStrategy {
    /**
     * A metódus érdemi működés nélkül visszatér.
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<Character> chars) {
        return;
    }
}
