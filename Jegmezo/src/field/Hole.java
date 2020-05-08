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
    public Hole(Item item, int snowcount) {     //TODO: Engedjük, hogy lyukas táblán legyen item?
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

    @Override
    public FieldAppearance getAppearance() {
        FieldAppearance res = super.getAppearance();
        res.isHole = true;
        return res;
    }
}
