/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.io.FileWriter;
import java.io.IOException;
import Logger.Logger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author Zach
 */
public class SaveInformation {

    /**
     * Saves the names of the champion's abilities to the device
     *
     * @param names The array of names being saved
     * @param name The name of the champion
     */
    public static void saveAbilitesNamesToDevice(String[] names, String name) {
        try {
            FileWriter fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\passiveName.txt"));
            PrintWriter out = new PrintWriter(fw, true);
            out.println(names[0]);

            fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\qName.txt"));
            out = new PrintWriter(fw, true);
            out.println(names[1]);

            fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\wName.txt"));
            out = new PrintWriter(fw, true);
            out.println(names[2]);

            fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\eName.txt"));
            out = new PrintWriter(fw, true);
            out.println(names[3]);

            fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\rName.txt"));
            out = new PrintWriter(fw, true);
            out.println(names[4]);

            out.close();
            fw.close();
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND .txt FILE");
        }
    }

    /**
     * Saves The Ability Text To The Device
     *
     * @param txtArr The Text Array
     */
    public static void saveAbilitiesTextToDevice(String[] txtArr, String name) {
        String baseFilePath = "data\\Files\\" + name + "\\Information\\";
        try {
            FileWriter fw = new FileWriter(new File(baseFilePath + "passive.txt"));
            PrintWriter out = new PrintWriter(fw, true);
            out.println(txtArr[0]);

            fw.close();
            out.close();

            fw = new FileWriter(new File(baseFilePath + "q.txt"));
            out = new PrintWriter(fw, true);
            out.println(txtArr[1]);

            fw.close();
            out.close();

            fw = new FileWriter(new File(baseFilePath + "w.txt"));
            out = new PrintWriter(fw, true);
            out.println(txtArr[2]);

            fw.close();
            out.close();

            fw = new FileWriter(new File(baseFilePath + "e.txt"));
            out = new PrintWriter(fw, true);
            out.println(txtArr[3]);

            fw.close();
            out.close();

            fw = new FileWriter(new File(baseFilePath + "r.txt"));
            out = new PrintWriter(fw, true);
            out.println(txtArr[4]);

            fw.close();
            out.close();

        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND SAVE ABILITY TEXT");
        }
    }

    /**
     * Saves The ID Of A Champion To The Device For Local Use.
     *
     * @param id The ID Being Saved
     * @param name The Name Of The Champion.
     */
    public static void saveIDToChampion(String id, String name) {
        try {
            FileWriter fw = new FileWriter(new File("data\\Files\\" + name + "\\Information\\ID.txt"));
            PrintWriter out = new PrintWriter(fw, true);
            out.println(id);
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND ID.txt");
        }
    }

    /**
     * Saves The Icons Of The Champions To The Device For Local Use.
     *
     * @param img The Image Being Saved
     * @param name The Name Of The Champion
     */
    public static void saveIconsToDevice(BufferedImage img, String name) {
        File check = new File("data\\Files\\" + name);
        if (!check.exists()) {
            check.mkdir();
        }

        File icon = new File("data\\Icons\\" + name + "Icon.png");
        if (img == null) {
            Logger.log(SaveInformation.class.getName(), "ICON PICTURE NOT FOUND");
            return;
        }
        try {
            ImageIO.write(img, "png", icon);
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE ICON");
        }
    }

