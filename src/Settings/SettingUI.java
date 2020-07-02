package Settings;

import Persistence.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingUI {

    private Stage stage;

    public BorderPane getSettingPane(){
        BorderPane borderPane = new BorderPane();
        GridPane gPane = new GridPane();
        HBox bottomBox = new HBox();

        Button previewButton = new Button("Preview");

        Button saveButton = new Button("", new ImageView(new Image("/Persistence/icons/save.png")));
        Tooltip saveTip = new Tooltip("Saves the settings");
        saveButton.setTooltip(saveTip);

        Label scaleLabel = new Label("Interfaces Scale");
        Spinner<Integer> scaleSpinner = new Spinner<Integer>();
        scaleSpinner.setEditable(true);
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,300,100);
        scaleSpinner.setValueFactory(valueFactory);

        Label themeLabel = new Label("Theme");
        ObservableList<String> themes = FXCollections.observableArrayList("Light", "Dark");
        ComboBox<String> themeComboBox = new ComboBox<String>(themes);

        gPane.add(scaleLabel,0,0);
        gPane.add(scaleSpinner,1,0);
        gPane.add(themeLabel,0,1);
        gPane.add(themeComboBox,1,1);

        bottomBox.getChildren().add(saveButton);
        bottomBox.getChildren().add(previewButton);

        borderPane.setCenter(gPane);
        borderPane.setBottom(bottomBox);

        borderPane.getStylesheets().add(SettingsStorage.class.getResource("main.css").toExternalForm());
        return borderPane;
    }

    public void settingStage(){
        stage = new Stage();
        stage.setResizable(true);
        stage.setScene(new Scene(getSettingPane()));
        stage.sizeToScene();
        stage.setTitle("Settings");
        stage.show();
    }
}
