/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

/**
 *
 * @author Zach
 */
public class Item extends FindLocal{

    private int ad, ap;
    private double armor, attackSpeed;
    private int crit;
    private int hp;
    private int id;
    private String info;
    private double mr;
    private String name;
    private int resource;

    /**
     * Creates A New Item Object.
     *
     * @param name The Name Of The Item.
     */
    public Item(String name) {
        this.name = name;
        update();
    }

    /**
     * Gets The Amount The Item Effects The Champion's Attack Damage
     * @return Returns How Much It Effects The Attack Damage Stats.
     */
    public int getAdFactor() {
        return ad;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Ability Power
     * @return Returns How Much It Effects The Ability Power Stats.
     */
    public int getApFactor() {
        return ap;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Armor
     * @return Returns How Much It Effects The Armor Stats.
     */
    public double getArmorFactor() {
        return armor;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Attack Speed
     * @return Returns How Much It Effects The Attack Speed Stats.
     */
    public double getAttackSpeedFactor() {
        return attackSpeed;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Critical Strike Chance
     * @return Returns How Much It Effects The Critical Strike Chance Stats.
     */
    public int getCritFactor() {
        return crit;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Hit Points
     * @return Returns How Much It Effects The Hit Point Stats.
     */
    public int getHpFactor() {
        return hp;
    }

    /**
     * Gets The Item's ID
     * @return Returns This Items ID.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets The Information About This Item
     * @return Returns This Items Information.
     */
    public String getItemInfo() {
        return this.info;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Magic Resist
     * @return Returns How Much It Effects The Magic Resist Stats.
     */
    public double getMrFactor() {
        return mr;
    }

    /**
     * Gets The Name Of The Item
     *
     * @return Returns The Name Of The Item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets The Amount The Item Effects The Champion's Resource
     * @return Returns How Much It Effects The Resource Stats.
     */
    public int getResourceFactor() {
        return resource;
    }

    /**
     * Updates All Stats.
     */
    private void update() {
        this.info = findItemInfo(name);

        if (this.info.contains("</stats")) {
            String[] stats = this.info.split("</stats>");
            stats[0] = stats[0].replaceAll("<stats>", "");
            if (stats[0].contains("<br>")) {
                String txt = "";
                for (int i = 0; i < stats.length; i++) {
                    txt = txt + stats[i];
                }
                System.out.println(txt);
                stats = txt.split("<br>");
            }
            for (int i = 0; i < stats.length; i++) {
                System.out.println(stats[i] + "\n");

                if (stats[i].contains("unique")) { //Used For Unique Parts of Item (Not In Yet)
                    break;
                } else if (stats[i].contains("aura")) { //Used For Aura Parts of Item (Not In Yet)
                    break;
                }
                if (stats[i].contains("Ability Power")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("Ability Power", "");
                    s = s.replace(" ", "");
                    this.ap = this.ap + Integer.parseInt(s);
                }
                if (stats[i].contains("Attack Damage")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("Attack Damage", "");
                    s = s.replace(" ", "");
                    this.ad = this.ad + Integer.parseInt(s);
                }
                if (stats[i].contains("Health") && !stats[i].contains("Regen")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("Health", "");
                    s = s.replace(" ", "");
                    this.hp = this.hp + Integer.parseInt(s);
                }
                if (stats[i].contains("Attack Speed")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("%", "");
                    s = s.replace("Attack Speed", "");
                    s = s.replace(" ", "");
                    double as = Double.parseDouble(s) / 100;
                    this.attackSpeed = this.attackSpeed + as;
                }
                if (stats[i].contains("Critical Strike Chance")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("%", "");
                    s = s.replace("Critical Strike Chance", "");
                    s = s.replace(" ", "");
                    this.crit = this.crit + Integer.parseInt(s);
                }
                if (stats[i].contains("Armor")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("Armor", "");
                    s = s.replace(" ", "");
                    this.armor = this.armor + Double.parseDouble(s);
                }
                if (stats[i].contains("Magic Resist")) {
                    String s = stats[i];
                    s = s.replace("+", "");
                    s = s.replace("Magic Resist", "");
                    s = s.replace(" ", "");
                    this.mr = this.mr + Double.parseDouble(s);
                }
            }
        }
    }

}
