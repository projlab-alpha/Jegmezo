package control;

import field.GameField;

/**
 * Az inicializálásért és inditásért felelős Singleton osztály
 */
public class Control {

    /**
     * Játékosok száma
     */
    private int PlayerCount;
    private GameField gameField;
    /**
     *  Az egyetlen példánya az osztálynak
     */
    private static Control control = null;

    /**
     *  Privát konstruktor a Singleton osztály inicializálásához
     */
    private Control(){}

    /**
     *  Visszatér az osztály egyetlen példányával, ha nincs inicializál egyet
     * @return Az osztály egyetlen példánya
     */
    public static Control getInstance(){
        if (control == null)
            control = new Control();

        return control;
    }

    /**
     * Vesztettek, mert meghalt valaki
     */
    public void CharacterDied(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "CharacterDied()");
    }

    /**
     * Jelzőrakétát sikeresen kilőtték
     */
    public void Win(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Win()");
    }
}