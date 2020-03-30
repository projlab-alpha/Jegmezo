package character;

import control.Direction;
import control.Skeleton;
import snowstormStrategy.SnowstormStrategyIgloo;

/**
 * Egy játszható karakter,
 * felülírja az ősosztálya UseAbility metódusát úgy,
 * hogy iglut tudjon építeni.
 */
public class Eskimo extends Character {

    /**
     * A karakter épít egy iglut a mezőre, amin áll
     * @param d Az irány amerre használja képességét.
     * @return Az eszkimó képességének visszatérési értéke használatlan, így mindig 0
     */
    @Override
    public int UseAbility(Direction d) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "UseAbility()");
        SnowstormStrategyIgloo strat = new SnowstormStrategyIgloo();
        this.field.ChangeStrategy(strat);
        return 0;
    }
}
