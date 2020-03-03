package Initiative;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class InitiativeUI {


    public BorderPane getInitPane(){
        //Declaring a bunch of stuff

        BorderPane borderPane = new BorderPane();
        ListView list = new ListView();//need to add the combatants to the list
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        VBox leftBox = new VBox();
        Label currentCombatant = new Label("Current Combatant");

        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> System.out.println("nextTurn pushed")); //temp-Change when nextTurn implemented
        Button newCombatant = new Button("Add New Combatant");
        newCombatant.setOnAction(event -> new addNewCombatant().newCombatantStage());

        //Putting objects into the boxs
        bottomBox.getChildren().add(nextTurn);
        bottomBox.getChildren().add(newCombatant);
        topBox.getChildren().add(currentCombatant);

        //putting boxes/list into borderPane
        borderPane.setLeft(leftBox);
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);
        borderPane.setCenter(list);

        return borderPane;
    }

    public void initStage(){
        Stage initStage = new Stage();
        initStage.setResizable(false);
        initStage.setScene(new Scene(getInitPane()));
        initStage.sizeToScene();
        initStage.setTitle("Initiative Tracker");
        initStage.show();
    }
}
