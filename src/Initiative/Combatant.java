package Initiative;

public class Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    private int hp;
    private int ac;
    private String name;

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * Gets the hit points of a combatant
     *
     * @return hp the hit points of the combatant
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the armor class of a combatant
     *
     * @return ac the armor class of the combatant
     */
    public int getAc() {
        return ac;
    }

    /**
     * Gets the name of the combatant
     *
     * @return name the name of the combatant
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the hit points of the combatant
     *
     * @param hp the hit points of the combatant
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Sets the armor class of the combatant
     *
     * @param ac the armor class of the combatant
     */
    public void setAc(int ac) {
        this.ac = ac;
    }

    /**
     * Sets the name of the combatant
     *
     * @param name the name of the combatant
     */
    public void setName(String name) {
        this.name = name;
    }

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    Combatant() {
        this.name = "no value";
        this.hp = 4;
        this.ac = 10;
    }

    /**
     * Name constructor
     *
     * @param name name of the combatant
     */
    Combatant(String name) {
        this.name = name;
        this.hp = 4;
        this.ac = 10;
    }

    /**
     * Name and hit point constructor
     *
     * @param name name of the combatant
     * @param hp   hit points of the combatant
     */
    Combatant(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.ac = 10;
    }

    /**
     * Name, hit point, armor class constructor
     *
     * @param name name of the combatant
     * @param hp   hit points of the combatant
     * @param ac   armor class of the combatant
     */
    Combatant(String name, int hp, int ac) {
        this.name = name;
        this.hp = hp;
        this.ac = ac;
    }
}
