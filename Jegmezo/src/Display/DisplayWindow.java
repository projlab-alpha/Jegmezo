package Display;

import character.Eskimo;
import character.Researcher;
import control.Control;
import control.Direction;
import field.GameField;
import item.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Ez az osztály felelős a grafikus megjelenítéséért
 * és a felhasználóval folytatott kommunikáció lebonyolításáért.
 * Az osztály JSwing-es eszközökkel valósítja meg a működését.
 */
public class DisplayWindow extends JFrame {

    /**
     * Zászló, amivel zárolni lehet a felületet. Zároláskor a felület nem fogad több inputot.
     */
    private boolean locked;

    /**
     * A jelenlegi karakter maradék munkáinak kiírására használt szöveg mező.
     */
    private JTextField apField;

    /**
     * A jelenlegi karakter testhőjének kiírására használt szöveg mező.
     */
    private JTextField warmthField;

    /**
     * A jelenlegi forduló kiírására használt szöveg mező.
     */
    private JTextField turnsField;

    /**
     * Portré a jelenlegi karakterről, ez vagy egy eszkimót, vagy egy sarkkutatót ábrázol.
     */
    private JLabel charPortrait;

    /**
     * Tömb a jelenlegi karakter inventory-ának tartalmát megjelenítő címkékből.
     */
    private JLabel[] invPanels;

    /**
     * Kollekció a különböző modellbeli játékmezőket reprezentáló DisplayTile objektumokról.
     * Pontosan olyan hosszú, mint amennyi mezőből áll a játékpálya.
     */
    private ArrayList<DisplayTile> tiles;

