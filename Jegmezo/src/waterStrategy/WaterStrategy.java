package waterStrategy;

/**
 * A vízbeesési stratégiákat megkötő interfész
 */
public interface WaterStrategy {

    /**
     * A megvalósítandó metódus
     * @param c Az érintett karakter
     */
    void execute(character.Character c);
}
