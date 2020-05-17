package character;

import bearAttackStrategy.BearAttackStrategyIgloo;
import control.Direction;
import snowstormStrategy.SnowstormStrategyIgloo;

/**
 * Egy játszható karakter,
 * felülírja az ősosztálya UseAbility metódusát úgy,
 * hogy iglut tudjon építeni.
 */
public class Eskimo extends Character {

    /**
     * Beállítja a testhőt 5-re.
     */
    public Eskimo() {
        warmth = 5;
    } 

    /**
     * Használja az eszkimó különleges képességét, amely egy
     * iglut épít a jelenlegi mezőre. Ehhez megváltoztatja a mező hóvihar stratégiáját
     * SnowstormStrategyIgloo-ra, és a medve támadás stratégiáját BearAttackStrategy
     * Igloo-ra, a ChangeSnowStrategy()/ChangeBearStrategy() metódusok meghívásával.
     * Ez után eggyel csökkenti az eszkimó hátralévő munkáinak számát, majd visszatér -1 -gyel.
     * @param d Az irány amerre használja képességét.
     * @return 0
     */
    @Override
    public int UseAbility(Direction d) {
        if(actionpoint > 0) {
            field.ChangeSnowStrategy(new SnowstormStrategyIgloo());
            field.ChangeBearStrategy(new BearAttackStrategyIgloo());
            --actionpoint;
        }
        return -1;
    }
}
