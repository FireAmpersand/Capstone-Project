package backend_models;

import java.util.Scanner;

/**
 *
 * @author Zach
 */
public class Champion extends FindLocal {

    private double attackSpeed;
    private double baseArmor;
    private double baseMR;
    private double scaleArmor;
    private double scaleMR;
    private int baseAD;
    private int baseAP;
    private int baseHP;
    private int baseResource;
    private int crit;
    private String eName, eTxt;
    private int id, moveSpeed;
    private String name;
    private String passiveName;
    private String passiveTxt;
    private String qName;
    private String qTxt;
    private String rName;
    private String rTxt;
    private String resourceType;
    private int scaleAD;
    private int scaleAttackSpeed;
    private int scaleCrit;
    private int scaleHP;
    private int scaleResource;
    private String wName;
    private String wTxt;

    /**
     * Creates A New Champion Object
     *
     * @param name The Name Of The Champion
     */
    public Champion(String name) {
        this.name = name;
        this.baseAD = 0;
        this.baseAP = 0;
        this.baseArmor = 0;
        this.baseHP = 0;
        this.baseMR = 0;
        this.baseResource = 0;
        this.resourceType = "";
        this.attackSpeed = 0;
        this.moveSpeed = 0;
        this.id = 0;
        this.crit = 0;
        update();
    }

    /**
     * Returns How Much Attack Damage Is Scaled Per Level.
     *
     * @return Attack Damage Per Level
     */
    public int getAdScale() {
        return this.scaleAD;
    }

    /**
     * Returns How Much The Armor Is Scaled Per Level.
     *
     * @return Armor Per Level
     */
    public double getArmorScale() {
        return this.scaleArmor;
    }

    /**
     * Returns The Base Attack Speed For The Champion
     *
     * @return Base Attack Speed
     */
    public double getAttackSpeed() {
        return this.attackSpeed;
    }

    /**
     * Returns How Much The Attack Speed Is Scaled Per Level.
     *
     * @return Attack Speed Per Level
     */
    public int getAttackSpeedScale() {
        return this.scaleAttackSpeed;
    }

    /**
     * Returns The Base Attack Damage For The Champion.
     *
     * @return Base Attack Damage
     */
    public int getBaseAD() {
        return this.baseAD;
    }

    /**
     * Returns The Base Ability Power Of The Champion.
     *
     * @return Base Ability Power
     */
    public int getBaseAP() {
        return this.baseAP;
    }

    /**
     * Returns The Base Armor For The Champion.
     *
     * @return Base Armor
     */
    public double getBaseArmor() {
        return this.baseArmor;
    }

    /**
     * Returns The Base Critical Strike Chance For The Champion.
     *
     * @return Base Critical Strike
     */
    public int getBaseCrit() {
        return this.crit;
    }

    /**
     * Returns The Base Hit Points Of The Champion
     *
     * @return Base Hit Points
     */
    public int getBaseHP() {
        return this.baseHP;
    }

    /**
     * Returns The Base Magic Resist For The Champion.
     *
     * @return Base Magic Resist
     */
    public double getBaseMR() {
        return this.baseMR;
    }

    /**
     * Returns The Base Resource For The Champion.
     *
     * @return Base Resource
     */
    public int getBaseResource() {
        return this.baseResource;
    }

    /**
     * Returns How Much The Critical Strike Chance Is Scaled Per Level.
     *
     * @return Critical Strike Change Per Level
     */
    public int getCritScale() {
        return this.scaleCrit;
    }

    /**
     * Returns The E Ability Name
     *
     * @return The E Name
     */
    public String getEName() {
        return this.eName;
    }

    /**
     * Returns The Description Of The E Ability.
     *
     * @return The E Text
     */
    public String getETxt() {
        return this.eTxt;
    }

    /**
     * Returns How Much The Hit Points Are Scaled Per Level.
     *
     * @return Hit Points Per Level
     */
    public int getHPScale() {
        return this.scaleHP;
    }

