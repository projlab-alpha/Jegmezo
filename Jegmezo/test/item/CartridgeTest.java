package item;

import character.Eskimo;
import character.Character;

import org.junit.Before;
import org.junit.Test;

public class CartridgeTest {
    Item item;
    Character c;

    @Before
    public void setUp(){
        item = new Cartridge();
        c = new Eskimo();
    }

    @Test
    public void UseItemTest(){
        item.UseItem(c);
    }
}
