import DiceRoller.DiceUI;
import Initiative.InitiativeUI;
import Initiative.InitiativeUI2;
import Persistence.SettingsStorage;
import Settings.SettingUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main JavaFX application launcher for the dungeonomicon table top role playing game assistance software.
 * Features include
 *     Dice Roller
 *     Initiative Tracker (planned)
 *     Player Character Sheet (planned)
 *
 * @author Joe Teahen
 * @author Matt Schwennesen
 * @author Patrick Philbin
 * @author Thomas Grifka
 * @author Daniel Masker
 */

public class Dungeonomicon extends Application {

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * launches the stage for the main menu
     *
     * @param primaryStage - stage given from the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene());
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Creates the scene for the main menu stage
     *
     * @return - the scene for the main menu stage
     */
    private Scene mainScene() {
        VBox pane = new VBox();

        //Labels
        Label banner = new Label("Welcome to the Dungeonomicon!");
        banner.setAlignment(Pos.CENTER);

        //Buttons
        Button settings = new Button("Settings");
        settings.maxWidthProperty().bind(pane.widthProperty());
        settings.setOnAction(event -> new SettingUI().settingStage());

        Button diceRoller = new Button("Dice Roller");
        diceRoller.maxWidthProperty().bind(pane.widthProperty());
        diceRoller.setOnAction(event -> new DiceUI().diceStage());

        Button initTracker = new Button("Initiative Tracker");
        initTracker.maxWidthProperty().bind(pane.widthProperty());
        initTracker.setOnAction(event -> new InitiativeUI().initStage());

        Button initTracker2 = new Button("Initiative Tracker 2.0-In Dev");
        initTracker.maxWidthProperty().bind(pane.widthProperty());
        initTracker2.setOnAction(event -> new InitiativeUI2().initStage());

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.spacingProperty().set(10);
        pane.getChildren().addAll(banner, settings, diceRoller, initTracker, initTracker2);

        pane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());

        return new Scene(pane);
    }
}
