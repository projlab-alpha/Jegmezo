package waterStrategy;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;
import snowstormStrategy.SnowstormStrategyDefault;

import java.util.ArrayList;

public class WaterStrategyDefaultTest {

    WaterStrategy waterStrategy;
    Character c;

    @Before
    public void setUp(){
        waterStrategy = new WaterStrategyDefault();
        c = new Eskimo();
    }

    @Test
    public void executeTest(){
        waterStrategy.execute(c);
    }
}
