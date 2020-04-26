package item;

import character.Character;
import character.Eskimo;
import org.junit.Before;
import org.junit.Test;

public class DivingsuitTest {
    Item item;
    Character c;

    @Before
    public void setUp(){
        item = new Divingsuit();
        c = new Eskimo();
    }

    @Test
    public void UseItemTest(){
        item.UseItem(c);
    }
}
