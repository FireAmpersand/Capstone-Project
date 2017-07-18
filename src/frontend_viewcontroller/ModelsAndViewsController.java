package frontend_viewcontroller;

import backend_models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModelsAndViewsController {

    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;

    /**
     * Creates The ModelsAndViewController Object
     *
     * @param aBackend The BackendModelSetup Object
     * @param aMainViewDisplay The MainViewDisplay Object
     */
    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();
    }

    /**
     * Creates All The Items With ActionListeners
     */
    private void initController() {
        for (int i = 0; i < ServerConnection.getCurrentChampionRoster().size(); i++) {
            theMainViewDisplay.championButtons[i].addActionListener(new ChooseChampion(i));
        }
        for (int i = 0; i < ServerConnection.getCurrentItems().size(); i++) {
            theMainViewDisplay.itemButtons[i].addActionListener(new ChooseItem(i));
        }
        for (int i = 0; i < ServerConnection.getCurrentSpells().size(); i++) {
            theMainViewDisplay.spellButtons[i].addActionListener(new ChooseSpell(i));
        }
        theMainViewDisplay.championSelectButton.addActionListener(new OpenChampionSelectWindow());
        theMainViewDisplay.itemSearchButton.addActionListener(new SearchItems());
        theMainViewDisplay.championPassiveButton.addActionListener(new AblitiesInformation(0));
        theMainViewDisplay.championQButton.addActionListener(new AblitiesInformation(1));
        theMainViewDisplay.championWButton.addActionListener(new AblitiesInformation(2));
        theMainViewDisplay.championEButton.addActionListener(new AblitiesInformation(3));
        theMainViewDisplay.championRButton.addActionListener(new AblitiesInformation(4));
        theMainViewDisplay.updateLevelButton.addActionListener(new UpdateLevel());
        theMainViewDisplay.closeWindowButton.addActionListener(new CloseWindow());
        theMainViewDisplay.item1Button.addActionListener(new OpenItemSelectWindow(1));
        theMainViewDisplay.item2Button.addActionListener(new OpenItemSelectWindow(2));
        theMainViewDisplay.item3Button.addActionListener(new OpenItemSelectWindow(3));
        theMainViewDisplay.item4Button.addActionListener(new OpenItemSelectWindow(4));
        theMainViewDisplay.item5Button.addActionListener(new OpenItemSelectWindow(5));
        theMainViewDisplay.item6Button.addActionListener(new OpenItemSelectWindow(6));
        theMainViewDisplay.item7Button.addActionListener(new OpenItemSelectWindow(7));
        theMainViewDisplay.clearItemsButton.addActionListener(new ResetItems());
        theMainViewDisplay.sumKeyD.addActionListener(new OpenSpellSelectWindow(0));
        theMainViewDisplay.sumKeyF.addActionListener(new OpenSpellSelectWindow(1));

    }

    private class ChooseSpell implements ActionListener {

        private int spell;

        public ChooseSpell(int i) {
            this.spell = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> spells = ServerConnection.getCurrentSpells();
            String name = spells.get(spell);
            theMainViewDisplay.updateSpells(name);
            theMainViewDisplay.spellSelectorPanel.setVisible(false);
        }
    }

    private class OpenSpellSelectWindow implements ActionListener {

        int slot;

        public OpenSpellSelectWindow(int i) {
            slot = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.spellSelectorPanel.setVisible(true);
            theMainViewDisplay.spellSlot = slot;
        }
    }

    /**
     * The Abilities Information Function
     */
    private class AblitiesInformation implements ActionListener {

        String type;

        AblitiesInformation(int type) {
            if (type == 0) {
                this.type = "Passive";
            } else if (type == 1) {
                this.type = "Q";
            } else if (type == 2) {
                this.type = "W";
            } else if (type == 3) {
                this.type = "E";
            } else if (type == 4) {
                this.type = "R";
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theMainViewDisplay.ablitiesPanel.isVisible()) {
                theMainViewDisplay.ablitiesPanel.setVisible(false);
            } else {
                theMainViewDisplay.ablitiesPanel.setVisible(true);
            }
            switch (this.type) {
                case "Passive":
                    theMainViewDisplay.titleLabel.setText(theBackendModel.champion.getPassiveName());
                    if (theBackendModel.champion.getPassiveTxt().equals("")) {
                        theMainViewDisplay.abilityTextArea.setText("CHAMPION PASSIVE NOT AVAILABE");
                        break;
                    }
                    theMainViewDisplay.abilityTextArea.setText(theBackendModel.champion.getPassiveTxt());
                    break;
                case "Q":
                    theMainViewDisplay.titleLabel.setText(theBackendModel.champion.getQName());
                    if (theBackendModel.champion.getQTxt().equals("")) {
                        theMainViewDisplay.abilityTextArea.setText("CHAMPION Q NOT AVAILABE");
                        break;
                    }
                    theMainViewDisplay.abilityTextArea.setText(theBackendModel.champion.getQTxt());
                    break;
                case "W":
                    theMainViewDisplay.titleLabel.setText(theBackendModel.champion.getWName());
                    if (theBackendModel.champion.getWTxt().equals("")) {
                        theMainViewDisplay.abilityTextArea.setText("CHAMPION W NOT AVAILABE");
                        break;
                    }
                    theMainViewDisplay.abilityTextArea.setText(theBackendModel.champion.getWTxt());
                    break;
                case "E":
                    theMainViewDisplay.titleLabel.setText(theBackendModel.champion.getEName());
                    if (theBackendModel.champion.getETxt().equals("")) {
                        theMainViewDisplay.abilityTextArea.setText("CHAMPION E NOT AVAILABE");
                        break;
                    }
                    theMainViewDisplay.abilityTextArea.setText(theBackendModel.champion.getETxt());
                    break;
                case "R":
                    theMainViewDisplay.titleLabel.setText(theBackendModel.champion.getRName());
                    if (theBackendModel.champion.getRTxt().equals("")) {
                        theMainViewDisplay.abilityTextArea.setText("CHAMPION E NOT AVAILABE");
                        break;
                    }
                    theMainViewDisplay.abilityTextArea.setText(theBackendModel.champion.getRTxt());
                    break;
            }
        }
    }

    /**
     * The Selecting Champion Function
     */
    private class ChooseChampion implements ActionListener {

        private int num;

        ChooseChampion(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.championSelectorPanel.setVisible(false);
            List roster = ServerConnection.getCurrentChampionRoster();
            String name = roster.get(num).toString();
            theBackendModel.champion = new Champion(name);

            theBackendModel.champion.update();
            theMainViewDisplay.levelSelectArea.setText("1");
            theMainViewDisplay.updateChampion(name);
        }
    }

    /**
     * The Selecting Item Function
     */
    private class ChooseItem implements ActionListener {

        private int slot;

        public ChooseItem(int itemSlot) {
            this.slot = itemSlot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.updateItems(slot);
            List<String> items = ServerConnection.getCurrentItems();
            String name = items.get(slot);
            switch (theMainViewDisplay.itemSlot) {
                case 1:
                    theBackendModel.item1 = new Item(name);
                    break;

                case 2:
                    theBackendModel.item2 = new Item(name);
                    break;

                case 3:
                    theBackendModel.item3 = new Item(name);
                    break;

                case 4:
                    theBackendModel.item4 = new Item(name);
                    break;

                case 5:
                    theBackendModel.item5 = new Item(name);
                    break;

                case 6:
                    theBackendModel.item6 = new Item(name);
                    break;

                case 7:
                    theBackendModel.item7 = new Item(name);
                    break;
            }
            theMainViewDisplay.updateStats();
        }

    }

    /**
     * The Close Window Function
     */
    private class CloseWindow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.ablitiesPanel.setVisible(false);
        }

    }

    /**
     * The Champion Selection Open Function
     */
    private class OpenChampionSelectWindow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theMainViewDisplay.championSelectorPanel.setVisible(true);
        }

    }

    /**
     * The Item Selection Open Function
     */
    private class OpenItemSelectWindow implements ActionListener {

        private int slot;

        public OpenItemSelectWindow(int itemSlot) {
            this.slot = itemSlot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.itemSlot = this.slot;
            if (this.slot == 4) {
                //Code For Slot 4 HEre
            }
            theMainViewDisplay.itemSelectPanel.setVisible(true);
        }

    }

    /**
     * The Search Items Function
     */
    private class SearchItems implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.searchItems();
        }

    }

    private class ResetItems implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theMainViewDisplay.resetItems();
        }

    }

    /**
     * The Update Level Function
     */
    private class UpdateLevel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            theMainViewDisplay.setLevel();

        }

    }

}
