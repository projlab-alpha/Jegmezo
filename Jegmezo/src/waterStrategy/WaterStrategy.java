package waterStrategy;

/**
 * Ez az interfész adja meg, hogy milyen metódusokat kell megvalósítaniuk a vízbeesési
 * stratégiáknak.
 */
public interface WaterStrategy {

    /**
     * Végrehajtja a stratégia lefutásakor szükséges feladatokat.
     * @param c Az érintett karakter
     */
    void execute(character.Character c);
}
