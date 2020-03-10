package Initiative;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddNewCombatant {

    //Variables---------------------------------------------------------------------------------------------------------
    private Stage newStage;
    private InitiativeUI UI;

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * sets-up the pane for the stage
     * @return GridPane
     */
    public GridPane getNewCombatantPane(){
        //Declaring labels
        GridPane gridPane = new GridPane();
        Label nameLabel = new Label("Name*: ");
        Label initLabel = new Label("Initiative*: ");
        Label healthLabel = new Label("Health: ");
        Label acLabel = new Label("Armor Class: ");
        Label reqLabel = new Label("* are required fields");
        Label npcornotLabel = new Label("NPC/PC?*");

        //Declaring and setting phantom Text of TextFields
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");

        TextField initText = new TextField();
        initText.setPromptText("17");

        TextField healthText = new TextField();
        healthText.setPromptText("25");

        TextField acText = new TextField();
        acText.setPromptText("17");

        //Declares ChoiceBox
        ChoiceBox<String> npcornot = new ChoiceBox<String>(FXCollections.observableArrayList("NPC", "PC"));
        npcornot.getSelectionModel().selectFirst();

        //Buttons
        //Ok Button
        Button addButton = new Button("Ok");
        addButton.setOnAction(event -> {
            try{
                if(npcornot.getSelectionModel().getSelectedItem().equals("NPC")){
                    Combatant newCombatant = new NonPlayerCharacter(Integer.parseInt(initText.getText()),
                            nameText.getText(),Integer.parseInt(healthText.getText()),
                            Integer.parseInt(acText.getText()));
                    UI.getInitiativeTracker().addCombatant(newCombatant);
                } else {
                    Combatant newCombatant = new PlayerCharacter(Integer.parseInt(initText.getText()),
                            nameText.getText());
                    UI.getInitiativeTracker().addCombatant(newCombatant);
                }
                UI.refresh();
                newStage.close();
            }catch(NumberFormatException nf){
                new errorWindow().errorWindow("A number could not be formatted correctly \n Please try again");
            }
        });

        //Cancel Button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> newStage.close());

        //adds everything to the gridPane
        gridPane.add(nameLabel, 0,0);
        gridPane.add(nameText, 1, 0);
        gridPane.add(initLabel,0, 1);
        gridPane.add(initText, 1,1);
        gridPane.add(healthLabel, 0,2);
        gridPane.add(healthText,1,2);
        gridPane.add(acLabel,0,3);
        gridPane.add(acText,1,3);
        gridPane.add(npcornot,1,5);
        gridPane.add(npcornotLabel,0,5);
        gridPane.add(addButton,0,6);
        gridPane.add(cancelButton,1,6);
        gridPane.add(reqLabel,0,7,2,1);

        return gridPane;
    }

    /**
     * Creates the stage for adding a new Combatant
     * @param UI the InitivitveUI object - Used for some functions
     */
    public void newCombatantStage(InitiativeUI UI){
        this.UI = UI;
        newStage = new Stage();
        newStage.setResizable(false);
        newStage.setScene(new Scene(getNewCombatantPane()));
        newStage.sizeToScene();
        newStage.setTitle("Add New Combatant");
        newStage.show();
    }
}
