package backend_models;

import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import Logger.Logger;
import java.math.BigDecimal;
import java.math.MathContext;

public class FindLocal {

    /**
     * Searches the local directories and folders to find the champions's Base
     * Attack Damage.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Attack Damage.
     */
    public static int findAD(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\AD.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double ad = Double.parseDouble(adStr);
            int iAD = (int) Math.round(ad);
            return iAD;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S AD FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Ability Power.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Ability Power.
     */
    public static int findAP(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\AP.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String apStr = read.nextLine();
            double ap = Double.parseDouble(apStr);
            int iAP = (int) Math.round(ap);
            return iAP;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S AP FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Armor.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Armor.
     */
    public static double findArmor(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\Armor.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String armorStr = read.nextLine();
            double armor = Double.parseDouble(armorStr);
            return armor;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ARMOR FILE");
            return 0.0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Attack Speed.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Attack Speed.
     */
    public static double findAttackSpeed(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\AttackSpeed.txt");
        try {
            Scanner read = new Scanner(theFile);
            String atSpd = read.nextLine();
            double atSped = Double.parseDouble(atSpd);
            BigDecimal bd = new BigDecimal(atSped);
            bd = bd.round(new MathContext(3));
            return bd.doubleValue();
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S ATTACK SPEED FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Critical Strike Chance.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Critical Strike Chance.
     */
    public static int findCritChance(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\Crit.txt");
        try {
            Scanner read = new Scanner(theFile);
            String critStr = read.nextLine();
            int crit = (int) Double.parseDouble(critStr);
            return crit;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S MOVE SPEED FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's E
     * Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns E Icon Image.
     * @throws IOException
     */
    public static BufferedImage findE(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\" + filePath + "E.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champion's E Name
     *
     * @param filePath The File Location
     * @return The E Name
     */
    public static String findEName(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\eName.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            return read.nextLine();

        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S E NAME FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's E
     * Description.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns E Description.
     */
    public static String findEText(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Files\\" + filePath + "\\Information\\e.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt = txt + " " + read.next();
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S E FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Hit Points.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Hit Points.
     */
    public static int findHP(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\HP.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String hpStr = read.nextLine();
            double hp = Double.parseDouble(hpStr);
            int iHP = (int) Math.round(hp);
            return iHP;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S HP FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's ID.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns The ID Of The Champion.
     */
    public static int findID(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\ID.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String idStr = read.nextLine();
            int id = Integer.parseInt(idStr);
            return id;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Icon Image.
     * @throws IOException
     */
    public static BufferedImage findIcon(String filePath) throws IOException {
        File theFile = new File("data\\Icons\\" + filePath + "Icon.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the item's
     * Information.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns The Item's Information.
     */
    public static String findItemInfo(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Items\\" + filePath + "\\info.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt += " " + read.next();
            }

            //txt = txt.replaceAll("\\<.*?>", "");
            return txt;
        } catch (IOException ex) {
            Logger.log(FindLocal.class.getName(), "COULD NOT FIND ITEM ino.txt");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Magic Resist.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Magic Resist.
     */
    public static double findMR(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\MR.txt");
        try {
            Scanner read = new Scanner(theFile);
            String mrStr = read.nextLine();
            double mr = Double.parseDouble(mrStr);
            return mr;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S MR FILE");
            return 0.0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Movement Speed.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Movement Speed.
     */
    public static int findMoveSpeed(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\MS.txt");
        try {
            Scanner read = new Scanner(theFile);
            String mvSpd = read.nextLine();
            double mvSped = Double.parseDouble(mvSpd);
            int ms = (int) Math.round(mvSped);
            return ms;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S MOVE SPEED FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's
     * Passive Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Passive Icon Image.
     * @throws IOException
     */
    public static BufferedImage findPassive(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\" + filePath + "Passive.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champion's Passive
     * Name
     *
     * @param filePath The location of the file
     * @return The Passive Name
     */
    public static String findPassiveName(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\passiveName.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            return read.nextLine();

        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S PASSIVE NAME FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's
     * Passive Description.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Passive Description.
     */
    public static String findPassiveText(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Files\\" + filePath + "\\Information\\passive.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt = txt + " " + read.next();
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S PASSIVE FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Q
     * Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Q Icon Image.
     * @throws IOException
     */
    public static BufferedImage findQ(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\" + filePath + "Q.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champion's Q Name
     *
     * @param filePath The location of the file
     * @return The Q Name
     */
    public static String findQName(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\qName.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            return read.nextLine();

        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S Q NAME FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Q
     * Description.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Q Description.
     */
    public static String findQText(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Files\\" + filePath + "\\Information\\q.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt = txt + " " + read.next();
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S Q FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's R
     * Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns R Icon Image.
     * @throws IOException
     */
    public static BufferedImage findR(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\" + filePath + "R.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champion's R Name
     * @param filePath The location of the file
     * @return The R Name
     */
    public static String findRName(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\rName.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            return read.nextLine();

        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S R NAME FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's R
     * Description.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns R Description.
     */
    public static String findRText(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Files\\" + filePath + "\\Information\\r.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt = txt + " " + read.next();
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO " + filePath.toUpperCase() + "'S FIND R FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Base
     * Resource.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Base Resource.
     */
    public static String findResource(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\Resource.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String resource = read.nextLine();
            return resource;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S RESOURCE FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Attack
     * Damage Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Attack Damage Per Level.
     */
    public static int findScaleAd(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\ADScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double ad = Double.parseDouble(adStr);
            int iAD = (int) Math.round(ad);
            return iAD;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Armor
     * Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Armor Per Level.
     */
    public static double findScaleArmor(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\ArmorScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double ad = Double.parseDouble(adStr);
            return ad;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Attack
     * Speed Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Attack Speed Per Level.
     */
    public static int findScaleAttackSpeed(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\AttackSpeedScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double ad = Double.parseDouble(adStr);
            int iAD = (int) Math.round(ad);
            return iAD;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's
     * Critical Strike Chance Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Critical Strike Chance Per Level.
     */
    public static int findScaleCrit(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\CritScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double ad = Double.parseDouble(adStr);
            int iAD = (int) Math.round(ad);
            return iAD;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Hit
     * Points Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Hit Points Per Level.
     */
    public static int findScaleHp(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\HPScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double hp = Double.parseDouble(adStr);
            int iHP = (int) Math.round(hp);
            return iHP;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Magic
     * Resist Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Magic Resist Per Level.
     */
    public static double findScaleMr(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\MRScaling.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double mr = Double.parseDouble(adStr);
            return mr;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's
     * Resource Per Level.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Resource Per Level.
     */
    public static int findScaleResource(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\ResourcePerLevel.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            String adStr = read.nextLine();
            double re = Double.parseDouble(adStr);
            int iRe = (int) Math.round(re);
            return iRe;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S ID FILE");
            return 0;
        }
    }

    /**
     * Searches the local directories and folders to find the champions's Splash
     * Art.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns Splash Art Image.
     * @throws IOException
     */
    public static BufferedImage findSplash(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\Splash.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champions's W
     * Icon.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns W Icon Image.
     * @throws IOException
     */
    public static BufferedImage findW(String filePath) throws IOException {
        File theFile = new File("data\\Files\\" + filePath + "\\" + filePath + "W.png");
        return ImageIO.read(theFile);
    }

    /**
     * Searches the local directories and folders to find the champion's W Name
     * @param filePath the location of the file
     * @return The W name
     */
    public static String findWName(String filePath) {
        File theFile = new File("data\\Files\\" + filePath + "\\Information\\wName.txt");
        Scanner read;
        try {
            read = new Scanner(theFile);
            return read.nextLine();

        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND" + filePath.toUpperCase() + "'S W NAME FILE");
            return "";
        }
    }

    /**
     * Searches the local directories and folders to find the champions's W
     * Description.
     *
     * @param filePath The Path Where The Information Will Be Pulled From.
     * @return Returns W Description.
     */
    public static String findWText(String filePath) {
        try {
            Scanner read = new Scanner(new File("data\\Files\\" + filePath + "\\Information\\w.txt"));
            String txt = "";
            while (read.hasNext()) {
                txt = txt + " " + read.next();
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.log(FindLocal.class.getName(), "FAILED TO FIND " + filePath.toUpperCase() + "'S W FILE");
            return "";
        }
    }
}
