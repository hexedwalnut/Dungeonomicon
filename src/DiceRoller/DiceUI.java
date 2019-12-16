package DiceRoller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DiceUI {
    //Labels
    private Label diceOutput = new Label("Test");
    private Label doubleDiceOutput = new Label("Test");
    private Label halfDiceOutput = new Label("Test");
    private Label quarterDiceOutput = new Label("Test");

    private SimpleIntegerProperty intDiceOutput = new SimpleIntegerProperty();
    //Buttons
    private Button roll3 = new Button("Roll D3");
    private Button roll4 = new Button("Roll D4");
    private Button roll6 = new Button("Roll D6");
    private Button roll8 = new Button("Roll D8");
    private Button roll10 = new Button("Roll D10");
    private Button roll12 = new Button("Roll D12");
    private Button roll20 = new Button("Roll D20");
    private Button roll100 = new Button("Roll D100");
    private Button clear = new Button("Clear");
    //TextFields
    private TextField modifier = new TextField();
    private TextField custRoll = new TextField();


    public GridPane getDicePane() {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(false);
        modifier.setPrefColumnCount(0);
        custRoll.setPrefColumnCount(0);
        doubleDiceOutput.setFont(new Font("Ariel", 24));
        diceOutput.setFont(new Font("Ariel", 48));
        halfDiceOutput.setFont(new Font("Ariel", 24));
        quarterDiceOutput.setFont(new Font("Ariel", 12));
        VBox diceResult = new VBox();
        diceResult.setAlignment(Pos.CENTER);
        diceResult.getChildren().addAll(doubleDiceOutput, diceOutput, halfDiceOutput, quarterDiceOutput);
        pane.add(diceResult, 0, 0, 3, 3);

        doubleDiceOutput.textProperty().bind(intDiceOutput.multiply(2).asString());
        diceOutput.textProperty().bind(intDiceOutput.asString());
        halfDiceOutput.textProperty().bind(intDiceOutput.divide(2.0).asString());
        quarterDiceOutput.textProperty().bind(intDiceOutput.divide(4.0).asString());

        modifier.setPromptText("Modifier");
        modifier.setStyle("-fx-alignment: center");

        custRoll.setPromptText("Custom Roll");


        //allows the nodes to grow to desired size
        roll3.setMaxWidth(Double.MAX_VALUE);
        roll4.setMaxWidth(Double.MAX_VALUE);
        roll6.setMaxWidth(Double.MAX_VALUE);
        roll8.setMaxWidth(Double.MAX_VALUE);
        roll10.setMaxWidth(Double.MAX_VALUE);
        roll12.setMaxWidth(Double.MAX_VALUE);
        roll20.setMaxWidth(Double.MAX_VALUE);
        clear.setMaxWidth(Double.MAX_VALUE);

        //has everything fill to column width
        GridPane.setFillWidth(roll3, true);
        GridPane.setFillWidth(roll4, true);
        GridPane.setFillWidth(roll6, true);
        GridPane.setFillWidth(roll8, true);
        GridPane.setFillWidth(roll10, true);
        GridPane.setFillWidth(roll12, true);
        GridPane.setFillWidth(roll20, true);
        GridPane.setFillWidth(clear, true);

        //sets button actions
        DiceRoller diceRoller = new DiceRoller();
        roll3.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(3)));
        roll4.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(4)));
        roll6.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(6)));
        roll8.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(8)));
        roll10.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(10)));
        roll12.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(12)));
        roll20.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(20)));
        roll100.setOnAction(e -> intDiceOutput.set(diceRoller.rollCustom(100)));
        custRoll.setOnAction(e -> {
            try {
                intDiceOutput.set(diceRoller.rollCalc(custRoll.getText()));
            } catch (RollFormatException ignored) {

            }
        });
        clear.setOnAction(e -> {
            intDiceOutput.set(0);
            diceRoller.clearTotal();
        });

        //adds everything to pane
        pane.add(roll3, 0, 3);
        pane.add(roll4, 1, 3);
        pane.add(roll6, 2, 3);
        pane.add(roll8, 0, 4);
        pane.add(roll10, 1, 4);
        pane.add(roll12, 2, 4);
        pane.add(roll20, 0, 5);
        pane.add(roll100, 1, 5);
        pane.add(modifier, 2, 5);
        pane.add(clear, 2, 6);
        pane.add(custRoll, 0, 6, 2, 1);

        return pane;
    }
}
