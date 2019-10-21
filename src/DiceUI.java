import DiceRoller.Dice;
import DiceRoller.DiceRoller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class DiceUI {
    //Labels
    private Label diceOutput = new Label();
    //Buttons
    private Button roll3 = new Button("Roll D3");
    private Button roll4 = new Button("Roll D4");
    private Button roll6 = new Button("Roll D6");
    private Button roll8 = new Button("Roll D8");
    private Button roll10 = new Button("Roll D10");
    private Button roll12 = new Button("Roll D12");
    private Button roll20 = new Button("Roll D20");
    private Button roll100 = new Button("Roll D100");
    private Button rollCust = new Button("Roll");
    //TextFields
    private TextField modifier = new TextField("Modifier");
    private TextField custRoll = new TextField("Custom Roll");


    public GridPane getDicePane(){
        GridPane pane = new GridPane();
        BorderPane diceImage = new BorderPane();
        pane.setGridLinesVisible(false);
        modifier.setPrefColumnCount(0);
        custRoll.setPrefColumnCount(0);
        diceImage.setCenter(diceOutput);
        diceOutput.setFont(new Font("Ariel",24));
        pane.add(diceImage,0,0,3,3);

        //allows the nodes to grow to desired size
        roll3.setMaxWidth(Double.MAX_VALUE);
        roll4.setMaxWidth(Double.MAX_VALUE);
        roll6.setMaxWidth(Double.MAX_VALUE);
        roll8.setMaxWidth(Double.MAX_VALUE);
        roll10.setMaxWidth(Double.MAX_VALUE);
        roll12.setMaxWidth(Double.MAX_VALUE);
        roll20.setMaxWidth(Double.MAX_VALUE);
        rollCust.setMaxWidth(Double.MAX_VALUE);

        //has everything fill to column width
        pane.setFillWidth(roll3,true);
        pane.setFillWidth(roll4,true);
        pane.setFillWidth(roll6,true);
        pane.setFillWidth(roll8,true);
        pane.setFillWidth(roll10,true);
        pane.setFillWidth(roll12,true);
        pane.setFillWidth(roll20,true);
        pane.setFillWidth(rollCust,true);

        //sets button actions
        roll3.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D3,1)));
        roll4.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D4,1)));
        roll6.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D6,1)));
        roll8.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D8,1)));
        roll10.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D10,1)));
        roll12.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D12,1)));
        roll20.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D20,1)));
        roll100.setOnAction(e -> diceOutput.setText(""+DiceRoller.roll(Dice.D100,1)));

        //adds everything to pane
        pane.add(roll3, 0,3);
        pane.add(roll4, 1,3);
        pane.add(roll6, 2,3);
        pane.add(roll8, 0,4);
        pane.add(roll10, 1,4);
        pane.add(roll12, 2,4);
        pane.add(roll20, 0,5);
        pane.add(roll100, 1,5);
        pane.add(modifier,2,5);
        pane.add(rollCust,2,6);
        pane.add(custRoll, 0,6,2,1);

        return pane;
    }
}
