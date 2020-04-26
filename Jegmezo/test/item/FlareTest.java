package item;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class FlareTest {
    Item item;
    Character c;

    @Before
    public void setUp(){
        item = new Flare();
        c = new Eskimo();
    }

    @Test
    public void UseItemTest(){
        item.UseItem(c);
    }
}
