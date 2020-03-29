package item;

/**
 * A különböző tárgyakat reprezentáló osztályok interfésze.
 */
public interface Item {

    /**
     * A tárgyak használata, minden tárgy mást csinál
     * @param c A karakter aki használja
     * @return Sikeres-e a használat.
     */
    boolean UseItem(character.Character c);
}
