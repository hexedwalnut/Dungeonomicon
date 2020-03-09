package Initiative;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class InitiativeUI {

    private Stage initStage;
    private ListView<Combatant> listView;
    private InitiativeTracker initiativeTracker;

    public BorderPane getInitPane(){
        //Declaring a bunch of stuff

        BorderPane borderPane = new BorderPane();
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        VBox leftBox = new VBox();
        Label currentCombatant = new Label("Current Combatant: ");
        GridPane gPane = new GridPane();

        initiativeTracker = new InitiativeTracker();
        listView = new ListView<Combatant>();

        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> System.out.println("nextTurn pushed")); //temp-Change when nextTurn implemented
        Button newCombatant = new Button("Add New Combatant");
        newCombatant.setOnAction(event -> {
            new addNewCombatant().newCombatantStage(this);
        });
        Button loadCombatants = new Button("Load Combatants");
        Button removeCombatant = new Button("Remove Combatant");
        removeCombatant.setOnAction(event -> {
            initiativeTracker.removeCombatant(listView.getSelectionModel().getSelectedItem());
            refresh();
        });


        bottomBox.getChildren().add(nextTurn);
        bottomBox.getChildren().add(newCombatant);
        bottomBox.getChildren().add(removeCombatant);
        bottomBox.getChildren().add(loadCombatants);
        topBox.getChildren().add(currentCombatant);

        //putting boxes/list into borderPane
        borderPane.setCenter(gPane);
        borderPane.setLeft(leftBox);
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        return borderPane;
    }

    public void refresh(){

        if(initiativeTracker.hasCombatants()){
            ObservableList<Combatant> combatants = FXCollections.observableArrayList(initiativeTracker.getCombatants());
            listView = new ListView<>(combatants);
        } else{
            listView = new ListView<>();
        }
        initStage.show();
    }


    public void initStage(){
        initStage = new Stage();
        initStage.setResizable(true);
        initStage.setScene(new Scene(getInitPane()));
        initStage.sizeToScene();
        initStage.setTitle("Initiative Tracker");
        initStage.show();
    }
}
