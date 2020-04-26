package bearAttackStrategy;

import character.Eskimo;
import character.Character;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BearAttackStrategyDefaultTest {

    BearAttackStrategyDefault bearAttackStrategyDefault;
    ArrayList<Character> characters;

    @Before
    public void setUp(){
        bearAttackStrategyDefault = new BearAttackStrategyDefault();
        characters.clear();
        Eskimo e = new Eskimo();
        characters.add(e);
    }

    @Test
    public void executeTest(){
        bearAttackStrategyDefault.execute(characters);
    }
}
