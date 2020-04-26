package character;

import control.Direction;
import org.junit.Before;
import org.junit.Test;

public class ResearcherTest {
    Character character;

    @Before
    public void setUp(){
        character = new Researcher();
    }

    @Test
    public void UseAbilityTest(){
        character.UseAbility(Direction.SOUTH);
    }
}
