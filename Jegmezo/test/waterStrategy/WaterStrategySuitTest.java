package waterStrategy;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class WaterStrategySuitTest {

    WaterStrategy waterStrategy;
    Character c;

    @Before
    public void setUp(){
        waterStrategy = new WaterStrategySuit();
        c = new Eskimo();
    }

    @Test
    public void executeTest(){
        waterStrategy.execute(c);
    }
}
