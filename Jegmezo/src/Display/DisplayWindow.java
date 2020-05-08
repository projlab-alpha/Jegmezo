package Display;

import control.Control;
import field.Floe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;

public class DisplayWindow extends JFrame {
    private boolean locked;
    private JTextField apField;
    private JTextField warmthField;
    private JTextField turnsField;
    private JLabel charPortrait;
    private JLabel[] invPanels;

    public DisplayWindow(int width, int height) {
        //initialize variables
        invPanels = new JLabel[9];

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
        OptionsMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayWindow.this.dispatchEvent(new WindowEvent(DisplayWindow.this, WindowEvent.WINDOW_CLOSING));
            }
        });
        optionsMenu.add(OptionsMenuItemExit);
        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);


        //Game table panel setup
        //container panel for game tiles
        JPanel gameTableDisplay = new JPanel();
        gameTableDisplay.setLayout(new GridLayout(width, height));

        Floe testfloe = new Floe(null, 10, 3);  //TODO: replace test with initialized floes

        for(int i = 0; i < width * height; i++) {
            gameTableDisplay.add(new DisplayTile(testfloe));
        }
        Dimension dim = new Dimension(width * 32, height * 32);
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
        c1.ipady = 600;
        c1.ipadx = 400;

        gameTableDisplayPanel.setBackground(Color.CYAN);        //delete later
        gameTableDisplayPanel.add(gameTableDisplay);
        this.add(gameTableDisplayPanel, c1);


        //Character panel setup
        JPanel characterInfoDisplayPanel = new JPanel();
        characterInfoDisplayPanel.setLayout(new GridBagLayout());
            //add stuff to panel here

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
    }

    public void redraw() {
        //TODO
    }

    public void showVictory() {
        //TODO
    }

    public void showDefeat() {
        //TODO
    }
}
