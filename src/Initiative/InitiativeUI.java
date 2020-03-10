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



public class InitiativeUI {

    //variables---------------------------------------------------------------------------------------------------------
    private Stage initStage;
    private ListView<Combatant> listView;
    private InitiativeTracker initiativeTracker;

    //methods-----------------------------------------------------------------------------------------------------------

    /**
     * Generates the Pane for the stage
     * @return BorderPane conatining everything for the tracker
     */
    public BorderPane getInitPane(){
        //Declaring a bunch of stuff
        BorderPane borderPane = new BorderPane();
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        VBox leftBox = new VBox();
        Label currentCombatant = new Label("Current Combatant: ");
        GridPane gPane = new GridPane();

        //sets up the tracker
        initiativeTracker = new InitiativeTracker();
        listView = new ListView<Combatant>();

        //creates the buttons and adds their functionality
        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> System.out.println("nextTurn pushed")); //temp-Change when nextTurn implemented

        Button newCombatant = new Button("Add New Combatant");
        newCombatant.setOnAction(event -> {
            new addNewCombatant().newCombatantStage(this);
        });

        Button loadCombatants = new Button("Load Combatants");

        Button removeCombatant = new Button("Remove Combatant");
        removeCombatant.setOnAction(event -> {
            Combatant selected = listView.getSelectionModel().getSelectedItem();
            initiativeTracker.removeCombatant(selected);
            listView.getItems().remove(selected);
            refresh();
        });

        //sets-up the gridpane
        /*
        No Longer Used... wasn't working. May be used later
        gPane.add(listView,0,0);
        gPane.maxWidthProperty().bind(initStage.widthProperty());
         */

        bottomBox.getChildren().add(nextTurn);
        bottomBox.getChildren().add(newCombatant);
        bottomBox.getChildren().add(removeCombatant);
        bottomBox.getChildren().add(loadCombatants);
        topBox.getChildren().add(currentCombatant);

        //putting boxes/list into borderPane
        borderPane.setCenter(listView); //swap to gridpane if using that
        borderPane.setLeft(leftBox);
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        return borderPane;
    }

    /**
     * Gets initiativeTracker
     * @return initiativeTracker
     */
    public InitiativeTracker getInitiativeTracker() {
        return initiativeTracker;
    }

    /**
     * Updates the ListView
     */
    public void refresh(){

        if(initiativeTracker.hasCombatants()){
            ObservableList<Combatant> combatants = FXCollections.observableArrayList(initiativeTracker.getCombatants());
            listView.getItems().removeAll();
            listView.getItems().addAll(combatants);
        } else{
            listView.getItems().removeAll();
        }
    }

    /**
     * Generates the scene to be displayed
     */
    public void initStage(){
        initStage = new Stage();
        initStage.setResizable(true);
        initStage.setScene(new Scene(getInitPane()));
        initStage.sizeToScene();
        initStage.setTitle("Initiative Tracker");
        initStage.show();
    }
}
