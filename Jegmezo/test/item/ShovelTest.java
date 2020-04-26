package item;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class ShovelTest {
    Item item;
    Character c;

    @Before
    public void setUp(){
        item = new Shovel();
        c = new Eskimo();
    }

    @Test
    public void UseItemTest(){
        item.UseItem(c);
    }
}
