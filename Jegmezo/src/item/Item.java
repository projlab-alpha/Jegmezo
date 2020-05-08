package item;

/**
 * Ez az interfész adja meg, hogy milyen függvényeket kell megvalósítaniuk a játékban
 * összeszedhető tárgyaknak.
 */
public interface Item {

    /**
     * Végrehajtja a tárgy használatákor szükséges feladatokat.
     * A visszatérési értéke azt jelzi a használó karakter felé, hogy a tárgy felhasználódott, és
     * ezért meg kell semmisíteni.
     * @param c A karakter aki használja
     * @return El kell tűnnie-e az itemnek használat után
     */
    boolean UseItem(character.Character c);
    
 
}
	
