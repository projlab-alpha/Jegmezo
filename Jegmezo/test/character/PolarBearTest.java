package character;

import control.Direction;

import org.junit.Before;
import org.junit.Test;

public class PolarBearTest {

    Character character;

    @Before
    public void setUp(){
        character = new PolarBear();
    }

    @Test
    public void ChangeWarmthTest(){
        character.ChangeWarmth(2);
    }

    @Test
    public void FellInWaterTest(){
        character.FellInWater();
    }

    @Test
    public void MoveTest(){
        character.Move(Direction.SOUTH);
    }

    @Test
    public void UseAbilityTest(){
        character.UseAbility(Direction.SOUTH);
    }
}
