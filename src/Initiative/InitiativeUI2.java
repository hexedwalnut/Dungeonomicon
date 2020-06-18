package Initiative;

import Persistance.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class InitiativeUI2 extends InitiativeUI{

    //variables---------------------------------------------------------------------------------------------------------
    private Stage initStage;
    private ListView<Combatant> listView;
    private InitiativeTracker initiativeTracker;
    private ArrayList<Node> combats;
    private GridPane gPane;
    private Stage newCombatantStage;

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
        gPane = new GridPane();

        gPane.setMinHeight(100);

        //sets up the tracker
        initiativeTracker = new InitiativeTracker();
        listView = new ListView<Combatant>();

        //creates the buttons and adds their functionality
        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> {
            try{
                Combatant combatant = initiativeTracker.nextTurn();
                currentCombatant.setText("Current Combatant: "+ combatant.toString());
                //highlight Current Combatant in grid

            }catch(IndexOutOfBoundsException e){
                new errorWindow().errorWindow("No Combatants to have a turn!\n Please add some Combatants and try " +
                        "again.");
            }
        });


        MenuBar menuBarNewCombatant = new MenuBar();
        Menu menuNewCombatant = new Menu("New Combatant");

        MenuItem itemNewCombatantNPC = new MenuItem("NPC");
        itemNewCombatantNPC.setOnAction(event -> {
            newCombatantStage = new Stage();
            newCombatantStage.setScene(new Scene(getNewNPCPane()));
            newCombatantStage.setTitle("Create a New NPC");
            newCombatantStage.setResizable(false);
            newCombatantStage.sizeToScene();
            newCombatantStage.show();
        });

        MenuItem itemNewCombatantPC = new MenuItem("PC");
        itemNewCombatantPC.setOnAction(event -> {
            newCombatantStage = new Stage();
            newCombatantStage.setScene(new Scene(getNewPCPane()));
            newCombatantStage.setTitle("Create a New PC");
            newCombatantStage.setResizable(false);
            newCombatantStage.sizeToScene();
            newCombatantStage.show();
        });

        menuNewCombatant.getItems().addAll(itemNewCombatantNPC, itemNewCombatantPC);
        menuBarNewCombatant.getMenus().add(menuNewCombatant);


        Button loadCombatants = new Button("Load Combatants");


        bottomBox.getChildren().add(nextTurn);
        bottomBox.getChildren().add(menuBarNewCombatant);
        bottomBox.getChildren().add(loadCombatants);

        topBox.getChildren().add(currentCombatant);



        //putting boxes/list into borderPane
        borderPane.setCenter(gPane); //swap to gridpane if using that
        borderPane.setLeft(leftBox);
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        borderPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return borderPane;
    }

    /**
     * sets-up the pane for the stage
     * @return GridPane
     */
    public GridPane getNewPCPane(){
        //Declaring labels
        GridPane gridPane = new GridPane();
        Label nameLabel = new Label("Name: ");
        Label initLabel = new Label("Initiative: ");

        //Declaring and setting phantom Text of TextFields
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");

        TextField initText = new TextField();
        initText.setPromptText("17");

        //Buttons
        //Ok Button
        Button addButton = new Button("Ok");
        addButton.setOnAction(event -> {
            try{
                Combatant newCombatant = new PlayerCharacter(Integer.parseInt(initText.getText()), nameText.getText());
                getInitiativeTracker().addCombatant(newCombatant);
                refresh();
                newCombatantStage.close();
            }catch(NumberFormatException nf){
                new errorWindow().errorWindow("A number could not be formatted correctly \n Please try again");
            }
        });

        //Cancel Button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> newCombatantStage.close());

        //adds everything to the gridPane
        gridPane.add(nameLabel, 0,0);
        gridPane.add(nameText, 1, 0);
        gridPane.add(initLabel,0, 1);
        gridPane.add(initText, 1,1);
        gridPane.add(addButton,0,2);
        gridPane.add(cancelButton,1,2);

        gridPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return gridPane;
    }

    /**
     * sets-up the pane for the stage
     * @return GridPane
     */
    public GridPane getNewNPCPane(){
        //Declaring labels
        GridPane gridPane = new GridPane();
        Label nameLabel = new Label("Name: ");
        Label initLabel = new Label("Initiative: ");
        Label healthLabel = new Label("Health: ");
        Label acLabel = new Label("Armor Class: ");

        //Declaring and setting phantom Text of TextFields
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");

        TextField initText = new TextField();
        initText.setPromptText("17");

        TextField healthText = new TextField();
        healthText.setPromptText("25");

        TextField acText = new TextField();
        acText.setPromptText("17");

        //Buttons
        //Ok Button
        Button addButton = new Button("Ok");
        addButton.setOnAction(event -> {
            try{
                Combatant newCombatant = new NonPlayerCharacter(Integer.parseInt(initText.getText()),
                        nameText.getText(),Integer.parseInt(healthText.getText()), Integer.parseInt(acText.getText()));
                getInitiativeTracker().addCombatant(newCombatant);
                refresh();
                newCombatantStage.close();
            }catch(NumberFormatException nf){
                new errorWindow().errorWindow("A number could not be formatted correctly \n Please try again");
            }
        });

        //Cancel Button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> newCombatantStage.close());

        //adds everything to the gridPane
        gridPane.add(nameLabel, 0,0);
        gridPane.add(nameText, 1, 0);
        gridPane.add(initLabel,0, 1);
        gridPane.add(initText, 1,1);
        gridPane.add(healthLabel, 0,2);
        gridPane.add(healthText,1,2);
        gridPane.add(acLabel,0,3);
        gridPane.add(acText,1,3);
        gridPane.add(addButton,0,6);
        gridPane.add(cancelButton,1,6);

        gridPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return gridPane;
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
        if(gPane.getChildren().size() != 0){
            gPane.getChildren().removeAll(combats);
        }
        if(initiativeTracker.hasCombatants()){
            initiativeTracker.sortCombatants();
            ObservableList<Combatant> combatants = FXCollections.observableArrayList(initiativeTracker.getCombatants());
            combats = new ArrayList<Node>();
            for (Combatant combatant: combatants) {
                combats.add(new Label(combatant.toString()));
                Button removeButton = new Button("Remove Combatant");
                removeButton.setOnAction(event -> {
                    Combatant selected = combatants.get(combats.indexOf(this)-1);
                    initiativeTracker.removeCombatant(selected);
                    gPane.getChildren().remove(selected);
                    refresh();
                });
                combats.add(removeButton);
                Button editCombatant = new Button("Edit Combatant");
                editCombatant.setOnAction(event -> {
                    new EditCombatant().editStage(this, combatants.get(combats.indexOf(this)-2));
                });
                combats.add(editCombatant);
            }
            for (int i = 0; i < combats.size(); i+=3) {
                gPane.add(combats.get((i)),0,i);
                gPane.add(combats.get((i)+1),1,i);
                gPane.add(combats.get((i)+2),2,i);
            }

        } else{
            listView.getItems().removeAll(combats);
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
        initStage.setTitle("Initiative Tracker 2.0");
        initStage.show();
    }
}
