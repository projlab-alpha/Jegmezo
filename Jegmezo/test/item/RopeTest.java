package item;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class RopeTest {
    Item item;
    Character c;

    @Before
    public void setUp(){
        item = new Rope();
        c = new Eskimo();
    }

    @Test
    public void UseItemTest(){
        item.UseItem(c);
    }
}
