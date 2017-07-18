package frontend_viewcontroller;

import backend_models.*;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import Logger.Logger;
import java.text.NumberFormat;

public class MainViewDisplay extends JFrame {

    public BackendModelSetup theBackendModel;
    public BufferedImage championIcon, championPassive, championQ, championW, championE,
            championR, splashArt, defualtIcon, defualtPassive, defualtQ, defualtW,
            defualtE, defualtR;
    public JButton[] championButtons, itemButtons, spellButtons;
    public JButton championSelectButton, championPassiveButton, championQButton,
            championWButton, championEButton, championRButton, closeWindowButton,
            updateLevelButton, item1Button, item2Button, item3Button,
            item4Button, item5Button, item6Button, item7Button, itemSearchButton,
            clearItemsButton, sumKeyD, sumKeyF;
    public JLabel championIconLabel, championHPLabel,
            championResourceLabel, championADLabel, championAPLabel,
            championArmorLabel, championMRLabel, championAttackSpeedLabel,
            championMovementSpeedLabel, championCritLabel,
            championLevelLabel, hudEnergyLabel, hudDefualtLabel, hudManaLabel,
            hudRageLabel, hudShieldLabel, splashArtLabel, titleLabel;
    public JPanel championLevelPanel, championAttackSpeedPanel,
            championMovementSpeedPanel, championCritPanel, adStatsPanel,
            hudPanel, apStatsPanel, hpStatsPanel, resorceStatsPanel, armorStatsPanel,
            mrStatsPanel, interactivePanel, championIconPanel, championPassivePanel,
            championQPanel, championWPanel, championEPanel, championRPanel, splashArtPanel,
            championSelectorPanel, ablitiesPanel, item1Panel, item2Panel, item3Panel,
            item4Panel, item5Panel, item6Panel, item7Panel, itemSelectPanel, sumKeyDPanel,
            sumKeyFPanel, spellSelectorPanel;
    public JTextArea abilityTextArea, levelSelectArea, itemSearchArea;

    private double screenWidth, screenHeight;
    public int itemSlot, spellSlot;

    /**
     * Creates The Main View Display
     *
     * @param aBackend The BackendModelSetup Object
     */
    public MainViewDisplay(BackendModelSetup aBackend) {
        super();
        this.theBackendModel = aBackend;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = screenSize.getWidth();
        this.screenHeight = screenSize.getHeight();
        this.setPreferredSize(screenSize);
        this.createSelectors();
        this.initComponents();
    } //Make Selector Panel for Spells

    /**
     * Setup the JPanels
     */
    private void setupJPanels() {
        this.hudPanel = new JPanel();
        this.adStatsPanel = new JPanel();
        this.apStatsPanel = new JPanel();
        this.hpStatsPanel = new JPanel();
        this.interactivePanel = new JPanel();
        this.resorceStatsPanel = new JPanel();
        this.armorStatsPanel = new JPanel();
        this.mrStatsPanel = new JPanel();
        this.championIconPanel = new JPanel();
        this.championPassivePanel = new JPanel();
        this.championQPanel = new JPanel();
        this.championWPanel = new JPanel();
        this.championEPanel = new JPanel();
        this.championRPanel = new JPanel();
        this.championAttackSpeedPanel = new JPanel();
        this.championMovementSpeedPanel = new JPanel();
        this.championCritPanel = new JPanel();
        this.championLevelPanel = new JPanel();
        this.splashArtPanel = new JPanel();
        this.championSelectorPanel = new JPanel();
        this.ablitiesPanel = new JPanel();
        this.item1Panel = new JPanel();
        this.item2Panel = new JPanel();
        this.item3Panel = new JPanel();
        this.item4Panel = new JPanel();
        this.item5Panel = new JPanel();
        this.item6Panel = new JPanel();
        this.item7Panel = new JPanel();
        this.itemSelectPanel = new JPanel();
        this.sumKeyDPanel = new JPanel();
        this.sumKeyFPanel = new JPanel();
        this.spellSelectorPanel = new JPanel();
    }

    /**
     * Setup the JLabels
     */
    private void setupJLabels() {
        this.championLevelLabel = new JLabel("1");
        this.championAttackSpeedLabel = new JLabel("0.0");
        this.championMovementSpeedLabel = new JLabel("0");
        this.championCritLabel = new JLabel("0");
        this.championHPLabel = new JLabel("0");
        this.championResourceLabel = new JLabel("0");
        this.championADLabel = new JLabel("0");
        this.championAPLabel = new JLabel("0");
        this.championArmorLabel = new JLabel("0");
        this.championMRLabel = new JLabel("0");
        this.titleLabel = new JLabel("");
    }

    /**
     * Setup the JTextAreas
     */
    private void setupJTextAreas() {
        this.abilityTextArea = new JTextArea("");
        this.abilityTextArea.setEditable(false);
        this.abilityTextArea.setLineWrap(true);
        this.abilityTextArea.setWrapStyleWord(true);
        this.levelSelectArea = new JTextArea("1");
        this.itemSearchArea = new JTextArea("");
    }

    /**
     * Setup the JButtons
     */
    private void setupJButtons() {
        this.championSelectButton = new JButton("Select List");
        this.itemSearchButton = new JButton("Search");
        this.closeWindowButton = new JButton(new ImageIcon(ImageBuilder.buildSizedImage("data\\HUD\\close.png", 57, 57, "CLOSE")));
        this.closeWindowButton.setBackground(Color.BLACK);
        this.updateLevelButton = new JButton("Update");
        this.clearItemsButton = new JButton("Clear Items");
        Image add = ImageBuilder.buildSizedImage("data\\HUD\\add.png", 52, 52, "ADD");
        this.item1Button = new JButton(new ImageIcon(add));
        this.item2Button = new JButton(new ImageIcon(add));
        this.item3Button = new JButton(new ImageIcon(add));
        this.item5Button = new JButton(new ImageIcon(add));
        this.item6Button = new JButton(new ImageIcon(add));
        this.item7Button = new JButton(new ImageIcon(add));
        this.sumKeyD = new JButton(new ImageIcon(add));
        this.sumKeyF = new JButton(new ImageIcon(add));
        add = ImageBuilder.buildSizedImage("data\\HUD\\add.png", 42, 42, "ADD");
        this.item4Button = new JButton(new ImageIcon(add));
    }

