package field;

import character.Eskimo;
import character.Character;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FloeTest {

    AbstractField field;
    Character c;

    @Before
    public void setUp(){
        field = new Floe(null, 2, 2);
        c = new Eskimo();
    }

    @Test
    public void AcceptTest(){
        field.Accept(c);
    }

}
