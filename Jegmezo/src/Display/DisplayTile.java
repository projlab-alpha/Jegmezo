package Display;

import field.AbstractField;

import javax.swing.*;

public class DisplayTile extends JLabel {
    private AbstractField field;

    public DisplayTile() {
        this.redraw();
    }

    public void redraw() {
        //TODO
        this.setIcon(new ImageIcon(DisplayTile.class.getResource("/images/floe.png")));
    }
}
