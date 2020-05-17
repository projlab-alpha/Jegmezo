package field;

import item.Item;

/**
 * Ez az osztály reprezentálja a lyukas jégmezőket. Leszármazottja az AbstractField-nek,
 * implementálja az absztrakt metódusait, viszont azonnal vízbe ejt minden rálépő karaktert.
 */
public class Hole extends AbstractField {
    /**
     * Beállítja a jégtáblába fagyott tárgyat, a tábla kapacitását, és a táblán lévő hó mennyiségét.
     *
     * @param item      a jégtáblába fagyott tárgy
     * @param snowcount a táblán lévő hó mennyisége
     */
    public Hole(Item item, int snowcount) {
        super(item, 0, snowcount);
    }

    /**
     * Elfogadja az argumentumban kapott karaktert. A mező
     * characters kollekciójához hozzáadja az argumentumban kapott karaktert, és meghívja
     * a karakter SetField() metódusát, az önmagára mutató referenciát átadva
     * argumentumként, majd meghívja a karakter FellInWater() metódusát.
     * @param c Az átvett karakter
     */
    @Override
    public void Accept(character.Character c) {
        this.characters.add(c);
        c.setField(this);
        c.FellInWater();
    }

    /**
     * Létrehoz egy FieldAppearance objektumot, és beállítja a flag-eit a mező státusza szerint,
     * majd visszatér vele. A FieldAppearance-ban az isUnstable hamis, az isHole igaz lesz.
     * @return A mező megjelenését tartalmazó FieldAppearance.
     */
    @Override
    public FieldAppearance getAppearance() {
        FieldAppearance res = super.getAppearance();
        res.isHole = true;
        res.isUnstable = false;
        return res;
    }
}
