package field;

import character.Character;
import item.Item;

/**
 * Ez az osztály reprezentálja az instabil jégmezőket. Leszármazottja az AbstractField-nek,
 * implementálja az absztrakt metódusait, viszont képes átfordulni, ha túl sokan állnak rajta. Ha
 * a mező átfordul, az összes rajta lévő karakter vízbe esik.
 */
public class UnstableFloe extends AbstractField {

    /**
     * Beállítja a jégtáblába fagyott tárgyat, a tábla kapacitását, és a táblán lévő hó mennyiségét.
     * @param item      a jégtáblába fagyott tárgy
     * @param capacity  a tábla kapacitása
     * @param snowcount a táblán lévő hó mennyisége
     */
    public UnstableFloe(Item item, int capacity, int snowcount) {
        super(item, capacity, snowcount);
    }

    /**
     * Elfogadja az argumentumban kapott karaktert. A mező
     * characters kollekciójához hozzáadja az argumentumban kapott karaktert, és meghívja
     * a karakter SetField() metódusát, az önmagára mutató referenciát átadva
     * argumentumként. Ha ez után a karaktereket tároló kollekció hossza nagyobb, mint az
     * osztály capacity tagváltozójának értéke, akkor meghívja a saját RollOver() metódusát.
     * @param c Az átvett karakter
     */ 
    @Override
    public void Accept(Character c) {
        this.characters.add(c);
        c.setField(this);
        if(this.characters.size() > this.Capacity)
            RollOver();
    }

    /**
     * Ez a metódus reprezentálja, hogy átbillen a jégtábla. A metódus a
     * characters tagváltozó összes elemére meghívja a karakter FellInWater() metódusát.
     */
    public void RollOver(){
        for (character.Character c : this.characters) {
            c.FellInWater();
        }
    }

    @Override
    public FieldAppearance getAppearance() {
        FieldAppearance res = super.getAppearance();
        res.isUnstable = true;
        return res;
    }
}
