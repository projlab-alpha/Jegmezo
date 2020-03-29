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
        this.characters.set(0, new character.Eskimo());
        this.characters.get(0).FellInWater();
    }

    @Override
    public void Accept(Character c) {
        Skeleton.methodCalled(this.getClass().getSimpleName(), "Accept()");
        this.characters.add(c);
        c.setField(this);
        boolean res = Skeleton.askQuestion("Atfordul a mezo?");
        if(res)
            this.RollOver();
    }
}
