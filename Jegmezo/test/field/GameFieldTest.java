package field;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {
    GameField gameField;
    AbstractField abstractField;

    @Before
    public void setUp(){
        gameField = new GameField();
        abstractField = new Floe(null, 2, 2);
    }

    @Test
    public void addFieldTest(){
        gameField.addField(abstractField);
    }

    @Test
    public void SnowStormTest(){
        gameField.SnowStorm();
    }

}
