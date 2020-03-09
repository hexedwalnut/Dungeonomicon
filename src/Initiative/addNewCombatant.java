package Initiative;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class addNewCombatant {

    private Stage newStage;
    private InitiativeUI UI;

    public GridPane getNewCombatantPane(){
        //Declaring a bunch of stuff

        GridPane gridPane = new GridPane();
        Label nameLabel = new Label("Name*: ");
        Label initLabel = new Label("Initiative*: ");
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");
        TextField initText = new TextField();
        initText.setPromptText("17");
        Label healthLabel = new Label("Health: ");
        TextField healthText = new TextField();
        healthText.setPromptText("25");
        Label acLabel = new Label("Armor Class: ");
        TextField acText = new TextField();
        acText.setPromptText("17");
        Label reqLabel = new Label("* are required fields");
        ChoiceBox<String> npcornot = new ChoiceBox<String>(FXCollections.observableArrayList("NPC", "PC"));
        Label npcornotLabel = new Label("NPC/PC?*");
        Button addButton = new Button("Ok");
        addButton.setOnAction(event -> {
            System.out.println("Ok button pushed");
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
            }catch(NumberFormatException nf){
                new errorWindow().errorWindow("A number could not be formatted correctly \n Please try again");
            }
            UI.refresh();
            newStage.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> newStage.close());

        gridPane.add(nameLabel, 0,0);
        gridPane.add(nameText, 1, 0);
        gridPane.add(initLabel,0, 1);
        gridPane.add(initText, 1,1);
        gridPane.add(healthLabel, 0,2);
        gridPane.add(healthText,1,2);
        gridPane.add(acLabel,0,3);
        gridPane.add(acText,1,3);
        gridPane.add(addButton,0,5);
        gridPane.add(cancelButton,1,5);
        gridPane.add(npcornot,1,6);
        gridPane.add(npcornotLabel,0,6);
        gridPane.add(reqLabel,0,7,2,1);

        return gridPane;
    }

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