    /**
     * Saves A Item To A List Then To The Device For Local Use.
     *
     * @param itemName Name Of The Items Being Saved.
     */
    public static void saveItemListToDevice(String itemName) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File("data\\appData\\items.txt"), true);
            PrintWriter out = new PrintWriter(fw, true);
            out.print(itemName + ",");
            out.close();
            fw.close();
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE " + itemName.toUpperCase() + "TO FILE");
        }
    }

    /**
     * Saves The Current Item To The Device Along With Its Information.
     *
     * @param itemName Name Of The Item.
     * @param id ID Of The Item.
     * @param description The Description Of The Item.
     * @param img The Item Image.
     */
    public static void saveItemsToDevice(String itemName, int id, String description, BufferedImage img) {
        File filePath = new File("data\\Items\\" + itemName);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(filePath + "\\id.txt");
            PrintWriter out = new PrintWriter(fw, true);
            out.println(id);

            fw = new FileWriter(filePath + "\\info.txt");
            out = new PrintWriter(fw, true);
            out.println(description);
            out.close();
            fw.close();

            String path = "data\\Items\\" + itemName + "\\" + itemName + ".png";
            File imgPath = new File(path);
            ImageIO.write(img, "png", imgPath);
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND ITEM FILE");
        }
    }

    /**
     * Saves The Ability Pictures To The Device For Local Use.
     *
     * @param imgs The Images Being Saved.
     * @param name The Name Of The Champion
     */
    public static void savePictureToDevice(BufferedImage[] imgs, String name) {
        File passive = new File("data\\Files\\" + name + "\\" + name + "Passive.png");
        File q = new File("data\\Files\\" + name + "\\" + name + "Q.png");
        File w = new File("data\\Files\\" + name + "\\" + name + "W.png");
        File e = new File("data\\Files\\" + name + "\\" + name + "E.png");
        File r = new File("data\\Files\\" + name + "\\" + name + "R.png");
        if (imgs[0] == null || imgs[1] == null || imgs[2] == null || imgs[3] == null || imgs[4] == null) {
            String imgsNotFound = "";
            if (imgs[0] == null) {
                imgsNotFound = "passive";
            }
            if (imgs[1] == null) {
                imgsNotFound = imgsNotFound + ",q";
            }
            if (imgs[2] == null) {
                imgsNotFound = imgsNotFound + ",w";
            }
            if (imgs[3] == null) {
                imgsNotFound = imgsNotFound + ",e";
            }
            if (imgs[4] == null) {
                imgsNotFound = imgsNotFound + "r";
            }
            Logger.log(SaveInformation.class.getName(), "ONE OR MORE PICTURES WEREN'T FOUND" + "(" + imgsNotFound + ")");
        }
        try {
            if (!(imgs[0] == null)) {
                ImageIO.write(imgs[0], "png", passive);
            }
            if (!(imgs[1] == null)) {
                ImageIO.write(imgs[1], "png", q);
            }
            if (!(imgs[2] == null)) {
                ImageIO.write(imgs[2], "png", w);
            }
            if (!(imgs[3] == null)) {
                ImageIO.write(imgs[3], "png", e);
            }
            if (!(imgs[4] == null)) {
                ImageIO.write(imgs[4], "png", r);
            }
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE PICTURE");
        }
    }

    /**
     * Saves The Updated Roster To The Device
     *
     * @param name The Name Being Saved
     */
    public static void saveRosterToDevice(String name) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File("data\\appData\\champions.txt"), true);
            PrintWriter out = new PrintWriter(fw, true);
            out.print(name + ",");
            out.close();
            fw.close();
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE " + name.toUpperCase() + "TO FILE");
        }
    }

    /**
     * Saves The Champion Splash Art To The Device
     *
     * @param img The Image Being Saved
     * @param name The Name Of The Champion
     */
    public static void saveSplashArtToDevice(BufferedImage img, String name) {
        File splash = new File("data\\Files\\" + name + "\\Splash.png");
        if (img == null) {
            Logger.log(SaveInformation.class.getName(), name);
            return;
        }
        try {
            ImageIO.write(img, "png", splash);
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE SPLASH");
        }
    }

    /**
     * Saves The Champion Stats To The Device
     *
     * @param stats The Array Of Stats Being Saved
     * @param name The Name Of The Champion
     */
    public static void saveStatsToDevice(double[] stats, String name) {
        try {
            FileWriter fw = new FileWriter("data\\Files\\" + name + "\\Information\\HP.txt");
            PrintWriter out = new PrintWriter(fw, true);
            out.println(stats[0]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\AD.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[1]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\AttackSpeed.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[2]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\MS.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[3]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\Armor.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[4]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\MR.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[5]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\AttackRange.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[6]);

            /*fw = new FileWriter("Files\\" + name + "\\Information\\Resource.txt");
             out = new PrintWriter(fw,true);
             out.println(stats[7]);*/ //Don't use this yet
            fw = new FileWriter("data\\Files\\" + name + "\\Information\\ResourcePerLevel.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[8]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\HPScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[9]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\ADScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[10]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\HPRegenScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[11]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\HPRegen.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[12]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\CritScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[13]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\MRScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[14]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\ResourceRegenScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[15]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\AttackSpeedScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[16]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\Crit.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[17]);

            fw = new FileWriter("dsta\\Files\\" + name + "\\Information\\HPScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[18]);

            fw = new FileWriter("data\\Files\\" + name + "\\Information\\ArmorScaling.txt");
            out = new PrintWriter(fw, true);
            out.println(stats[19]);

            fw.close();
            out.close();
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND FILE");
        }
    }

    /**
     * Saves The Spell List To The Device
     * @param name Name Of The Spell
     */
    public static void saveSpellsListToDevice(String name) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File("data\\appData\\spells.txt"), true);
            PrintWriter out = new PrintWriter(fw, true);
            out.print(name + ",");
            out.close();
            fw.close();
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO SAVE " + name.toUpperCase() + "TO FILE");
        }

    }

    /**
     * Saves The Spell To The Device 
     * @param name The Name Of The Spell
     * @param id The ID Of The Spell
     * @param description The Desciption Of The Spell
     * @param img The Image For The Spell
     */
    public static void saveSpellToDevice(String name, String id, String description, BufferedImage img) {
        File filePath = new File("data\\Spells\\" + name);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(filePath + "\\id.txt");
            PrintWriter out = new PrintWriter(fw, true);
            out.println(id);

            fw = new FileWriter(filePath + "\\info.txt");
            out = new PrintWriter(fw, true);
            out.println(description);
            out.close();
            fw.close();

            String path = "data\\Spells\\" + name + "\\" + name + ".png";
            File imgPath = new File(path);
            ImageIO.write(img, "png", imgPath);
        } catch (IOException ex) {
            Logger.log(SaveInformation.class.getName(), "FAILED TO FIND ITEM FILE");
        }
    }

}
