import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dungeonomicon extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DiceUI diceUI = new DiceUI();
        Scene scene = new Scene(diceUI.getDicePane());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
