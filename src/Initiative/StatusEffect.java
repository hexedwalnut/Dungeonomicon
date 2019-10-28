package Initiative;

public class StatusEffect {
    //Variables---------------------------------------------------------------------------------------------------------
    private String name = ""; //Status effect Name
    private String description = ""; //Status effect Description

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * The setter for name
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for description
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The getter for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    StatusEffect() {
        this.name = "NO NAME";
        this.description = "NO DESCRIPTION";
    }

    /**
     * The Constructor
     * @param name name of the status effect
     * @param description description of the status effect
     */
    public StatusEffect(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
