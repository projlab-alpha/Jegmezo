package item;

import character.Character;
import snowstormStrategy.SnowstormStrategyTent;

/**
 * Ez az osztály felcsavart sátrat reprezentál, ami egy olyan használható tárgy, amely
 * használatkor egy sátrat helyez el karakter alatti mezőn, megvédve a mezőn álló karaktereket a
 * hóvihartól, de a medvétől nem. A sátor egy kör után eltűnik.
 */
public class Tent implements Item {

    /**
     * Meghívja az argumentumban kapott karakter GetField()
     * metódusát, majd a kapott mező ChangeSnowStrategy() metódusát, argumentumként
     * egy új SnowstormStrategyTent objektumot átadva, majd igazzal tér vissza.
     * @param c A karakter aki használja
     * @return True
     */
    @Override
    public boolean UseItem(Character c) {
        c.getField().ChangeSnowStrategy(new SnowstormStrategyTent());
        return true;
    }


}
 