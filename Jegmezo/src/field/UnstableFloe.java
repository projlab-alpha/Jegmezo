package field;

import character.Character;
import control.Skeleton;

/**
 * Egy stabil jégtáblát szimbolizáló osztály,
 * karakterek és tárgyak lehetnek rajta
 */
public class UnstableFloe extends AbstractField {

    /**
     * Átfordul a mező, a rajta lévő játékosok vízbe esnek
     */
    public void RollOver(){
        Skeleton.methodCalled(this.getClass().getSimpleName(), "RollOver()");
        Skeleton.indent();
        for (character.Character c : this.characters) {
            c.FellInWater();
        }
        Skeleton.indent();
    }

    @Override
    public void Accept(Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Accept()");
        Skeleton.indent();
        c.setField(this);
        this.characters.add(c);
        boolean res = Skeleton.askQuestion("Atfordul a mezo?");
        if(res)
            this.RollOver();
        Skeleton.returned();
    }
}
