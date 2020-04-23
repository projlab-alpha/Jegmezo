package waterStrategy;

/**
 * Ez a vízbeesési stratégia reprezentálja azt az esetet, amikor egy búvár ruhát viselő karakter
 * esik vízbe. Ebben az esetben a karakterrel nem történik semmi; a normálistól nem eltérő
 * módon mozoghat, cselekedhet tovább.
 */
public class WaterStrategySuit implements WaterStrategy {

    /**
     * A metódus érdemi működés nélkül visszatér.
     * @param c Az érintett karakter
     */
    @Override
    public void execute(character.Character c) {
        return;
    }
}