    /**
     * Returns This Champion's ID.
     *
     * @return ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns How Much The Magic Resist Is Scaled Per Level.
     *
     * @return Magic Resist Per Level
     */
    public double getMRScale() {
        return this.scaleMR;
    }

    /**
     * Returns The Base Movement Speed For The Champion
     *
     * @return Base Movement Speed
     */
    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    /**
     * Returns The Champions Name
     *
     * @return Returns The Champion's Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns The Passive Ability Name
     *
     * @return The Passive's name
     */
    public String getPassiveName() {
        return this.passiveName;
    }

    /**
     * Returns The Description Of The Passive Ability.
     *
     * @return The Passive Text
     */
    public String getPassiveTxt() {
        return this.passiveTxt;
    }

    /**
     * Returns The Q Ability Name
     *
     * @return The Q Name
     */
    public String getQName() {
        return this.qName;
    }

    /**
     * Returns The Description Of The Q Ability.
     *
     * @return The Q Text
     */
    public String getQTxt() {
        return this.qTxt;
    }

    /**
     * Returns The R Ability Name
     *
     * @return The R Name
     */
    public String getRName() {
        return this.rName;
    }

    /**
     * Returns The Description Of The R Ability
     *
     * @return The R Text
     */
    public String getRTxt() {
        return this.rTxt;
    }

    /**
     * Returns How Much The Resource Is Scaled Per Level.
     *
     * @return Resource Per Level
     */
    public int getResourceScale() {
        return this.scaleResource;
    }

    /**
     * Returns The Resource Type For The Champion.
     *
     * @return Resource Type
     */
    public String getResourceType() {
        return this.resourceType;
    }

    /**
     * Returns The W Ability Name
     *
     * @return The W Name
     */
    public String getWName() {
        return this.wName;
    }

    /**
     * Returns The Description Of The W Ability.
     *
     * @return The W Text
     */
    public String getWTxt() {
        return this.wTxt;
    }

    /**
     * Forces All Fields To Be Updated With New Information.
     */
    public void update() {
        this.passiveName = findPassiveName(name);
        this.qName = findQName(name);
        this.wName = findWName(name);
        this.eName = findEName(name);
        this.rName = findRName(name);
        this.baseHP = findHP(name);
        this.baseAD = findAD(name);
        this.baseAP = findAP(name);
        this.baseArmor = findArmor(name);
        this.baseMR = findMR(name);
        this.passiveTxt = findPassiveText(name);
        this.qTxt = findQText(name);
        this.wTxt = findWText(name);
        this.eTxt = findEText(name);
        this.rTxt = findRText(name);
        this.attackSpeed = findAttackSpeed(name);
        this.moveSpeed = findMoveSpeed(name);
        this.id = findID(name);
        this.crit = findCritChance(name);
        this.scaleAD = findScaleAd(name);
        this.scaleArmor = findScaleArmor(name);
        this.scaleAttackSpeed = findScaleAttackSpeed(name);
        this.scaleCrit = findScaleCrit(name);
        this.scaleHP = findScaleHp(name);
        this.scaleMR = findScaleMr(name);
        this.scaleResource = findScaleResource(name);
        String resource = findResource(name);
        Scanner read = new Scanner(resource);
        String r = read.next();
        if (r.equals("M")) {
            this.resourceType = "Mana";
            this.baseResource = Integer.parseInt(read.next());
        } else if (r.equals("E")) {
            this.resourceType = "Energy";
            this.baseResource = Integer.parseInt(read.next());
        } else if (r.equals("S")) {
            this.resourceType = "Sheild";
            this.baseResource = Integer.parseInt(read.next());
        } else if (r.equals("R")) {
            this.resourceType = "Rage";
            this.baseResource = Integer.parseInt(read.next());
        } else {
            this.resourceType = "None";
            this.baseResource = 0;
        }
    }

}
