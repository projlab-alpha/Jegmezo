package Display;

import control.Control;
import field.GameField;
import item.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DisplayWindow extends JFrame {
    private boolean locked;
    private JTextField apField;
    private JTextField warmthField;
    private JTextField turnsField;
    private JLabel charPortrait;
    private JLabel[] invPanels = new JLabel[9];
    private ArrayList<DisplayTile> tiles;

    public DisplayWindow(int width, int height, GameField gameField) {
        //initialize variables
        for(int i = 0; i < 9; i++) {
            invPanels[i] = new JLabel();
            System.out.println(invPanels[i].getClass().getSimpleName());
            invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/invslot.png")));
        }
        tiles = new ArrayList<>(width * height);
        final int imgDim = 32;

        //Window setup
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Jégmező");
        this.setSize(800, 600);
        this.setLayout(new GridBagLayout());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Control.getInstance().keyPressed(e);
            }
        });


        this.getContentPane().setBackground(Color.darkGray);        //delete later

        //Menu bar setup
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem OptionsMenuItemExit = new JMenuItem("Exit", KeyEvent.VK_E);
        OptionsMenuItemExit.addActionListener(e -> DisplayWindow.this.dispatchEvent(new WindowEvent(DisplayWindow.this, WindowEvent.WINDOW_CLOSING)));
        JMenuItem OptionsMenuItemControls = new JMenuItem("Controls", KeyEvent.VK_C);
        OptionsMenuItemControls.addActionListener(e -> JOptionPane.showMessageDialog(DisplayWindow.this, "Controls:\n"
                +"Arrow keys to move\n"
                +"a/w/s/d to use ability in the given direction\n"
                +"Shift to pick an item up\n"
                +"Number keys 1-9 to use item in the given slot\n"
                +"Space to end turn\n", "Controls", JOptionPane.INFORMATION_MESSAGE));
        optionsMenu.add(OptionsMenuItemExit);
        optionsMenu.add(OptionsMenuItemControls);
        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);


        //Game table panel setup
        //container panel for game tiles
        JPanel gameTableDisplay = new JPanel();
        gameTableDisplay.setLayout(new GridLayout(width, height));

        for(int i = 0; i < width * height; i++) {
            DisplayTile tile = new DisplayTile(gameField.getFloeAt(i));
            tiles.add(tile);
            gameTableDisplay.add(tile);
        }
        Dimension dim = new Dimension(width * imgDim, height * imgDim);
        gameTableDisplay.setPreferredSize(dim);
        gameTableDisplay.setMaximumSize(dim);
        gameTableDisplay.setMinimumSize(dim);

        JPanel gameTableDisplayPanel = new JPanel();
        gameTableDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        c1.weightx = 1;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 2;
        c1.gridheight = 3;
        c1.ipady = 0;
        c1.ipadx = 0;
        gameTableDisplayPanel.setMinimumSize(new Dimension(400, 400));
        gameTableDisplayPanel.setMaximumSize(new Dimension(400, 400));

        gameTableDisplayPanel.setBackground(Color.CYAN);        //delete later
        gameTableDisplayPanel.add(gameTableDisplay);
        this.add(gameTableDisplayPanel, c1);


        //Character panel setup
        JPanel characterInfoDisplayPanel = new JPanel();
        characterInfoDisplayPanel.setLayout(new GridBagLayout());

        JPanel inventoryDisplay = new JPanel();
        inventoryDisplay.setLayout(new GridLayout(3, 3));
        for(int i = 0; i < 9; i++) {
            inventoryDisplay.add(invPanels[i]);
        }
        characterInfoDisplayPanel.add(inventoryDisplay);


        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.FIRST_LINE_END;
        c2.weightx = 1;
        c2.gridwidth = 1;
        c2.gridheight = 2;
        c2.gridx = 2;
        c2.gridy = 0;
        c2.ipady = 400;

        characterInfoDisplayPanel.setBackground(Color.magenta);        //delete later
        this.add(characterInfoDisplayPanel, c2);


        //Status panel setup
        JPanel statusDisplayPanel = new JPanel();
        statusDisplayPanel.setLayout(new GridBagLayout());
            //add stuff to panel here

        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.LAST_LINE_END;
        c3.weightx = 1;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.gridx = 2;
        c3.gridy = 2;
        c3.ipady = 200;

        statusDisplayPanel.setBackground(Color.red);        //delete later
        this.add(statusDisplayPanel, c3);
        redraw();
    }

    public void redraw() {
        System.out.println("redrawing");
        character.Character currentChar = Control.getInstance().getCurrentChar();
        ArrayList<Item> currCharInv = currentChar.getInventory();
        for(int i = 0; i < Math.min(currCharInv.size(), 9); i++) {
            if(currCharInv.get(i) instanceof Cartridge)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/cartridge.png")));
            else if(currCharInv.get(i) instanceof Divingsuit)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/divingsuit.png")));
            else if(currCharInv.get(i) instanceof Flare)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/flare.png")));
            else if(currCharInv.get(i) instanceof Food)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/food.png")));
            else if(currCharInv.get(i) instanceof FragileShovel)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/fragileshovel.png")));
            else if(currCharInv.get(i) instanceof Pistol)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/pistol.png")));
            else if(currCharInv.get(i) instanceof Rope)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/rope.png")));
            else if(currCharInv.get(i) instanceof Shovel)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/shovel.png")));
            else if(currCharInv.get(i) instanceof Tent)
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/tentitem.png")));
            else
                invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/invslot.png")));
        }
        for(DisplayTile tile : tiles)
            tile.redraw();
    }

    public void showVictory() {
        JOptionPane.showMessageDialog(this, "You win!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showDefeat() {
        JOptionPane.showMessageDialog(this, "You lose!", "Game over!", JOptionPane.WARNING_MESSAGE);
    }
}
