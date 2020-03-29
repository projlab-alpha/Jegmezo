package field;

import control.Skeleton;

/**
 * Egy stabil jégtáblát szimbolizáló osztály,
 * karakterek és tárgyak lehetnek rajta.
 */
public class Floe extends AbstractField {
    @Override
    public void Accept(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Accept()");
        this.characters.add(c);
        c.setField(this);
    }
}
