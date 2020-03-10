package Initiative;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class errorWindow {

    //Variables---------------------------------------------------------------------------------------------------------
    private Stage newStage;

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * Sets-up the pane for the ErrorWindo
     * @param error the error message
     * @return GridPane with the error message
     */
    public GridPane getPane(String error){
        GridPane pane = new GridPane();
        Label label = new Label("Error: "+ error);

        Button okButton = new Button("Ok");
        okButton.setOnAction(event -> newStage.close());

        pane.add(label,0,0);
        pane.add(okButton,0,1);

        GridPane.setHalignment(okButton, HPos.CENTER);

        return pane;
    }

    /**
     * Sets-up the scene for the ErrorWindow
     * @param error the error Message
     */
    public void errorWindow(String error){
        newStage = new Stage();
        newStage.setResizable(false);
        newStage.setScene(new Scene(getPane(error)));
        newStage.sizeToScene();
        newStage.setTitle("Error!");
        newStage.show();
    }
}
