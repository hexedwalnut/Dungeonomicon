package Initiative;

import Persistence.SettingsStorage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditCombatant {

    private InitiativeUI ui;
    private Stage editStage;
    private Combatant combatant;

    public GridPane getEditCombatantPane(){
        GridPane pane = new GridPane();
        Label nameLabel = new Label("Name: ");
        Label initLabel = new Label("Initiative: ");
        Label healthLabel = new Label("Health: ");
        Label acLabel = new Label("Armor Class: ");
        Label reqLabel = new Label("Leave Blank to not Change");

        //Declaring and setting phantom Text of TextFields
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");

        TextField initText = new TextField();
        initText.setPromptText("17 or +/-4");

        TextField healthText = new TextField();
        healthText.setPromptText("25 or +/-2");

        TextField acText = new TextField();
        acText.setPromptText("17 or +/-6");

        Button cancelButton = new Button("Canel");
        cancelButton.setOnAction(event -> editStage.close());

        Button okButton = new Button("Ok");
        okButton.setOnAction(event -> {
            try {
                if (!nameText.getText().equals("")) {
                    combatant.setName(nameText.getText());
                }
                if (!initText.getText().equals("")) {
                    if (initText.getText().charAt(0) == '+'){
                        combatant.setInitiative(combatant.getInitiative()
                                + Integer.parseInt(initText.getText().substring(1)));
                    } else if(initText.getText().charAt(0) == '-'){
                        combatant.setInitiative(combatant.getInitiative()
                                - Integer.parseInt(initText.getText().substring(1)));
                    } else {
                        combatant.setInitiative(Integer.parseInt(initText.getText()));
                    }
                }
                if (!healthText.getText().equals("") && combatant instanceof NonPlayerCharacter) {
                    if (healthText.getText().charAt(0) == '+'){
                        ((NonPlayerCharacter)combatant).setHitPoints(((NonPlayerCharacter)combatant).getHitPoints()
                                + Integer.parseInt(healthText.getText().substring(1)));
                    } else if(healthText.getText().charAt(0) == '-'){
                        ((NonPlayerCharacter)combatant).setHitPoints(((NonPlayerCharacter)combatant).getHitPoints()
                                - Integer.parseInt(healthText.getText().substring(1)));
                    } else {
                        ((NonPlayerCharacter)combatant).setHitPoints(Integer.parseInt(healthText.getText()));
                    }
                }
                if (!acText.getText().equals("") && combatant instanceof NonPlayerCharacter) {
                    if (acText.getText().charAt(0) == '+'){
                        ((NonPlayerCharacter)combatant).setArmorClass(((NonPlayerCharacter)combatant).getArmorClass()
                                + Integer.parseInt(acText.getText().substring(1)));
                    } else if(acText.getText().charAt(0) == '-'){
                        ((NonPlayerCharacter)combatant).setArmorClass(((NonPlayerCharacter)combatant).getArmorClass()
                                - Integer.parseInt(acText.getText().substring(1)));
                    } else {
                        ((NonPlayerCharacter)combatant).setArmorClass(Integer.parseInt(acText.getText()));
                    }
                }
                ui.refresh();
                editStage.close();
            } catch(NumberFormatException e){
                new errorWindow().errorWindow("One or more numbers had an invalid format");
            }
        });

        pane.add(nameLabel,0,0);
        pane.add(nameText,1,0);
        pane.add(initLabel, 0,1);
        pane.add(initText,1,1);
        pane.add(healthLabel,0,2);
        pane.add(healthText,1,2);
        pane.add(acLabel,0,3);
        pane.add(acText,1,3);
        pane.add(reqLabel,0,4,2,1);
        pane.add(okButton,0,5);
        pane.add(cancelButton,1,5);

        pane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return pane;
    }

    public void editStage(InitiativeUI UI,Combatant combatant){
        this.combatant = combatant;
        ui = UI;
        editStage = new Stage();
        editStage.setResizable(false);
        editStage.setScene(new Scene(getEditCombatantPane()));
        editStage.sizeToScene();
        editStage.setTitle("Edit Combatant: " + combatant.getName());
        editStage.show();
    }

}
