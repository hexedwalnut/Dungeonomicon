package Initiative;

import Persistance.Combatants;
import Persistance.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class InitiativeUI2 extends InitiativeUI{

    //variables---------------------------------------------------------------------------------------------------------
    private Stage initStage;
    private InitiativeTracker initiativeTracker;
    private ArrayList<Node> nodes;
    private GridPane gPane;
    private Stage newCombatantStage;
    private Stage saveload; //needed for the FileChooser
    private Stage statusEffectsStage;

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

        //creates the buttons and adds their functionality
        Button nextTurn = new Button("Next Turn");
        nextTurn.setOnAction(event -> {
            //resets the style of every node(removes the highlight)
            for(Node node: gPane.getChildren()){
                node.setStyle(gPane.getStyle());
            }
            //attempts to move to a new combatant
            try{
                //cycles the combatant
                Combatant combatant = initiativeTracker.nextTurn();
                //sets the upper label to show the details of the current combatant
                currentCombatant.setText("Current Combatant: "+ combatant.toString());
                //gets the current combatants position
                int currentCombNum = initiativeTracker.getCursor();
                //sets the highlight
                gPane.getChildren().get(currentCombNum*3).setStyle("-fx-background-color: #424549");

            }catch(IndexOutOfBoundsException e){
                new errorWindow().errorWindow("No Combatants to have a turn!\n Please add some Combatants and try " +
                        "again.");
            }
        });

        Tooltip nextTip = new Tooltip("Moves through the turn order and highlights the current combatant.");
        nextTurn.setTooltip(nextTip);


        MenuBar menuBarNewCombatant = new MenuBar();
        Menu menuNewCombatant = new Menu("New Combatant");

        MenuItem itemNewCombatantNPC = new MenuItem("New NPC");
        itemNewCombatantNPC.setOnAction(event -> {
            newCombatantStage = new Stage();
            newCombatantStage.setScene(new Scene(getNewNPCPane()));
            newCombatantStage.setTitle("Create a New NPC");
            newCombatantStage.setResizable(false);
            newCombatantStage.sizeToScene();
            newCombatantStage.show();
        });

        MenuItem itemNewCombatantPC = new MenuItem("New PC");
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


        Button loadCombatants = new Button("Load");
        //loadCombatants.setStyle("-fx-background-image: url('../Persistance/Upload-Folder-512.png')");
        loadCombatants.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Combatants");
            fileChooser.setInitialDirectory(new File("XMLFiles"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XML", "*.xml"),
                    new FileChooser.ExtensionFilter("All Images", "*.*")
            );
            File file = fileChooser.showOpenDialog(saveload);
            Combatants combatants = new Combatants(file.getPath());
            combatants.Load();
            for (Combatant comb: combatants.getCombatants()) {
                initiativeTracker.addCombatant(comb);
                System.out.println(comb);
            }
            refresh();
        });

        Tooltip loadTip = new Tooltip("Loads a XML file that contains Combatants.");
        loadCombatants.setTooltip(loadTip);

        Button saveCombatants = new Button("Save");
        //saveCombatants.setStyle("-fx-background-image: url('../Persistance/saveIcon.png')");
        saveCombatants.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Combatants");
            fileChooser.setInitialDirectory(new File("XMLFiles"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XML", "*.xml")
            );
            File file = fileChooser.showSaveDialog(saveload);
            if(file != null) {
                Combatants combatants = new Combatants(file.getPath());
                combatants.AddCombatants(initiativeTracker.getCombatants());
                combatants.Save();
            }
        });

        Tooltip saveTip = new Tooltip("Saves the current Combatants to a XML file for future use.");
        saveCombatants.setTooltip(saveTip);

        bottomBox.getChildren().add(nextTurn);
        bottomBox.getChildren().add(menuBarNewCombatant);
        bottomBox.getChildren().add(loadCombatants);
        bottomBox.getChildren().add(saveCombatants);

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

    public BorderPane getStatusPane(Combatant combatant){
        BorderPane borderPane = new BorderPane();
        HBox topBox = new HBox();
        HBox botBox = new HBox();
        GridPane gridPane = new GridPane();

        Label combInfo = new Label(combatant.toString());
        topBox.getChildren().add(combInfo);

        Menu addEffectMenu = new Menu("New Effect");
        MenuBar effectsMenuBar = new MenuBar();

        MenuItem newStandardEffect = new MenuItem("Standard Effect");
        MenuItem newCustomEffect = new MenuItem("Custom Effect");

        addEffectMenu.getItems().addAll(newStandardEffect,newCustomEffect);

        effectsMenuBar.getMenus().add(addEffectMenu);

        botBox.getChildren().add(effectsMenuBar);

        borderPane.setBottom(botBox);
        borderPane.setCenter(gridPane);
        borderPane.setTop(topBox);

        borderPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());
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
        //checks to see if there are any combatants to remove
        if(gPane.getChildren().size() != 0){
            gPane.getChildren().removeAll(nodes);
        }

        //checks if there are combatants to load
        if(initiativeTracker.hasCombatants()) {
            //sorts the combatants
            initiativeTracker.sortCombatants();
            //gets an observable list of combatants
            ObservableList<Combatant> combatants = FXCollections.observableArrayList(initiativeTracker.getCombatants());
            //creates an arraylist to store the nodes for the combatants
            nodes = new ArrayList<Node>();
            for (Combatant combatant : combatants) {
                //creates the label that holds the combatants info
                nodes.add(new Label(combatant.toString()));

                Button statusEffectsButton = new Button("Effects");
                statusEffectsButton.setOnAction(event -> {
                    Combatant selected = combatants.get(nodes.indexOf(this) / 4);
                    statusEffectsStage = new Stage();
                    statusEffectsStage.setScene(new Scene(getStatusPane(selected)));
                    statusEffectsStage.setTitle(selected.getName()+"'s Current Effects");
                    statusEffectsStage.setResizable(true);
                    statusEffectsStage.sizeToScene();
                    statusEffectsStage.show();
                });
                Tooltip statsEffectsTip = new Tooltip("Brings up a interface to view the " +
                        "Combatants status effects");
                statusEffectsButton.setTooltip(statsEffectsTip);
                nodes.add(statusEffectsButton);

                //creates the remove button for the combatant
                Button removeButton = new Button("Remove");
                removeButton.setOnAction(event -> {
                    //gets the combatant based on location in a list
                    Combatant selected = combatants.get(nodes.indexOf(this) / 4); //this has to be division, but dont know why.
                    //removes the combatant and updates the display
                    initiativeTracker.removeCombatant(selected);
                    refresh();
                });
                Tooltip removeTip = new Tooltip("Removes the Combatant from the tracker.");
                removeButton.setTooltip(removeTip);
                //adds the remove button to the list of nodes
                nodes.add(removeButton);

                //creates the edit button for the combatant
                Button editCombatant = new Button("Edit");
                editCombatant.setOnAction(event -> {
                    //gets the combatant and generates the editing pane
                    new EditCombatant().editStage(this, combatants.get(nodes.indexOf(this) / 4)); //this has to be division, but dont know why.
                });
                Tooltip editTip = new Tooltip("Allows for editing of the Combatant.");
                editCombatant.setTooltip(editTip);
                nodes.add(editCombatant);
            }
            //adds the nodes to the grid pane in the right order
            for (int i = 0; i < nodes.size(); i += 4) {
                gPane.add(nodes.get((i)), 0, i);
                gPane.add(nodes.get((i) + 1), 1, i);
                gPane.add(nodes.get((i) + 2), 2, i);
                gPane.add(nodes.get((i) + 3), 3, i);
            }
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
