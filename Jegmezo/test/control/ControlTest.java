package control;

import org.junit.Before;
import org.junit.Test;

public class ControlTest {

    Control control;

    @Before
    public void setUp(){
        control = Control.getInstance();
    }

    @Test
    public void CharacterDiedTest(){
        control.CharacterDied();
    }

    @Test
    public void WinTest(){
        control.Win();
    }
}
