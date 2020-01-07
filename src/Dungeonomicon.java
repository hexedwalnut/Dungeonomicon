import DiceRoller.DiceUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dungeonomicon extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene());
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    private Scene mainScene () {
        VBox pane = new VBox();

        //Labels
        Label banner = new Label("Welcome to the Dungeonomicon!");
        banner.setAlignment(Pos.CENTER);

        //Buttons
        Button settings = new Button("Settings");
        settings.maxWidthProperty().bind(pane.widthProperty());

        Button diceRoller = new Button("Dice Roller");
        diceRoller.maxWidthProperty().bind(pane.widthProperty());
        diceRoller.setOnAction(event -> new DiceUI().diceStage());

        Button initTracker = new Button("Initiative Tracker");
        initTracker.maxWidthProperty().bind(pane.widthProperty());

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.spacingProperty().set(10);
        pane.getChildren().addAll(banner, settings, diceRoller, initTracker);

        return new Scene(pane);
    }
}