    /**
     * Konstruktor. Létrehozza a grafikai felületet.
     * @param width         A játéktábla szélessége
     * @param height        A játéktábla magassága
     * @param gameField     Maga a játéktábla
     */
    public DisplayWindow(int width, int height, GameField gameField) {
        //-------------------------------------------------
        //initialize variables
        //-------------------------------------------------
        this.locked = false;
        apField = new JTextField();
        warmthField = new JTextField();
        turnsField = new JTextField();
        charPortrait = new JLabel();
        invPanels = new JLabel[9];
        for(int i = 0; i < 9; i++) {
            invPanels[i] = new JLabel();
            invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/invslot.png")));
        }
        tiles = new ArrayList<>(width * height);

        //-------------------------------------------------
        //Window setup
        //-------------------------------------------------
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Jegmezo");
        this.setSize(800, 600);
        this.setLayout(new GridBagLayout());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!DisplayWindow.this.locked)
                    Control.getInstance().keyPressed(e);
            }
        });
        this.getContentPane().setBackground(new Color(128, 128, 128));

        //-------------------------------------------------
        //Menu bar setup
        //-------------------------------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem OptionsMenuItemControls = new JMenuItem("Controls", KeyEvent.VK_C);
        OptionsMenuItemControls.addActionListener(e -> JOptionPane.showMessageDialog(DisplayWindow.this, "Controls:\n"
                +"Arrow keys to move\n"
                +"a/w/s/d to use ability in the given direction\n"
                +"Shift to pick an item up\n"
                +"Control to dig\n"
                +"Number keys 1-9 to use item in the given slot\n"
                +"Space to end turn\n", "Controls", JOptionPane.INFORMATION_MESSAGE));
        JMenuItem OptionsMenuItemExit = new JMenuItem("Exit", KeyEvent.VK_E);
        OptionsMenuItemExit.addActionListener(e -> DisplayWindow.this.dispatchEvent(new WindowEvent(DisplayWindow.this, WindowEvent.WINDOW_CLOSING)));
        optionsMenu.add(OptionsMenuItemControls);
        optionsMenu.add(OptionsMenuItemExit);
        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);

        //-------------------------------------------------
        //Game table panel setup
        //-------------------------------------------------
        JPanel gameTableDisplayPanel = new JPanel();
        gameTableDisplayPanel.setLayout(new GridBagLayout());

        //container panel for game tiles
        JPanel gameTableDisplay = new JPanel();
        gameTableDisplay.setLayout(new GridLayout(width, height));

        for(int i = 0; i < width * height; i++) {
            DisplayTile tile = new DisplayTile(gameField.getFloeAt(i));
            tiles.add(tile);
            gameTableDisplay.add(tile);
        }
        gameTableDisplayPanel.add(gameTableDisplay);

        //constraints for game panel
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        c1.weightx = 1;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 2;
        c1.ipady = 0;
        c1.ipadx = 0;
        gameTableDisplayPanel.setMinimumSize(new Dimension(400, 400));
        gameTableDisplayPanel.setMaximumSize(new Dimension(400, 400));
        gameTableDisplayPanel.setBackground(new Color(48, 106, 255));

        this.add(gameTableDisplayPanel, c1);

        //-------------------------------------------------
        //Character panel setup
        //-------------------------------------------------
        JPanel characterInfoDisplayPanel = new JPanel();
        characterInfoDisplayPanel.setLayout(new GridBagLayout());

        //Inventory display
        JPanel inventoryDisplay = new JPanel();
        inventoryDisplay.setLayout(new GridLayout(3, 3));
        for(int i = 0; i < 9; i++) {
            inventoryDisplay.add(invPanels[i]);
        }
        //Panel for character portrait + inventory grid
        JPanel charGridPanel = new JPanel();
        charGridPanel.setLayout(new GridLayout(2, 1));
        charGridPanel.add(charPortrait);
        charGridPanel.add(inventoryDisplay);
        //Panel for portrait + grid, warmth text field, AP text field
        JPanel charBoxPanel = new JPanel();
        charBoxPanel.setLayout(new BoxLayout(charBoxPanel, BoxLayout.Y_AXIS));
        charBoxPanel.add(charGridPanel);

        //warmth text field setup
        warmthField.setFont(new Font("OCR A Extended", Font.BOLD, 14));
        warmthField.setText("Warmth: ?");
        warmthField.setEditable(false);
        warmthField.setBackground(new Color(90, 90, 90));
        warmthField.setDisabledTextColor(new Color(255, 131, 0));
        warmthField.setEnabled(false);
        charBoxPanel.add(warmthField);

        //action point text field setup
        apField.setFont(new Font("OCR A Extended", Font.BOLD, 14));
        apField.setText("AP: ?");
        apField.setEditable(false);
        apField.setBackground(new Color(90, 90, 90));
        apField.setDisabledTextColor(new Color(255, 240, 5));
        apField.setEnabled(false);
        charBoxPanel.add(apField);

        //turns text field setup
        turnsField.setFont(new Font("OCR A Extended", Font.BOLD, 14));
        turnsField.setText("Turn ?");
        turnsField.setEditable(false);
        turnsField.setBackground(new Color(90, 90, 90));
        turnsField.setDisabledTextColor(new Color(100, 200, 5));
        turnsField.setEnabled(false);
        charBoxPanel.add(turnsField);

        characterInfoDisplayPanel.add(charBoxPanel);

        //constraints for character panel
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.FIRST_LINE_END;
        c2.weightx = 1;
        c2.gridwidth = 1;
        c2.gridheight = 2;
        c2.gridx = 2;
        c2.gridy = 0;
        c2.ipady = 400;

        characterInfoDisplayPanel.setBackground(new Color(158, 158, 158));
        this.add(characterInfoDisplayPanel, c2);

        //-------------------------------------------------
        //Status panel setup
        //-------------------------------------------------
        JPanel statusDisplayPanel = new JPanel();
        statusDisplayPanel.setLayout(new GridBagLayout());

        //constraints for status panel
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.LAST_LINE_END;
        c3.weightx = 1;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.gridx = 2;
        c3.gridy = 2;
        c3.ipady = 200;

        statusDisplayPanel.setBackground(new Color(128, 128, 128));
        this.add(statusDisplayPanel, c3);

        //-------------------------------------------------
        redraw();
        this.setVisible(true);
        this.requestFocus();
        //-------------------------------------------------
    }

    /**
     * Elvégzi a grafikus elemek kirajzolását. Lekéri a Control-tól a jelenlegi játékos karakterét,
     * majd annak attribútumai szerint frissíti a karakter panelt,
     * majd meghívja az összes DisplayTile redraw() metódusát, így frissítve a játékpálya megjelenítésének állását.
     */
    public void redraw() {
        character.Character currentChar = Control.getInstance().getCurrentChar();
        //update text fields
        warmthField.setText("Warmth:\t"+currentChar.getWarmth());
        apField.setText("AP:\t"+currentChar.getAP());
        //update character portrait
        if(currentChar instanceof Eskimo) {
            if(currentChar.isDrowning())
                charPortrait.setIcon(new ImageIcon(this.getClass().getResource("/images/eskimoportraitdrowning.png")));
            else
                charPortrait.setIcon(new ImageIcon(this.getClass().getResource("/images/eskimoportrait.png")));
        } else if (currentChar instanceof Researcher) {
            if(currentChar.isDrowning())
                charPortrait.setIcon(new ImageIcon(this.getClass().getResource("/images/researcherportraitdrowning.png")));
            else
                charPortrait.setIcon(new ImageIcon(this.getClass().getResource("/images/researcherportrait.png")));
        }
        //update inventory display
        ArrayList<Item> currCharInv = currentChar.getInventory();
        int i = 0;
        while(i < Math.min(currCharInv.size(), 9)) {
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
            i++;
        }
        while(i < 9) {
            invPanels[i].setIcon(new ImageIcon(this.getClass().getResource("/images/invslot.png")));
            i++;
        }
        //update game field tiles
        for(DisplayTile tile : tiles)
            tile.redraw();
        //update turn text field
        turnsField.setText("Turn\t"+Control.getInstance().getTurn());
    }

    /**
     * Egy dialógus megjelenítésével jelzi a játékosoknak, hogy a játék győzelemmel zárult, és zárolja a felületet.
     */
    public void showVictory() {
        this.locked = true;
        JOptionPane.showMessageDialog(this, "Flaregun fired! Rescue is coming!\n"+"You win!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Egy dialógus megjelenítésével jelzi a játékosoknak, hogy a játék vereséggel zárult, és zárolja a felületet.
     */
    public void showDefeat() {
        this.locked = true;
        JOptionPane.showMessageDialog(this, "A player has died!\n"+"You lose!", "Game over!", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Megjelenít egy dialógus panelt, amely egy karakter különleges képességének
     * eredményét írja ki.
     * @param user      A karakter neve, aki a képességet használta
     * @param d         A képesség használatának iránya
     * @param i         A képesség eredménye
     */
    public void showActionResult(String user, Direction d, int i) {
        JOptionPane.showMessageDialog(this, "Capacity of floe to the "+d+": "+i, user+" used ability", JOptionPane.INFORMATION_MESSAGE);
    }
}
