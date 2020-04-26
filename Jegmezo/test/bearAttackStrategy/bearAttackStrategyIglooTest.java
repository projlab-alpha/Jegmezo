package bearAttackStrategy;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class bearAttackStrategyIglooTest {

    BearAttackStrategyIgloo bearAttackStrategyIgloo;
    ArrayList<Character> characters;

    @Before
    public void setUp(){
        bearAttackStrategyIgloo = new BearAttackStrategyIgloo();
        characters.clear();
        Eskimo e = new Eskimo();
        characters.add(e);
    }

    @Test
    public void executeTest(){
        bearAttackStrategyIgloo.execute(characters);
    }
}
