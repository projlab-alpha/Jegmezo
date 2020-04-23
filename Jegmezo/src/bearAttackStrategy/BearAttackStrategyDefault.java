package bearAttackStrategy;
import character.Character;
import control.Control;

import java.util.ArrayList;

/**
 * Ez az alapértelmezett medvetámadási stratégia arra az esetre, ha olyan mezőn támad medve,
 * amelyen nincsen iglu. Ha a mezőn vannak karakterek, akkor azok meghalnak, így a játéknak
 * vége lesz.
 */
public class BearAttackStrategyDefault implements BearAttackStrategy {

    /**
     * Ha az argumentumban kapott
     * kollekció nem üres, akkor a Control CharacterDied() metódusának meghívásával jelzi,
     * hogy a medve megevett egy vagy több karaktert.
     * @param chars Az érintett karakterek listája
     */
    @Override
    public void execute(ArrayList<Character> chars) {
        if (chars.isEmpty() == false)
            Control.getInstance().CharacterDied();
    }
}
