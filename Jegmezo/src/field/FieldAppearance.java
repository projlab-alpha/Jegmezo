package field;

/**
 * Egyszerű adatstruktúra a getterek számának csökkentése érdekében,
 * a DisplayTile használja a mezők státuszának lekérdezésére.
 */
public class FieldAppearance {
    /**
     * Lyukas-e a mező.
     */
    public boolean isHole = false;

    /**
     * Instabil-e a mező.
     */
    public boolean isUnstable = false;

    /**
     * Van-e a mezőn hó.
     */
    public boolean hasSnow = false;

    /**
     * Van-e a mezőn tárgy.
     */
    public boolean hasItem = false;

    /**
     * Van-e a mezőn eszkimó.
     */
    public boolean hasEskimo = false;

    /**
     * Van-e a mezőn sarkkutató.
     */
    public boolean hasResearcher = false;

    /**
     * Van-e a mezőn jegesmedve.
     */
    public boolean hasBear = false;

    /**
     * Van-e a mezőn iglu.
     */
    public boolean hasIgloo = false;

    /**
     * Van-e a mezőn sátor.
     */
    public boolean hasTent = false;
}
