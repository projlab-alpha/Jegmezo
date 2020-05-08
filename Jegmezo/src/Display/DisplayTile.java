package Display;

import field.AbstractField;
import field.FieldAppearance;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayTile extends JLabel {
    private AbstractField field;

    public DisplayTile(AbstractField field) {
        this.field = field;
        this.redraw();
    }

    public void redraw() {
        FieldAppearance appearance = field.getAppearance();
        BufferedImage base, combined;
        ArrayList<BufferedImage> overlays = new ArrayList<>();
        try {
            //Set base layer
            if(appearance.isHole)
                base = ImageIO.read(DisplayTile.class.getResource("/images/Hole.png"));
            else
                base = ImageIO.read(DisplayTile.class.getResource("/images/Floe.png"));

            //set additional layers
            if(appearance.hasSnow)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Snow.png")));
            else if(appearance.hasItem)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Item.png")));
            if(appearance.hasIgloo)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Igloo.png")));
            else if(appearance.hasTent)     //Igloo takes precedence since it's permanent
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Tent.png")));
            if(appearance.hasEskimo)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Eskimo.png")));
            if(appearance.hasResearcher)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Researcher.png")));
            if(appearance.hasBear)
                overlays.add(ImageIO.read(DisplayTile.class.getResource("/images/Bear.png")));

            //Combine base and additional layers
            combined = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics g = combined.getGraphics();
            g.drawImage(base, 0, 0, null);               //Draw base onto combined
            for (BufferedImage overlay : overlays) {                    //Draw overlays on top of base
                g.drawImage(overlay, 0, 0, null);
            }
            g.dispose();

            //Set label icon to combined image
            this.setIcon(new ImageIcon(combined));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //try {
        //    BufferedImage image = ImageIO.read(DisplayTile.class.getResource("/images/Floe.png"));
        //    BufferedImage overlay = ImageIO.read(DisplayTile.class.getResource("/images/Tent.png"));
        //    combined = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
//
        //    Graphics g = combined.getGraphics();
        //    g.drawImage(image, 0, 0, null);
        //    g.drawImage(overlay, 0, 0, null);
//
        //    g.dispose();
//
        //    this.setIcon(new ImageIcon(combined));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //do stuff with appearance here
        //this.setIcon(new ImageIcon(DisplayTile.class.getResource("/images/floe.png")));
    }
}