    /**
     * Finds and uses the start up pictures
     */
    private void setupDefualtPictures() {
        this.hudDefualtLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\hudNoResorce.png", "NO RESOURCE HUD")));
        this.hudDefualtLabel.setVisible(true);

        this.hudManaLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\hudMana.png", "MANA")));
        this.hudManaLabel.setVisible(false);

        this.hudRageLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\hudFullRage.png", "RAGE")));
        this.hudRageLabel.setVisible(false);

        this.hudShieldLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\hudFullWhiteStacks.png", "SHIELD")));
        this.hudShieldLabel.setVisible(false);

        this.hudEnergyLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\hudEnergy.png", "ENERGY")));
        this.hudEnergyLabel.setVisible(true);

        this.championIconLabel = new JLabel(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\Icon.png", "DEFUALT ICON")));

        this.championPassiveButton = new JButton(new ImageIcon(ImageBuilder.buildImage("data\\HUD\\Passive.png", "PASSIVE ICON")));

        try {
            this.defualtIcon = ImageIO.read(new File("data\\HUD\\Icon.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT ICON");
        }
        try {
            this.defualtPassive = ImageIO.read(new File("data\\HUD\\Passive.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT PASSIVE");
        }
        try {
            this.defualtQ = ImageIO.read(new File("data\\HUD\\Q.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT Q");
        }
        try {
            this.defualtW = ImageIO.read(new File("data\\HUD\\Q.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT W");
        }
        try {
            this.defualtE = ImageIO.read(new File("data\\HUD\\Q.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT E");
        }
        try {
            this.defualtR = ImageIO.read(new File("data\\HUD\\Q.png"));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT R");
        }

        Image img = ImageBuilder.buildImage("data\\HUD\\Q.png", "DEFAULT Q");
        this.championQButton = new JButton(new ImageIcon(img));
        this.championWButton = new JButton(new ImageIcon(img));
        this.championEButton = new JButton(new ImageIcon(img));
        this.championRButton = new JButton(new ImageIcon(img));

        try {
            this.splashArt = ImageIO.read(new File("data\\HUD\\main.png"));
            img = this.splashArt.getScaledInstance((int) this.screenWidth, (int) this.screenHeight, Image.SCALE_DEFAULT);
            this.splashArtLabel = new JLabel(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT GET DEFUALT SPLASH ART");
        }
    }

    /**
     * Creates Graphical User Interface
     */
    private void initComponents() {
        ServerConnection.checkOnline();
        if (ServerConnection.getOnlineStats()) {
            String ver = ServerConnection.getCurrentVersion();
            this.setTitle("League of Legends Champion Builder For Patch " + ver);
        } else {
            this.setTitle("League of Legends Champion Builder");
        }

        //JLayeredPane
        JLayeredPane hudLayer = new JLayeredPane();
        JLayeredPane statsLayer = new JLayeredPane();
        JLayeredPane interactiveLayer = new JLayeredPane();
        JLayeredPane pictureLayer = new JLayeredPane();
        JLayeredPane splashLayer = new JLayeredPane();
        JLayeredPane selectorLayer = new JLayeredPane();
        JLayeredPane informationLayer = new JLayeredPane();

        hudLayer.setLayout(null);
        statsLayer.setLayout(null);
        interactiveLayer.setLayout(null);
        pictureLayer.setLayout(null);
        splashLayer.setLayout(null);
        selectorLayer.setLayout(null);
        informationLayer.setLayout(null);

        setupJPanels();
        setupJLabels();
        setupJTextAreas();
        setupJButtons();

        //Binding the Search Button to Enter
        JRootPane jp = getRootPane();
        jp.setDefaultButton(championSelectButton);
        //______________________________________________________________________

        setupDefualtPictures();

        //Hud Layer Setup
        this.hudPanel.setSize(new Dimension(1239, 195));
        this.hudPanel.setOpaque(false);
        this.hudPanel.setLocation((int) (this.screenWidth / 2 - 619), (int) (this.screenHeight - 260));
        this.hudPanel.add(this.hudDefualtLabel, BorderLayout.CENTER);
        this.hudPanel.add(this.hudEnergyLabel, BorderLayout.CENTER);
        this.hudPanel.add(this.hudManaLabel, BorderLayout.CENTER);
        this.hudPanel.add(this.hudRageLabel, BorderLayout.CENTER);
        this.hudPanel.add(this.hudShieldLabel, BorderLayout.CENTER);
        //______________________________________________________________________

        //AD Panel Setup
        this.adStatsPanel.setVisible(true);
        this.adStatsPanel.setSize(new Dimension(45, 20));
        this.adStatsPanel.setOpaque(false);
        this.adStatsPanel.setLocation((int) (this.screenWidth / 2 - 565), (int) (this.screenHeight - 216));
        this.championADLabel.setForeground(Color.WHITE);
        this.adStatsPanel.add(this.championADLabel);
        //______________________________________________________________________

        //AP Panel Setup
        this.apStatsPanel.setVisible(true);
        this.apStatsPanel.setSize(new Dimension(45, 20));
        this.apStatsPanel.setOpaque(false);
        this.apStatsPanel.setLocation((int) (this.screenWidth / 2 - 462), (int) (this.screenHeight - 216));
        this.championAPLabel.setForeground(Color.WHITE);
        this.apStatsPanel.add(this.championAPLabel);
        //______________________________________________________________________

        //Armor Panel Setup
        this.armorStatsPanel.setVisible(true);
        this.armorStatsPanel.setSize(new Dimension(45, 20));
        this.armorStatsPanel.setOpaque(false);
        this.armorStatsPanel.setLocation((int) (this.screenWidth / 2 - 565), (int) (this.screenHeight - 188));
        this.championArmorLabel.setForeground(Color.WHITE);
        this.armorStatsPanel.add(this.championArmorLabel);
        //______________________________________________________________________

        //MR Panel Setup
        this.mrStatsPanel.setVisible(true);
        this.mrStatsPanel.setSize(new Dimension(45, 20));
        this.mrStatsPanel.setOpaque(false);
        this.mrStatsPanel.setLocation((int) (this.screenWidth / 2 - 462), (int) (this.screenHeight - 188));
        this.championMRLabel.setForeground(Color.WHITE);
        this.mrStatsPanel.add(this.championMRLabel);
        //______________________________________________________________________

        //HP Panel Setup
        this.hpStatsPanel.setVisible(true);
        this.hpStatsPanel.setSize(new Dimension(85, 25));
        this.hpStatsPanel.setOpaque(false);
        this.hpStatsPanel.setLocation((int) (this.screenWidth / 2 + 25), (int) (this.screenHeight - 125));
        this.championHPLabel.setForeground(Color.WHITE);
        this.hpStatsPanel.add(this.championHPLabel);
        //______________________________________________________________________

        //Resorce Panel Setup
        this.resorceStatsPanel.setVisible(false);
        this.resorceStatsPanel.setSize(new Dimension(65, 25));
        this.resorceStatsPanel.setOpaque(false);
        this.resorceStatsPanel.setLocation((int) (this.screenWidth / 2 + 30), (int) (this.screenHeight - 100));
        this.championResourceLabel.setForeground(Color.WHITE);
        this.resorceStatsPanel.add(this.championResourceLabel);
        //______________________________________________________________________

        //Attack Speed Panel Setup
        this.championAttackSpeedPanel.setVisible(true);
        this.championAttackSpeedPanel.setSize(new Dimension(45, 20));
        this.championAttackSpeedPanel.setOpaque(false);
        this.championAttackSpeedPanel.setLocation((int) (this.screenWidth / 2 - 565), (int) (this.screenHeight - 158));
        this.championAttackSpeedLabel.setForeground(Color.WHITE);
        this.championAttackSpeedPanel.add(this.championAttackSpeedLabel);
        //______________________________________________________________________

        //Attack Range Panel Setup
        this.championCritPanel.setVisible(true);
        this.championCritPanel.setSize(new Dimension(45, 20));
        this.championCritPanel.setOpaque(false);
        this.championCritPanel.setLocation((int) (this.screenWidth / 2 - 565), (int) (this.screenHeight - 128));
        this.championCritLabel.setForeground(Color.WHITE);
        this.championCritPanel.add(this.championCritLabel);
        //______________________________________________________________________

        //Movement Speed Panel Setup
        this.championMovementSpeedPanel.setVisible(true);
        this.championMovementSpeedPanel.setSize(new Dimension(45, 20));
        this.championMovementSpeedPanel.setOpaque(false);
        this.championMovementSpeedPanel.setLocation((int) (this.screenWidth / 2 - 462), (int) (this.screenHeight - 128));
        this.championMovementSpeedLabel.setForeground(Color.WHITE);
        this.championMovementSpeedPanel.add(this.championMovementSpeedLabel);
        //______________________________________________________________________

        //Level Panel Setup
        this.championLevelPanel.setVisible(true);
        this.championLevelPanel.setSize(new Dimension(25, 20));
        this.championLevelPanel.setOpaque(false);
        this.championLevelPanel.setLocation((int) (this.screenWidth / 2 - 316), (int) (this.screenHeight - 114));
        this.championLevelLabel.setForeground(Color.WHITE);
        this.championLevelPanel.add(this.championLevelLabel);
        //______________________________________________________________________

        //Interactive Layer Setup (Used for Buttons, SearchFields, ect.)
        this.interactivePanel.setVisible(true);
        this.interactivePanel.setSize(new Dimension(1239, 400));
        this.interactivePanel.setOpaque(false);
        this.interactivePanel.setLocation(0, 0);
        this.interactivePanel.add(this.championSelectButton);
        this.interactivePanel.add(this.levelSelectArea);
        this.interactivePanel.add(this.updateLevelButton);
        this.interactivePanel.add(this.clearItemsButton);
        //______________________________________________________________________

        //Icon Panel Setup
        this.championIconPanel.setVisible(true);
        this.championIconPanel.setSize(new Dimension(120, 120));
        this.championIconPanel.setOpaque(false);
        this.championIconPanel.setLocation((int) (this.screenWidth / 2 - 405), (int) (this.screenHeight - 231));//-343,127
        this.championIconPanel.add(this.championIconLabel);
        //______________________________________________________________________

        //Passive Panel Setup
        this.championPassivePanel.setVisible(true);
        this.championPassivePanel.setSize(new Dimension(60, 65));
        this.championPassivePanel.setOpaque(false);
        this.championPassivePanel.setLocation((int) (this.screenWidth / 2 - 242), (int) this.screenHeight - 240);
        this.championPassivePanel.add(this.championPassiveButton);
        //______________________________________________________________________

        //Q Panel Setup
        this.championQPanel.setVisible(true);
        this.championQPanel.setSize(new Dimension(75, 85));
        this.championQPanel.setOpaque(false);
        this.championQPanel.setLocation((int) (this.screenWidth / 2 - 174), (int) (this.screenHeight - 238));
        this.championQPanel.add(this.championQButton);
        //______________________________________________________________________

        //W Panel Setup
        this.championWPanel.setVisible(true);
        this.championWPanel.setSize(new Dimension(75, 85));
        this.championWPanel.setOpaque(false);
        this.championWPanel.setLocation((int) (this.screenWidth / 2 - 91), (int) (this.screenHeight - 238));
        this.championWPanel.add(this.championWButton);
        //______________________________________________________________________

        //E Panel Setup
        this.championEPanel.setVisible(true);
        this.championEPanel.setSize(new Dimension(75, 85));
        this.championEPanel.setOpaque(false);
        this.championEPanel.setLocation((int) (this.screenWidth / 2 - 9), (int) (this.screenHeight - 238));
        this.championEPanel.add(this.championEButton);
        //______________________________________________________________________

        //R Panel Setup
        this.championRPanel.setVisible(true);
        this.championRPanel.setSize(new Dimension(75, 85));
        this.championRPanel.setOpaque(false);
        this.championRPanel.setLocation((int) (this.screenWidth / 2 + 74), (int) (this.screenHeight - 238));
        this.championRPanel.add(this.championRButton);
        //______________________________________________________________________

        //Splash Art Panel Setup
        this.splashArtPanel.setVisible(true);
        this.splashArtPanel.setSize(this.splashArt.getWidth(), this.splashArt.getHeight());
        this.splashArtPanel.setOpaque(false);
        this.splashArtPanel.setLocation(0, 0);
        this.splashArtPanel.add(this.splashArtLabel);
        //______________________________________________________________________

        //Creating selectorPanel
        this.championSelectorPanel.setVisible(true);
        this.championSelectorPanel.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        this.championSelectorPanel.setOpaque(true);
        this.championSelectorPanel.setLocation(0, 0);
        createSelectorPanel(1, -1);
        //______________________________________________________________________

        //Creating itemPanel
        this.itemSelectPanel.setVisible(false);
        this.itemSelectPanel.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        this.itemSelectPanel.setOpaque(true);
        this.itemSelectPanel.setLocation(0, 0);
        this.itemSearchArea.setSize(25, 5);
        this.itemSelectPanel.add(this.itemSearchArea);
        this.itemSelectPanel.add(this.itemSearchButton);
        createItemPanel(1, -1);
        //______________________________________________________________________

        //Creating spellPanel
        this.spellSelectorPanel.setVisible(false);
        this.spellSelectorPanel.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        this.spellSelectorPanel.setOpaque(true);
        this.spellSelectorPanel.setLocation(0, 0);
        createSpellPanel(1, -1);
        //______________________________________________________________________

        //Ablities Panel Setup
        this.ablitiesPanel.setVisible(false);
        this.ablitiesPanel.setSize(new Dimension(500, 200));
        this.ablitiesPanel.setOpaque(true);
        this.ablitiesPanel.setLocation(0, 0);
        this.abilityTextArea.setForeground(Color.BLACK);
        this.titleLabel.setForeground(Color.BLACK);
        this.abilityTextArea.setSize(480, 180);
        this.abilityTextArea.setLocation(0, 50);
        this.ablitiesPanel.add(this.titleLabel);
        this.ablitiesPanel.add(this.abilityTextArea);
        this.ablitiesPanel.add(this.closeWindowButton);
        //______________________________________________________________________

        //Item Panels Setup
        this.item1Panel.setVisible(true);
        this.item1Panel.setSize(new Dimension(52, 62));
        this.item1Panel.setOpaque(false);
        this.item1Panel.setLocation((int) this.screenWidth / 2 + 367, (int) this.screenHeight - 242);
        this.item1Panel.add(this.item1Button);

        this.item2Panel.setVisible(true);
        this.item2Panel.setSize(new Dimension(52, 62));
        this.item2Panel.setOpaque(false);
        this.item2Panel.setLocation((int) this.screenWidth / 2 + 426, (int) this.screenHeight - 242);
        this.item2Panel.add(this.item2Button);

        this.item3Panel.setVisible(true);
        this.item3Panel.setSize(new Dimension(52, 62));
        this.item3Panel.setOpaque(false);
        this.item3Panel.setLocation((int) this.screenWidth / 2 + 485, (int) this.screenHeight - 242);
        this.item3Panel.add(this.item3Button);

        this.item4Panel.setVisible(true);
        this.item4Panel.setSize(new Dimension(42, 52));
        this.item4Panel.setOpaque(false);
        this.item4Panel.setLocation((int) this.screenWidth / 2 + 550, (int) this.screenHeight - 207);
        this.item4Panel.add(this.item4Button);

        this.item5Panel.setVisible(true);
        this.item5Panel.setSize(new Dimension(52, 62));
        this.item5Panel.setOpaque(false);
        this.item5Panel.setLocation((int) this.screenWidth / 2 + 367, (int) this.screenHeight - 184);
        this.item5Panel.add(this.item5Button);

        this.item6Panel.setVisible(true);
        this.item6Panel.setSize(new Dimension(52, 62));
        this.item6Panel.setOpaque(false);
        this.item6Panel.setLocation((int) this.screenWidth / 2 + 426, (int) this.screenHeight - 184);
        this.item6Panel.add(this.item6Button);

        this.item7Panel.setVisible(true);
        this.item7Panel.setSize(new Dimension(52, 62));
        this.item7Panel.setOpaque(false);
        this.item7Panel.setLocation((int) this.screenWidth / 2 + 485, (int) this.screenHeight - 184);
        this.item7Panel.add(this.item7Button);
        //______________________________________________________________________

        //Summoner Keys Setup
        this.sumKeyDPanel.setVisible(true);
        this.sumKeyDPanel.setSize(new Dimension(52, 62));
        this.sumKeyDPanel.setOpaque(false);
        this.sumKeyDPanel.setLocation((int) this.screenWidth / 2 + 163, (int) this.screenHeight - 238);
        this.sumKeyDPanel.add(this.sumKeyD);

        this.sumKeyFPanel.setVisible(true);
        this.sumKeyFPanel.setSize(new Dimension(52, 62));
        this.sumKeyFPanel.setOpaque(false);
        this.sumKeyFPanel.setLocation((int) this.screenWidth / 2 + 225, (int) this.screenHeight - 238);
        this.sumKeyFPanel.add(this.sumKeyF);
        //______________________________________________________________________

        //Forcing Layers too set Size
        hudLayer.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        statsLayer.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        interactiveLayer.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        pictureLayer.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        selectorLayer.setSize(new Dimension((int) this.screenWidth, (int) this.screenHeight));
        informationLayer.setSize(new Dimension(500, 200));
        pictureLayer.setForeground(Color.BLACK);
        //______________________________________________________________________

        //Adding Panels to Layers to Display (Closer to top is top layer)
        hudLayer.add(this.hudPanel, 1);

        statsLayer.add(this.adStatsPanel, 1);
        statsLayer.add(this.apStatsPanel, 2);
        statsLayer.add(this.hpStatsPanel, 3);
        statsLayer.add(this.resorceStatsPanel, 4);
        statsLayer.add(this.armorStatsPanel, 5);
        statsLayer.add(this.mrStatsPanel, 6);
        statsLayer.add(this.championAttackSpeedPanel, 7);
        statsLayer.add(this.championCritPanel, 8);
        statsLayer.add(this.championMovementSpeedPanel, 9);
        statsLayer.add(this.championLevelPanel, 10);

        interactiveLayer.add(this.interactivePanel, 1);

        pictureLayer.add(this.championIconPanel, 1);
        pictureLayer.add(this.championPassivePanel, 2);
        pictureLayer.add(this.championQPanel, 3);
        pictureLayer.add(this.championWPanel, 4);
        pictureLayer.add(this.championEPanel, 5);
        pictureLayer.add(this.championRPanel, 6);
        pictureLayer.add(this.item1Panel, 7);
        pictureLayer.add(this.item2Panel, 8);
        pictureLayer.add(this.item3Panel, 9);
        pictureLayer.add(this.item4Panel, 10);
        pictureLayer.add(this.item5Panel, 11);
        pictureLayer.add(this.item6Panel, 12);
        pictureLayer.add(this.item7Panel, 13);
        pictureLayer.add(this.sumKeyDPanel, 14);
        pictureLayer.add(this.sumKeyFPanel, 15);

        splashLayer.add(this.splashArtPanel, 1);

        selectorLayer.add(this.championSelectorPanel, 1);
        selectorLayer.add(this.itemSelectPanel, 2);
        selectorLayer.add(this.spellSelectorPanel, 3);

        informationLayer.add(this.ablitiesPanel, 1);
        informationLayer.setLocation((int) (this.screenWidth / 2 - 250), (int) (this.screenHeight / 2 - 100));

        Container mainViewDisplay = getContentPane();
        mainViewDisplay.add(selectorLayer);
        mainViewDisplay.add(informationLayer);
        mainViewDisplay.add(interactiveLayer);
        mainViewDisplay.add(statsLayer);
        mainViewDisplay.add(hudLayer);
        mainViewDisplay.add(pictureLayer);
        mainViewDisplay.add(splashLayer);

        //______________________________________________________________________
        this.pack();
    }

    /**
     * Updates The On Screen Stats For The Champion
     *
     * @param name The Name Of The Champion
     */
    public void updateChampion(String name) {
        //Getting Stats and Setting them to local vars
        String hp = "" + theBackendModel.champion.getBaseHP();
        String resource = "" + theBackendModel.champion.getBaseResource();
        String type = theBackendModel.champion.getResourceType();
        String ad = "" + theBackendModel.champion.getBaseAD();
        String ap = "" + theBackendModel.champion.getBaseAP();
        String armor = "" + theBackendModel.champion.getBaseArmor();
        String mr = "" + theBackendModel.champion.getBaseMR();
        String attackSpeed = "" + theBackendModel.champion.getAttackSpeed();
        String moveSpeed = "" + theBackendModel.champion.getMoveSpeed();
        String crit = "" + theBackendModel.champion.getBaseCrit();
        //______________________________________________________________________

        //Setting HUD to match resource type
        if (type.equalsIgnoreCase("mana")) {
            this.resorceStatsPanel.setVisible(true);
            this.resorceStatsPanel.setForeground(Color.WHITE);
            this.resorceStatsPanel.setVisible(true);
            this.hudDefualtLabel.setVisible(false);
            this.hudManaLabel.setVisible(true);
            this.hudRageLabel.setVisible(false);
            this.hudShieldLabel.setVisible(false);
            this.hudEnergyLabel.setVisible(false);
        } else if (type.equalsIgnoreCase("rage")) {
            this.resorceStatsPanel.setVisible(true);
            this.resorceStatsPanel.setForeground(Color.WHITE);
            this.resorceStatsPanel.setVisible(true);
            this.hudDefualtLabel.setVisible(false);
            this.hudManaLabel.setVisible(false);
            this.hudRageLabel.setVisible(true);
            this.hudShieldLabel.setVisible(false);
            this.hudEnergyLabel.setVisible(false);
        } else if (type.equalsIgnoreCase("shield")) {
            this.resorceStatsPanel.setVisible(true);
            this.resorceStatsPanel.setForeground(Color.BLACK);
            this.resorceStatsPanel.setVisible(true);
            this.hudDefualtLabel.setVisible(false);
            this.hudManaLabel.setVisible(false);
            this.hudRageLabel.setVisible(false);
            this.hudShieldLabel.setVisible(true);
            this.hudEnergyLabel.setVisible(false);
        } else if (type.equalsIgnoreCase("energy")) {
            this.resorceStatsPanel.setVisible(true);
            this.resorceStatsPanel.setForeground(Color.WHITE);
            this.resorceStatsPanel.setVisible(true);
            this.hudDefualtLabel.setVisible(false);
            this.hudManaLabel.setVisible(false);
            this.hudRageLabel.setVisible(false);
            this.hudShieldLabel.setVisible(false);
            this.hudEnergyLabel.setVisible(true);
        } else {
            this.resorceStatsPanel.setVisible(false);
            this.resorceStatsPanel.setVisible(false);
            this.hudDefualtLabel.setVisible(true);
            this.hudManaLabel.setVisible(false);
            this.hudRageLabel.setVisible(false);
            this.hudShieldLabel.setVisible(false);
            this.hudEnergyLabel.setVisible(false);
        }
        //______________________________________________________________________

        //Setting the Labels to the local vars
        this.championHPLabel.setText(hp + "/" + hp);
        this.championResourceLabel.setText("" + resource + "/" + resource);
        this.championADLabel.setText("" + ad);
        this.championAPLabel.setText("" + ap);
        this.championArmorLabel.setText("" + armor);
        this.championMRLabel.setText("" + mr);
        this.championAttackSpeedLabel.setText("" + attackSpeed);
        this.championMovementSpeedLabel.setText("" + moveSpeed);
        this.championCritLabel.setText("" + crit);
        //______________________________________________________________________

        //Setting new pictures
        //Main Icon
        try {
            this.championIcon = FindLocal.findIcon(name);
            this.championIconLabel.setIcon(new ImageIcon(this.championIcon));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S ICON");
            this.championIconLabel.setIcon(new ImageIcon(this.defualtIcon));
        }
        //Passive
        try {
            this.championPassive = FindLocal.findPassive(name);
            Image img = this.championPassive.getScaledInstance(57, 57, Image.SCALE_DEFAULT);
            this.championPassiveButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S PASSIVE");
            this.championPassiveButton.setIcon(new ImageIcon(this.defualtPassive));
        }
        //Q
        try {
            this.championQ = FindLocal.findQ(name);
            Image img = this.championQ.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            this.championQButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S Q");
            this.championQButton.setIcon(new ImageIcon(this.defualtQ));
        }
        //W
        try {
            this.championW = FindLocal.findW(name);
            Image img = this.championW.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            this.championWButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S W");
            this.championWButton.setIcon(new ImageIcon(this.defualtW));
        }
        //E
        try {
            this.championE = FindLocal.findE(name);
            Image img = this.championE.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            this.championEButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S E");
            this.championEButton.setIcon(new ImageIcon(this.defualtE));
        }
        //R
        try {
            this.championR = FindLocal.findR(name);
            Image img = this.championR.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
            this.championRButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S R");
            this.championRButton.setIcon(new ImageIcon(this.defualtR));
        }
        //Splash
        try {
            this.splashArt = FindLocal.findSplash(name);
            Image img = this.splashArt.getScaledInstance((int) this.screenWidth, (int) this.screenHeight, Image.SCALE_DEFAULT);
            this.splashArtLabel.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND " + name.toUpperCase() + "'S SPLASH ART");
            try {
                this.splashArt = ImageIO.read(new File("data\\HUD\\main.png"));
                Image img = this.splashArt.getScaledInstance((int) this.screenWidth, (int) this.screenHeight, Image.SCALE_DEFAULT);
                this.splashArtLabel.setIcon(new ImageIcon(img));
            } catch (IOException ex1) {
                Logger.log(MainViewDisplay.class.getName(), "COULD NOT FIND DEFUALT SPLASH ART");

            }
        }
    }

    /**
     * Updates The On Screen Icons For The Item(s)
     *
     * @param item
     */
    public void updateItems(int item) {
        this.itemSelectPanel.setVisible(false);
        List<String> items = ServerConnection.getCurrentItems();
        String itemName = items.get(item);
        switch (this.itemSlot) {
            case 1:
                this.item1Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
            case 2:
                this.item2Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
            case 3:
                this.item3Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
            case 4:
                this.item4Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 42, 42, itemName)));
                break;
            case 5:
                this.item5Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
            case 6:
                this.item6Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
            case 7:
                this.item7Button.setIcon(new ImageIcon(ImageBuilder.buildSizedImage("data\\Items\\" + itemName + "\\" + itemName + ".png", 52, 62, itemName)));
                break;
        }
    }

    /**
     * Changes And Scales All Stats Based On Level
     */
    public void setLevel() {
        int newLevel = Integer.parseInt(this.levelSelectArea.getText());
        if (newLevel > 18) {
            newLevel = 18;
        } else if (newLevel < 1) {
            newLevel = 1;
        }
        this.levelSelectArea.setText(newLevel + "");
        this.championLevelLabel.setText("" + newLevel);
        this.championADLabel.setText("" + (theBackendModel.champion.getBaseAD() + ((newLevel - 1) * theBackendModel.champion.getAdScale())));
        this.championArmorLabel.setText("" + (theBackendModel.champion.getBaseArmor() + ((newLevel - 1) * theBackendModel.champion.getArmorScale())));
        this.championCritLabel.setText("" + (theBackendModel.champion.getBaseCrit() + ((newLevel - 1) * theBackendModel.champion.getCritScale())));
        int hp = theBackendModel.champion.getBaseHP() + ((newLevel - 1) * theBackendModel.champion.getHPScale());
        this.championHPLabel.setText("" + hp + "/" + hp);
        this.championAttackSpeedLabel.setText("" + (theBackendModel.champion.getAttackSpeed() + ((newLevel - 1) * theBackendModel.champion.getAttackSpeedScale())));
        this.championMRLabel.setText("" + (theBackendModel.champion.getBaseMR() + ((newLevel - 1) * theBackendModel.champion.getMRScale())));
        int re = theBackendModel.champion.getBaseResource() + ((newLevel - 1) * theBackendModel.champion.getResourceScale());
        this.championResourceLabel.setText("" + re + "/" + re);
        updateStats();
    }

    /**
     * Creates The Button Pages For Champions And Items
     */
    private void createSelectors() {
        ServerConnection.offlineBackup();
        List<String> roster = ServerConnection.getCurrentChampionRoster();
        this.championButtons = new JButton[roster.size() + 1];
        int size = (int) (this.screenHeight / 13.5);
        for (int indx = 0; indx < roster.size(); indx++) {
            Image img = ImageBuilder.buildSizedImage("data\\Icons\\" + roster.get(indx) + "Icon.png", size, size, roster.get(indx));
            this.championButtons[indx] = new JButton(new ImageIcon(img));
            this.championButtons[indx].setBackground(Color.BLACK);
            this.championButtons[indx].setToolTipText(roster.get(indx));
        }

        List<String> items = ServerConnection.getCurrentItems();
        this.itemButtons = new JButton[items.size()];
        int scale = (int) (this.screenHeight / 20);
        for (int in = 0; in < items.size(); in++) {
            Image img = ImageBuilder.buildSizedImage("data\\Items\\" + items.get(in) + "\\" + items.get(in) + ".png", scale, scale, items.get(in));
            if (img == null) {
                Image back = ImageBuilder.buildSizedImage("data\\HUD\\passive.png", scale, scale, "Defualt");
                this.itemButtons[in] = new JButton(new ImageIcon(back));
            } else {
                this.itemButtons[in] = new JButton(new ImageIcon(img));
            }
            this.itemButtons[in].setBackground(Color.BLACK);
            this.itemButtons[in].setToolTipText(items.get(in));
        }

        List<String> spells = ServerConnection.getCurrentSpells();
        this.spellButtons = new JButton[spells.size() +1 ];
        size = 52;
        for (int i = 0; i < spells.size(); i++) {
            Image img = ImageBuilder.buildSizedImage("data\\Spells\\" + spells.get(i) + "\\" + spells.get(i) + ".png", size, size, spells.get(i));
            if (img == null) {
                Image back = ImageBuilder.buildSizedImage("data\\HUD\\passive.png", size, size, "Defualt");
                this.spellButtons[i] = new JButton(new ImageIcon(back));
            } else {
                this.spellButtons[i] = new JButton(new ImageIcon(img));
            }
            this.spellButtons[i].setBackground(Color.BLACK);
            this.spellButtons[i].setToolTipText(spells.get(i));

        }
    }

    /**
     * Searches The Item List For A Item That Contains The Given Text
     */
    public void searchItems() {
        String src = this.itemSearchArea.getText();
        List items = ServerConnection.getCurrentItems();
        if (src.equalsIgnoreCase("")) {
            for (int i = 0; i < items.size(); i++) {
                this.itemButtons[i].setVisible(true);
            }
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().contains(src)) {
                this.itemButtons[i].setVisible(true);
            } else {
                this.itemButtons[i].setVisible(false);
            }
        }
    }

    /**
     * Updates the spells on the screen
     * @param name The Name of the Spell
     */
    public void updateSpells(String name) {
        Image img = ImageBuilder.buildSizedImage("data\\Spells\\" + name + "\\" + name + ".png", 52, 52, name);

        switch (this.spellSlot) {
            case 0:
                this.sumKeyD.setIcon(new ImageIcon(img));
                break;
            case 1:
                this.sumKeyF.setIcon(new ImageIcon(img));
                break;
        }
    }

    /**
     * Updates All on Screen Stats
     */
    public void updateStats() {
        int lvl = Integer.parseInt(this.levelSelectArea.getText()) - 1;

        int hp = theBackendModel.champion.getBaseHP() + (lvl * theBackendModel.champion.getHPScale());
        if (theBackendModel.item1 != null) {
            hp = hp + theBackendModel.item1.getHpFactor();
        }
        if (theBackendModel.item2 != null) {
            hp = hp + theBackendModel.item2.getHpFactor();
        }
        if (theBackendModel.item3 != null) {
            hp = hp + theBackendModel.item3.getHpFactor();
        }

        if (theBackendModel.item5 != null) {
            hp = hp + theBackendModel.item5.getHpFactor();
        }
        if (theBackendModel.item6 != null) {
            hp = hp + theBackendModel.item6.getHpFactor();
        }
        if (theBackendModel.item7 != null) {
            hp = hp + theBackendModel.item7.getHpFactor();
        }

        int ad = theBackendModel.champion.getBaseAD() + (lvl * theBackendModel.champion.getAdScale());
        if (theBackendModel.item1 != null) {
            ad = ad + theBackendModel.item1.getAdFactor();
        }
        if (theBackendModel.item2 != null) {
            ad = ad + theBackendModel.item2.getAdFactor();
        }
        if (theBackendModel.item3 != null) {
            ad = ad + theBackendModel.item3.getAdFactor();
        }

        if (theBackendModel.item5 != null) {
            ad = ad + theBackendModel.item5.getAdFactor();
        }
        if (theBackendModel.item6 != null) {
            ad = ad + theBackendModel.item6.getAdFactor();
        }
        if (theBackendModel.item7 != null) {
            ad = ad + theBackendModel.item7.getAdFactor();
        }

        int ap = theBackendModel.champion.getBaseAP();
        if (theBackendModel.item1 != null) {
            ap = ap + theBackendModel.item1.getApFactor();
        }
        if (theBackendModel.item2 != null) {
            ap = ap + theBackendModel.item2.getApFactor();
        }
        if (theBackendModel.item3 != null) {
            ap = ap + theBackendModel.item3.getApFactor();
        }
        if (theBackendModel.item5 != null) {
            ap = ap + theBackendModel.item5.getApFactor();
        }
        if (theBackendModel.item6 != null) {
            ap = ap + theBackendModel.item6.getApFactor();
        }
        if (theBackendModel.item7 != null) {
            ap = ap + theBackendModel.item7.getApFactor();
        }

        double as = theBackendModel.champion.getAttackSpeed() + (lvl * theBackendModel.champion.getAttackSpeedScale());
        if (theBackendModel.item1 != null) {
            as = as + (theBackendModel.item1.getAttackSpeedFactor() * as);
        }
        if (theBackendModel.item2 != null) {
            as = as + (theBackendModel.item2.getAttackSpeedFactor() * as);
        }
        if (theBackendModel.item3 != null) {
            as = as + (theBackendModel.item3.getAttackSpeedFactor() * as);
        }
        if (theBackendModel.item5 != null) {
            as = as + (theBackendModel.item5.getAttackSpeedFactor() * as);
        }
        if (theBackendModel.item6 != null) {
            as = as + (theBackendModel.item6.getAttackSpeedFactor() * as);
        }
        if (theBackendModel.item7 != null) {
            as = as + (theBackendModel.item7.getAttackSpeedFactor() * as);
        }

        int crit = theBackendModel.champion.getBaseAP() + (lvl * theBackendModel.champion.getBaseCrit());
        if (theBackendModel.item1 != null) {
            crit = crit + theBackendModel.item1.getCritFactor();
        }
        if (theBackendModel.item2 != null) {
            crit = crit + theBackendModel.item2.getCritFactor();
        }
        if (theBackendModel.item3 != null) {
            crit = crit + theBackendModel.item3.getCritFactor();
        }
        if (theBackendModel.item5 != null) {
            crit = crit + theBackendModel.item5.getCritFactor();
        }
        if (theBackendModel.item6 != null) {
            crit = crit + theBackendModel.item6.getCritFactor();
        }
        if (theBackendModel.item7 != null) {
            crit = crit + theBackendModel.item7.getCritFactor();
        }

        double armor = theBackendModel.champion.getBaseArmor() + (lvl * theBackendModel.champion.getArmorScale());
        if (theBackendModel.item1 != null) {
            armor = armor + theBackendModel.item1.getArmorFactor();
        }
        if (theBackendModel.item2 != null) {
            armor = armor + theBackendModel.item2.getArmorFactor();
        }
        if (theBackendModel.item3 != null) {
            armor = armor + theBackendModel.item3.getArmorFactor();
        }
        if (theBackendModel.item5 != null) {
            armor = armor + theBackendModel.item5.getArmorFactor();
        }
        if (theBackendModel.item6 != null) {
            armor = armor + theBackendModel.item6.getArmorFactor();
        }
        if (theBackendModel.item7 != null) {
            armor = armor + theBackendModel.item7.getArmorFactor();
        }

        double mr = theBackendModel.champion.getBaseMR() + (lvl * theBackendModel.champion.getMRScale());
        if (theBackendModel.item1 != null) {
            mr = mr + theBackendModel.item1.getMrFactor();
        }
        if (theBackendModel.item2 != null) {
            mr = mr + theBackendModel.item2.getMrFactor();
        }
        if (theBackendModel.item3 != null) {
            mr = mr + theBackendModel.item3.getMrFactor();
        }
        if (theBackendModel.item5 != null) {
            mr = mr + theBackendModel.item5.getMrFactor();
        }
        if (theBackendModel.item6 != null) {
            mr = mr + theBackendModel.item6.getMrFactor();
        }
        if (theBackendModel.item7 != null) {
            mr = mr + theBackendModel.item7.getMrFactor();
        }

        this.championMRLabel.setText("" + mr);
        this.championAPLabel.setText("" + ap);
        this.championADLabel.setText("" + ad);
        this.championHPLabel.setText("" + hp + "/" + hp);
        this.championArmorLabel.setText("" + armor);
        if (crit > 100) {
            this.championCritLabel.setText("100");
        } else {
            this.championCritLabel.setText("" + crit);
        }
        if (as > 2.5) {
            this.championAttackSpeedLabel.setText("2.5");
        } else {
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(3);
            nf.setMinimumFractionDigits(3);
            as = Double.parseDouble(nf.format(as));
            this.championAttackSpeedLabel.setText("" + as);
        }
    }

    /**
     * Resets the items
     */
    public void resetItems() {
        Image add = ImageBuilder.buildSizedImage("data\\HUD\\add.png", 52, 52, "ADD");
        this.item1Button.setIcon(new ImageIcon(add));
        this.item2Button.setIcon(new ImageIcon(add));
        this.item3Button.setIcon(new ImageIcon(add));
        this.item5Button.setIcon(new ImageIcon(add));
        this.item6Button.setIcon(new ImageIcon(add));
        this.item7Button.setIcon(new ImageIcon(add));
        add = ImageBuilder.buildSizedImage("data\\HUD\\add.png", 42, 42, "ADD");
        this.item4Button = new JButton(new ImageIcon(add));
        theBackendModel.item1 = null;
        theBackendModel.item2 = null;
        theBackendModel.item3 = null;
        theBackendModel.item4 = null;
        theBackendModel.item5 = null;
        theBackendModel.item6 = null;
        theBackendModel.item7 = null;
        updateStats();
    }

    /**
     * Creates The Selector Page For Champions
     *
     * @param curr The Current X-Position
     * @param lastX The Last X-Position
     */
    private void createSelectorPanel(int curr, int lastX) {
        if (curr == this.championButtons.length) {
            return;
        } else {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = lastX + 1;
            c.gridy = 0;
            this.championSelectorPanel.add(this.championButtons[curr - 1], c);
            createSelectorPanel(curr + 1, lastX + 1);
        }
    }

    /**
     * Creates The Selector Page For Items
     *
     * @param curr The Current X-Position
     * @param lastX The Last X-Position
     */
    private void createItemPanel(int curr, int lastX) {
        if (curr == this.itemButtons.length) {
            return;
        } else {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = lastX + 1;
            c.gridy = 0;
            this.itemSelectPanel.add(this.itemButtons[curr - 1], c);
            createItemPanel(curr + 1, lastX + 1);
        }
    }

    /**
     * Creates The Selector Page For Spells
     * @param curr current button
     * @param lastX 
     */
    private void createSpellPanel(int curr, int lastX) {
        if (curr == this.spellButtons.length) {
            return;
        } else {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = lastX + 1;
            c.gridy = 0;
            this.spellSelectorPanel.add(this.spellButtons[curr - 1], c);
            createSpellPanel(curr + 1, lastX + 1);
        }
    }
}
