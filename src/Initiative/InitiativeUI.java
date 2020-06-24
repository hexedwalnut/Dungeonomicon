package Initiative;

import Persistence.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Creates the UI framework for the initiative tracker
 *
 * @author Matt
 * @author Thomas
 */
public class InitiativeUI {

    private ListView<Combatant> listView;
    private InitiativeTracker initiativeTracker;

    //methods-----------------------------------------------------------------------------------------------------------

    /**
     * Generates the Pane for the stage
     * @return BorderPane containing everything for the tracker
     */
    public BorderPane getInitPane(){
        //Declaring a bunch of stuff
        BorderPane borderPane = new BorderPane();
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        VBox leftBox = new VBox();
        Label currentCombatant = new Label("Current Combatant: ");


        //sets up the tracker
        initiativeTracker = new InitiativeTracker();
        listView = new ListView<>();

        //creates the buttons and adds their functionality
        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> {
            try{
                currentCombatant.setText("Current Combatant: "+ initiativeTracker.nextTurn().toString());
            }catch(IndexOutOfBoundsException e){
                new errorWindow().errorWindow("No Combatants to have a turn!\n Please add some Combatants and try " +
                        "again.");
            }
        });

        MenuBar menuBarNewCombatant = new MenuBar();
        menuBarNewCombatant.setPadding(new Insets(0,0,0,0));
        Menu menuNewCombatant = new Menu("New Combatant");

        MenuItem itemNewCombatantNPC = new MenuItem("NPC");
        itemNewCombatantNPC.setOnAction(event -> newNPC());

        MenuItem itemNewCombatantPC = new MenuItem("PC");
        itemNewCombatantPC.setOnAction(event -> newPC());

        menuNewCombatant.getItems().addAll(itemNewCombatantNPC, itemNewCombatantPC);
        menuBarNewCombatant.getMenus().add(menuNewCombatant);

        Button editCombatant = new Button("Edit Combatant");
        editCombatant.setOnAction(event -> {
            if(listView.getSelectionModel().getSelectedItem() != null)
                new EditCombatant().editStage(this, listView.getSelectionModel().getSelectedItem());
            else
                new errorWindow().errorWindow("No Combatant was Selected");
        });

        Button loadCombatants = new Button("Load Combatants");

        Button removeCombatant = new Button("Remove Combatant");
        removeCombatant.setOnAction(event -> {
            Combatant selected = listView.getSelectionModel().getSelectedItem();
            initiativeTracker.removeCombatant(selected);
            listView.getItems().remove(selected);
            refresh();
        });

        bottomBox.getChildren().addAll(nextTurn, menuBarNewCombatant, removeCombatant, loadCombatants, editCombatant);
        topBox.getChildren().add(currentCombatant);

        //putting boxes/list into borderPane
        borderPane.setCenter(listView); //swap to gridpane if using that
        borderPane.setLeft(leftBox);
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        borderPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return borderPane;
    }

    /**
     * Creates and launches a new stage for PC addition to the initiative tracker
     */
    private void newPC(){
        Stage newCombatantStage = new Stage();

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

        newCombatantStage.setScene(new Scene(gridPane));
        newCombatantStage.setTitle("Create a New PC");
        newCombatantStage.setResizable(false);
        newCombatantStage.sizeToScene();
        newCombatantStage.show();
    }

    /**
     * Creates and launches a new stage for NPC addition to the initiative tracker
     */
    private void newNPC(){
        Stage newCombatantStage = new Stage();

        //Declaring labels
        GridPane gridPane = new GridPane();
        Label nameLabel = new Label("Name: ");
        Label initLabel = new Label("Initiative: ");
        Label healthLabel = new Label("Health: ");
        Label acLabel = new Label("Armor Class: ");

        //Declaring and setting phantom Text of TextFields
        TextField nameText = new TextField();
        nameText.setPromptText("Goblin 1/Twitch");
        nameText.setStyle("-fx-prompt-text-fill: #FF5733"); //Use Hex Color Codes to change the color

        TextField initText = new TextField();
        initText.setPromptText("17");
        initText.setStyle("-fx-prompt-text-fill: #E7D278"); //Use Hex Color Codes to change the color

        TextField healthText = new TextField();
        healthText.setPromptText("25");
        healthText.setStyle("-fx-prompt-text-fill: #FF0000"); //Use Hex Color Codes to change the color

        TextField acText = new TextField();
        acText.setPromptText("17");
        acText.setStyle("-fx-prompt-text-fill: #B6E778"); //Use Hex Color Codes to change the color

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

        newCombatantStage.setScene(new Scene(gridPane));
        newCombatantStage.setTitle("Create a New NPC");
        newCombatantStage.setResizable(false);
        newCombatantStage.sizeToScene();
        newCombatantStage.show();
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
        if (listView.getItems().size() > 1) {
            listView.getItems().remove(0, listView.getItems().size() - 1);
        } else if (listView.getItems().size() == 1) {
            listView.getItems().remove(0);
        }

        initiativeTracker.sortCombatants();
        ObservableList<Combatant> combatants = FXCollections.observableArrayList(initiativeTracker.getCombatants());
        listView.getItems().addAll(combatants);
    }

    /**
     * Generates the scene to be displayed
     */
    public void initStage(){
        //variables---------------------------------------------------------------------------------------------------------
        Stage initStage = new Stage();
        initStage.setResizable(true);
        initStage.setScene(new Scene(getInitPane()));
        initStage.sizeToScene();
        initStage.setTitle("Initiative Tracker");
        initStage.show();
    }
}
