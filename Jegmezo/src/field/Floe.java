package field;

import item.Item;

/**
 * Ez az osztály reprezentálja az egyszerű, stabil jégmezőket.
 * Leszármazottja az AbstractFieldnek, implementálja az absztrakt metódusait,
 * és nagyban ugyanazokat a feladatokat látja el.
 */
public class Floe extends AbstractField {

    /**
     * Beállítja a jégtáblába fagyott tárgyat, a tábla kapacitását, és a táblán lévő hó mennyiségét.
     * @param item      a jégtáblába fagyott tárgy
     * @param capacity  a tábla kapacitása
     * @param snowcount a táblán lévő hó mennyisége
     */
    public Floe(Item item, int capacity, int snowcount) {
        super(item, capacity, snowcount);
    }

    /**
     * Elfogadja az argumentumban kapott karaktert. A mező
     * characters kollekciójához hozzáadja az argumentumban kapott karaktert, és meghívja
     * a karakter SetField() metódusát, az önmagára mutató referenciát átadva
     * argumentumként.
     * @param c Az átvett karakter
     */
    @Override
    public void Accept(character.Character c) {
        this.characters.add(c);
        c.setField(this);
    }
}
