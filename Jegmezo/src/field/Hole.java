package field;

import control.Skeleton;

/**
 * Egy lyukat szimbolizáló mező, amibe a karakterek beleeshetnek
 */
public class Hole extends AbstractField {
    @Override
    public void Accept(character.Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Accept()");
        Skeleton.indent();
        this.characters.add(c);
        c.setField(this);
        c.FellInWater();
        Skeleton.returned();
    }
}
