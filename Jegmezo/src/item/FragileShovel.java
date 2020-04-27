package item;

/**
 * Ez az osztály törékeny ásót reprezentál. Hasonlóan működik a normális ásóhóz, azzal a
 * különbséggel, hogy véges sokszor lehet csak használni.
 */
public class FragileShovel extends Shovel {
    /**
     * Megmutatja, hányszor használható még az ásó.
     */
    private int UsesLeft = 3;

    /**
     * Meghívja az ősosztály UseItem metódusát,
     * argumentumként a kapott karaktert átadva, majd csökkenti a UsesLeft tagváltozó értékét eggyel.
     * @param c A karakter aki használja
     * @return Ha a csökkentés után a UsesLeft értéke kissebb, mint egy, akkor true, különben false.
     */
    @Override
    public boolean UseItem(character.Character c) {
        super.UseItem(c);
        return (--UsesLeft <= 0);
    }
 
}
