package Display;

import field.AbstractField;
import field.FieldAppearance;

import javax.swing.*;

public class DisplayTile extends JLabel {
    private AbstractField field;

    public DisplayTile(AbstractField field) {
        this.field = field;
        this.redraw();
    }

    public void redraw() {
        FieldAppearance appearance = field.getAppearance();
        //do stuff with appearance here
        //this.setIcon(new ImageIcon(DisplayTile.class.getResource("/images/floe.png")));
    }
}
