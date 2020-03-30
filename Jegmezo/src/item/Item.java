package item;

/**
 * A különböző tárgyakat reprezentáló osztályok interfésze.
 */
public interface Item {

    /**
     * A tárgyak használata, minden tárgy mást csinál
     * @param c A karakter aki használja
     * @return El kell tűnnie-e az itemnek használat után
     */
    boolean UseItem(character.Character c);
}
