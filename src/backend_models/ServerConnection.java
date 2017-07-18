package backend_models;

import java.net.MalformedURLException;
import java.net.URL;
import Logger.Logger;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerConnection extends SaveInformation {

    private static final String API_KEY = "25b8a43d-512c-481f-9d5a-d1a8bb776d9e";
    private static boolean isOnline;
    private static List<String> items;
    private static List<String> roster;
    private static List<String> spells;

    /**
     * Checks If The Application Is Updated
     */
    public static void checkIsUpdated() {
        String lastSavedVersion = "";
        try {
            Scanner read = new Scanner(new File("data\\appData\\lastSavedVersion.txt"));
            if (read.hasNext()) {
                lastSavedVersion = read.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.log(ServerConnection.class.getName(), "FAILED TO GET LOCAL VERSION");
        }

        if (getCurrentVersion() != null) {

            if (!lastSavedVersion.equals(getCurrentVersion()) && isOnline) {
                int select = JOptionPane.showConfirmDialog(null, "The Application Is Out Of Date.\nWould You Like To Update?", "Updater", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (select == 0) {
                    updateApplication();
                    JOptionPane.showMessageDialog(null, "Please Restart The Application", "Updater", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "The Application Is Up To Date", "Updater", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("App is up to date.");
            }
        }
    }

    /**
     * Checks If The Device Is Online
     */
    public static void checkOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec(("ping www.surrenderat20.net"));
            if (p1.waitFor() == 0) {
                isOnline = true;
            } else {
                isOnline = false;
            }
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "DEVICE IS OFFLINE, UNABLE TO CONTACT SERVER");
            isOnline = false;
        } catch (InterruptedException ex) {
            Logger.log(ServerConnection.class.getName(), "DEVICE IS OFFLINE, UNABLE TO CONTACT SERVER");
            isOnline = false;
        }
    }

    /**
     * Gets The Champion's Abilities From The League Of Legends API
     *
     * @param name The Name of The Champion
     * @param id The ID Of The Champion
     */
    public static void fetchAblities(String name, String id) { //Rumble Broken
        String spellURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + id + "?champData=spells&api_key=" + API_KEY;
        String passiveURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + id + "?champData=passive&api_key=" + API_KEY;
        String patch = getCurrentVersion();
        String spellImageLoc = "http://ddragon.leagueoflegends.com/cdn/" + patch + "/img/spell/";
        String passiveImageLoc = "http://ddragon.leagueoflegends.com/cdn/" + patch + "/img/passive/";

        JSONObject spell = new JSONObject(readUrl(spellURL));
        JSONArray jsonArr = spell.getJSONArray("spells");
        JSONObject json = null;
        BufferedImage[] pictures = new BufferedImage[5];
        for (int i = 0; i < jsonArr.length(); i++) {
            spell = jsonArr.getJSONObject(i);
            json = spell.getJSONObject("image");
            spell = new JSONObject(json.toString());
            try {
                pictures[i + 1] = ImageIO.read(new URL(spellImageLoc + spell.get("full")));
            } catch (IOException ex) {
                Logger.log(ServerConnection.class.getName(), "FAILED TO FIND IMAGE");
            }
        }

        JSONObject passive = new JSONObject(readUrl(passiveURL));
        JSONObject pJson = passive.getJSONObject("passive");
        passive = new JSONObject(pJson.toString());
        pJson = passive.getJSONObject("image");
        passive = new JSONObject(pJson.toString());
        try {
            pictures[0] = ImageIO.read(new URL(passiveImageLoc + passive.get("full")));
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "FAILED TO FIND IMAGE");
        }
        savePictureToDevice(pictures, name);

        JSONObject passTxt = new JSONObject(readUrl(passiveURL));
        JSONObject npassTxt = passTxt.getJSONObject("passive");
        passTxt = new JSONObject(npassTxt.toString());
        String[] descriptions = new String[5];
        String[] names = new String[5];
        descriptions[0] = passTxt.get("description").toString();
        names[0] = passTxt.get("name").toString();

        JSONObject spellTxt = new JSONObject(readUrl(spellURL));
        JSONArray jsonArrTxt = spellTxt.getJSONArray("spells");
        for (int i = 0; i < jsonArrTxt.length(); i++) {
            spellTxt = jsonArr.getJSONObject(i);
            descriptions[i + 1] = spellTxt.get("sanitizedDescription").toString();
            names[i + 1] = spellTxt.getString("name").toString();
        }
        saveAbilitiesTextToDevice(descriptions, name);
        saveAbilitesNamesToDevice(names, name);

        //Get Name of spell, save then read
    }

    /**
     * Gets The Champion's Icon From The League Of Legends API
     *
     * @param cham The Champion Object
     */
    public static void fetchChampionIcon(Champion cham) {
        int championId = cham.getId();
        String championIconURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + championId + "?champData=image&api_key=" + API_KEY;

        JSONObject json = new JSONObject(readUrl(championIconURL));
        JSONObject nJSON = json.getJSONObject("image");
        json = new JSONObject(nJSON.toString());
        String patch = getCurrentVersion();
        String imageLocation = "http://ddragon.leagueoflegends.com/cdn/" + patch + "/img/champion/" + json.getString("full");

        try {
            BufferedImage img = ImageIO.read(new URL(imageLocation));
            saveIconsToDevice(img, cham.getName());
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "SERVER NOT REACHABLE");
        }
    }

    /**
     * Gets The Champion's Icon From The League Of Legends API
     *
     * @param id The ID Of The Champion
     * @param name The Name Of The Champion
     */
    public static void fetchChampionIcon(String id, String name) {
        String championIconURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + id + "?champData=image&api_key=" + API_KEY;
        JSONObject json = new JSONObject(readUrl(championIconURL));
        JSONObject nJSON = json.getJSONObject("image");
        json = new JSONObject(nJSON.toString());
        String patch = getCurrentVersion();
        String imageLocation = "http://ddragon.leagueoflegends.com/cdn/" + patch + "/img/champion/" + json.getString("full");

        try {
            BufferedImage img = ImageIO.read(new URL(imageLocation));
            saveIconsToDevice(img, name);
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "SERVER NOT REACHABLE");
        }
    }

    /**
     * Updates The Item List
     */
    private static void fetchItems() {
        String itemsURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/item?itemListData=all&api_key=" + API_KEY;

        JSONObject json = new JSONObject(readUrl(itemsURL));
        JSONObject nJSON = json.getJSONObject("data");
        json = new JSONObject(nJSON.toString());
        List itemIds = json.names().toList();
        JSONObject currentItem;
        JSONObject currentPicture;
        String done = "";
        items = new ArrayList<String>();
        JDialog dlg = new JDialog((JFrame) null, "Updating Items", false);
        final JProgressBar bar = new JProgressBar(0, 100);
        JLabel progress = new JLabel("Progress...");
        JPanel pane = new JPanel();
        dlg.setLayout(new BorderLayout());
        dlg.getContentPane().add(progress, BorderLayout.CENTER);

        pane.add(BorderLayout.CENTER, bar);
        pane.add(BorderLayout.EAST, progress);

        dlg.getContentPane().add(pane, BorderLayout.WEST);

        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.setSize(new Dimension(500, 150));

        dlg.pack();
        dlg.setVisible(true);
        dlg.setLocationRelativeTo(null);
        for (int i = 0; i < itemIds.size(); i++) {
            if (!done.equals((int) (((i + 1) / (double) itemIds.size()) * 100) + "% Complete")) {
                done = (int) (((i + 1) / (double) itemIds.size()) * 100) + "% Complete";
                int p = (int) (((i + 1) / (double) itemIds.size()) * 100);
                bar.setValue(p);
                progress.setText(p + "% Complete");
                dlg.setTitle("Updating Items... " + p + "% Complete");
                dlg.getContentPane().paintAll(bar.getGraphics());
                if (p == 100) {
                    dlg.setVisible(false);
                }
            }
            try {
                BufferedImage img = null;
                if (Integer.parseInt(itemIds.get(i).toString()) == 3462) {
                    continue;
                } else {
                    currentItem = json.getJSONObject(itemIds.get(i).toString());
                    JSONObject map = currentItem.getJSONObject("maps");
                    if (map.getBoolean("11") == true) {
                        currentPicture = currentItem.getJSONObject("image");
                        String name = "";
                        if (currentItem.getString("name").contains(":")) {
                            name = "" + currentItem.getInt("id");
                        } else {
                            name = currentItem.getString("name");
                        }
                        items.add(name);
                        String pictureLocation = "https://ddragon.leagueoflegends.com/cdn/" + getCurrentVersion() + "/img/sprite/" + currentPicture.get("sprite");
                        img = ImageIO.read(new URL(pictureLocation));
                        img = img.getSubimage(currentPicture.getInt("x"), currentPicture.getInt("y"), currentPicture.getInt("w"), currentPicture.getInt("h"));
                        System.out.println(name);
                        saveItemListToDevice(name);
                        saveItemsToDevice(name, Integer.parseInt(itemIds.get(i).toString()), currentItem.getString("description"), img);
                    }
                }
            } catch (MalformedURLException ex) {
                Logger.log(ServerConnection.class.getName(), "FAILED TO REACH DD SERVER");
            } catch (IOException ex) {
                Logger.log(ServerConnection.class.getName(), "FAILED TO RETRIEVE THE PICTURE");
            }
        }
        String[] temp = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            temp[i] = items.get(i);
        }
        Arrays.sort(temp);
        items.clear();
        for (int i = 0; i < temp.length; i++) {
            items.add(temp[i]);
        }
    }

    /**
     * Gets The Champion's Splash Art From The League Of Legends API
     *
     * @param name The Name of The Champion
     * @param id The ID Of The Champion
     */
    public static void fetchSplashArt(String name, String id) {
        String nme = "";
        if (name.equals("Wukong")) {
            nme = "MonkeyKing";
        } else {
            nme = name;
        }
        String splashURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + id + "?champData=skins&api_key=" + API_KEY;
        JSONObject json = new JSONObject(readUrl(splashURL));
        JSONArray jsonArr = json.getJSONArray("skins");
        json = jsonArr.getJSONObject(0);
        String pictureLocation = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + name + "_" + json.get("num").toString() + ".jpg";
        try {
            BufferedImage splash = ImageIO.read(new URL(pictureLocation));
            saveSplashArtToDevice(splash, name);
        } catch (MalformedURLException ex) {
            Logger.log(ServerConnection.class.getName(), "FAILED TO CONNECT TO SERVER");
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "FAILED TO GET SPLASH IMAGE");
        }
    }

    /**
     * Gets The Champion's Stats From The League Of Legends API
     *
     * @param name The Name Of The Champion
     * @param id The ID Of The Champion
     */
    public static void fetchStats(String name, String id) { //MRScale Broken On Taliyah
        int championId = Integer.parseInt(id);
        double[] stats = new double[20];
        String championStatsURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/" + championId + "?champData=stats&api_key=" + API_KEY;
        JSONObject json = new JSONObject(readUrl(championStatsURL));
        JSONObject nJSON = json.getJSONObject("stats");
        json = new JSONObject(nJSON.toString());

        stats[0] = json.getDouble("hp"); //Base HP
        stats[1] = json.getDouble("attackdamage"); //Base AD
        stats[2] = 0.625 + (json.getDouble("attackspeedoffset") * -0.625); //Base Attackspeed
        stats[3] = json.getDouble("movespeed"); //Base Movespeed
        stats[4] = json.getDouble("armor"); //Base Armor
        stats[5] = json.getDouble("spellblock"); //Base MR
        stats[6] = json.getDouble("attackrange"); //Base Attack Range
        stats[7] = json.getDouble("mp"); //Resource
        stats[8] = json.getDouble("mpperlevel"); //Resource Scaling
        stats[9] = json.getDouble("hpperlevel"); //HP Scaling
        stats[10] = json.getDouble("attackdamageperlevel"); //AD Scaling
        stats[11] = json.getDouble("mpregenperlevel"); //HP Regen Scaling
        stats[12] = json.getDouble("hpregen"); //HP Regen/second
        stats[13] = json.getDouble("critperlevel"); //Crit Scaling
        stats[14] = json.getDouble("spellblockperlevel"); //MR Scaling
        stats[15] = json.getDouble("mpregen"); //Resource Regen Scaling
        stats[16] = (json.getDouble("attackspeedperlevel") / 100) * 0.625; //Attack Speed Scaling
        stats[17] = json.getDouble("crit"); //Base Crit
        stats[18] = json.getDouble("hpregenperlevel"); //Scaling HP
        stats[19] = json.getDouble("armorperlevel"); //Scaling Armor

        saveStatsToDevice(stats, name);
    }

    /**
     * Gets the summoner spells from the League Of Legends API
     */
    private static void fetchSummonerSpells() {
        String spellUrl = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/summoner-spell?spellData=image&api_key=" + API_KEY;
        JSONObject json = new JSONObject(readUrl(spellUrl));
        JSONObject nJSON = json.getJSONObject("data");
        json = new JSONObject(nJSON.toString());
        List keys = json.names().toList();
        JSONObject current;
        JSONObject image;
        BufferedImage img = null;
        String done = "";
        spells = new ArrayList<String>();
        
        JDialog dlg = new JDialog((JFrame) null, "Updating Spells", false);
        final JProgressBar bar = new JProgressBar(0, 100);
        JLabel progress = new JLabel("Progress...");
        JPanel pane = new JPanel();
        dlg.setLayout(new BorderLayout());
        dlg.getContentPane().add(progress, BorderLayout.CENTER);
        
        pane.add(BorderLayout.CENTER, bar);
        pane.add(BorderLayout.EAST, progress);
        
        dlg.getContentPane().add(pane, BorderLayout.WEST);
        
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.setSize(new Dimension(500, 150));
        
        dlg.pack();
        dlg.setVisible(true);
        dlg.setLocationRelativeTo(null);
        
        for (int i = 0; i < keys.size(); i++) {
            
            if (!done.equals((int) (((i + 1) / (double) keys.size()) * 100) + "% Complete")) {
                done = (int) (((i + 1) / (double) keys.size()) * 100) + "% Complete";
                int p = (int) (((i + 1) / (double) keys.size()) * 100);
                bar.setValue(p);
                progress.setText(p + "% Complete");
                dlg.setTitle("Updating Spells... " + p + "% Complete");
                dlg.getContentPane().paintAll(bar.getGraphics());
                if (p == 100) {
                    dlg.setVisible(false);
                }
            }
            
            current = json.getJSONObject(keys.get(i).toString());
            String id = "" + current.get("id");
            String name = current.getString("name");
            String description = current.getString("description");
            spells.add(name);
            image = current.getJSONObject("image");
            System.out.println(image.get("sprite"));
            String pictureLoc = "https://ddragon.leagueoflegends.com/cdn/" + getCurrentVersion() + "/img/sprite/" + image.get("sprite");
            try {
                img = ImageIO.read(new URL(pictureLoc));
                img = img.getSubimage(image.getInt("x"), image.getInt("y"), image.getInt("w"), image.getInt("h"));
                
            } catch (IOException ex) {
                Logger.log(ServerConnection.class.getName(), ex.getMessage());
            }
            saveSpellsListToDevice(name);
            saveSpellToDevice(name, id, description, img);
        }
        
        String[] temp = new String[spells.size()];
        for (int i = 0; i < spells.size(); i++) {
            temp[i] = spells.get(i);
        }
        Arrays.sort(temp);
        spells.clear();
        for (int i = 0; i < temp.length; i++) {
            spells.add(temp[i]);
        }
    }

    /**
     * Gets The Current Roster
     *
     * @return Returns The Roster
     */
    public static List getCurrentChampionRoster() {
        return roster;
    }

    /**
     * Gets The List Of Active Items
     *
     * @return Returns The Item List
     */
    public static List getCurrentItems(){
        return items;
    }
    
    /**
     * Gets The List Of Active Spells
     * @return the spell list
     */
    public static List getCurrentSpells() {
        return spells;
    }

    /**
     * Gets The Current Version If Online
     *
     * @return The Current Version
     */
    public static String getCurrentVersion() {
        try {
            if (isOnline) {
                JSONArray patch = new JSONArray(readUrl("https://global.api.pvp.net/api/lol/static-data/na/v1.2/versions?api_key=" + API_KEY));
                return patch.get(0).toString();
            }
        } catch (Exception e) {
            Logger.log(ServerConnection.class.getName(), e.toString());
            return null;
        }
        return null;
    }

    /**
     * Gets The Online Status Of The Device
     *
     * @return Returns The Online Status
     */
    public static boolean getOnlineStats() {
        return isOnline;
    }

    /**
     * Starts Up The Application When Offline
     */
    public static void offlineBackup() {
        checkOnline();
        String input = "";
        Scanner readFile;
        try {
            readFile = new Scanner(new File("data\\appData\\champions.txt"));
            input = readFile.nextLine();
            String[] champions = input.split(",");
            roster = new ArrayList<String>();
            for (int i = 0; i < champions.length; i++) {
                roster.add(champions[i]);
            }

            readFile = new Scanner(new File("data\\appData\\items.txt"));
            input = readFile.nextLine();
            String[] itemsList = input.split(",");
            Arrays.sort(itemsList);
            items = new ArrayList<String>();
            for (int i = 0; i < itemsList.length; i++) {
                items.add(itemsList[i]);
            }
            
            readFile = new Scanner(new File("data\\appData\\spells.txt"));
            input = readFile.nextLine();
            String[] spellList = input.split(",");
            spells = new ArrayList<String>();
            for(int i = 0; i < spellList.length; i++){
                spells.add(spellList[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.log(ServerConnection.class.getName(), ex.getMessage());
        }
    }

    /**
     * Decodes The Url For Easier Reading In The JSONObject
     *
     * @param urlString The Url Link
     * @return Returns The Decoded Url
     */
    private static String readUrl(String urlString) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } catch (MalformedURLException ex) {
            Logger.log(ServerConnection.class.getName(), "COULD NOT ACCESS THE URL");
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "URL DATA NOT VALID");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.log(ServerConnection.class.getName(), "FAILED TO CLOSE URL STREAM");
                }
            }
        }
        return "";
    }

    /**
     * Updates The Application
     */
    private static void updateApplication() {
        System.out.println("The App is out of date. Updating now. Please Wait.");
        try {
            FileWriter fc = new FileWriter(new File("data\\appData\\champions.txt"));
            PrintWriter reset = new PrintWriter(fc, true);
            reset.print("");

            fc = new FileWriter(new File("data\\appData\\items.txt"));
            reset = new PrintWriter(fc, true);
            reset.print("");
            
            fc = new FileWriter(new File("data\\appData\\spells.txt"));
            reset = new PrintWriter(fc, true);
            reset.print("");
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), ex.getMessage());
        }
        System.out.println("UPDATING ROSTER");
        updateChampionRoster();
        System.out.println("DONE.");
        System.out.println("UPDATING ITEMS");
        fetchItems();
        System.out.println("Updating Done.");
        System.out.println("Updating Spells");
        fetchSummonerSpells();
        System.out.println("DONE");
        try {
            FileWriter fw = new FileWriter(new File("data\\appData\\lastSavedVersion.txt"));
            PrintWriter out = new PrintWriter(fw, true);
            out.println(getCurrentVersion());
        } catch (IOException ex) {
            Logger.log(ServerConnection.class.getName(), "FAILED TO SAVE NEW VERSION");
        }
    }

    /**
     * Updates The Champion Roster
     */
    private static void updateChampionRoster() {
        String rosterURL = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?champData=info&api_key=" + API_KEY;
        JSONObject json = new JSONObject(readUrl(rosterURL));
        JSONObject nJSON = json.getJSONObject("data");
        json = new JSONObject(nJSON.toString());

        List currentRoster = json.names().toList();
        currentRoster.add(currentRoster.indexOf("MonkeyKing"), "Wukong");
        currentRoster.remove(currentRoster.indexOf("MonkeyKing"));
        Collections.sort(currentRoster);
        roster = currentRoster;
        JSONObject jsonID;
        String done = "";

        JDialog dlg = new JDialog((JFrame) null, "Updating Roster", false);
        final JProgressBar bar = new JProgressBar(0, 100);
        JLabel progress = new JLabel("Progress...");
        JPanel pane = new JPanel();
        dlg.setLayout(new BorderLayout());
        dlg.getContentPane().add(progress, BorderLayout.CENTER);

        pane.add(BorderLayout.CENTER, bar);
        pane.add(BorderLayout.EAST, progress);

        dlg.getContentPane().add(pane, BorderLayout.WEST);

        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.setSize(new Dimension(500, 120));

        dlg.pack();
        dlg.setVisible(true);
        dlg.setLocationRelativeTo(null);
        for (int indx = 0; indx < currentRoster.size(); indx++) {
            if (!done.equals((int) (((indx + 1) / (double) currentRoster.size()) * 100) + "% Complete")) {
                done = (int) (((indx + 1) / (double) currentRoster.size()) * 100) + "% Complete";
                int i = (int) (((indx + 1) / (double) currentRoster.size()) * 100);

                bar.setValue(i);
                progress.setText(i + "% Complete");
                dlg.setTitle("Updating Roster... " + i + "% Complete");
                dlg.getContentPane().paintAll(bar.getGraphics());
                if (i == 100) {
                    dlg.setVisible(false);
                }
            }
            if (currentRoster.get(indx).toString().equals("Wukong")) {
                jsonID = json.getJSONObject("MonkeyKing");
                saveIDToChampion(jsonID.get("id").toString(), currentRoster.get(indx).toString());
                fetchChampionIcon(jsonID.get("id").toString(), roster.get(indx));
                saveRosterToDevice(roster.get(indx));
                fetchSplashArt(currentRoster.get(indx).toString(), jsonID.get("id").toString());
                fetchAblities(currentRoster.get(indx).toString(), jsonID.get("id").toString());
                fetchStats(currentRoster.get(indx).toString(), jsonID.get("id").toString());
            } else {
                jsonID = json.getJSONObject(currentRoster.get(indx).toString());
                saveIDToChampion(jsonID.get("id").toString(), currentRoster.get(indx).toString());
                fetchChampionIcon(jsonID.get("id").toString(), roster.get(indx));
                saveRosterToDevice(roster.get(indx));
                fetchSplashArt(currentRoster.get(indx).toString(), jsonID.get("id").toString());
                fetchAblities(currentRoster.get(indx).toString(), jsonID.get("id").toString());
                fetchStats(currentRoster.get(indx).toString(), jsonID.get("id").toString());
            }
        }
    }
}
