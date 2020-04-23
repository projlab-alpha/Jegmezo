package bearAttackStrategy;

import character.Character;

import java.util.ArrayList;

/**
 * Ez az interfész adja meg, hogy milyen metódusokat kell megvalósítaniuk a medvetámadás
 * stratégiáknak.
 */
public interface BearAttackStrategy {
    /**
     * Végrehajtja a stratégia lefutásakor
     * szükséges feladatokat.
     * @param chars Az érintett karakterek listája
     */
    void execute(ArrayList<Character> chars);
}
