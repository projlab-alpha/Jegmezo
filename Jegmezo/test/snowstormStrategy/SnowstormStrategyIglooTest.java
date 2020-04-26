package snowstormStrategy;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SnowstormStrategyIglooTest {

    SnowstormStrategy snowstormStrategy;
    ArrayList<Character> characters;

    @Before
    public void setUp(){
        snowstormStrategy = new SnowstormStrategyIgloo();
        characters.clear();
        Eskimo e = new Eskimo();
        characters.add(e);
    }

    @Test
    public void executeTest(){
        snowstormStrategy.execute(characters);
    }
}
