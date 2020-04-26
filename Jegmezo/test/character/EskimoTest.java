package character;

import control.Direction;

import org.junit.Before;
import org.junit.Test;

public class EskimoTest {

    Character character;

    @Before
    public void setUp(){
        character = new Eskimo();
    }

    @Test
    public void UseAbilityTest(){
        character.UseAbility(Direction.SOUTH);
    }

}
