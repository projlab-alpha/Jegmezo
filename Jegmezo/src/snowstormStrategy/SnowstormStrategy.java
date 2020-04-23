package snowstormStrategy;

import java.util.ArrayList;

/**
 * Ez az interfész adja meg, hogy milyen metódusokat kell megvalósítaniuk a hóvihar stratégiáknak.
 */
public interface SnowstormStrategy {

    /**
     * Végrehajtja a stratégia lefutásakor szükséges feladatokat.
     * @param chars Az érintett karakterek listája
     */
    void execute(ArrayList<character.Character> chars);
}
