package field;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class HoleTest {
    AbstractField field;
    Character c;

    @Before
    public void setUp(){
        field = new Hole(null, 2);
        c = new Eskimo();
    }

    @Test
    public void AcceptTest(){
        field.Accept(c);
    }

}
