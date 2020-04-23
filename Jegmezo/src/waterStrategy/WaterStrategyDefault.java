package waterStrategy;

/**
 * Ez az alapértelmezett vízbeesési stratégia arra az esetre, ha olyan karakter esik vízbe, akin
 * nincsen búvár ruha. A meghívott karaktert fuldokló állapotba helyezi.
 */
public class WaterStrategyDefault implements WaterStrategy {

    /**
     * Meghívja az argumentumban kapott karakter Drown() metódusát.
     * @param c Az érintett karakter
     */
    @Override
    public void execute(character.Character c) {
        c.Drown();
    }
}
