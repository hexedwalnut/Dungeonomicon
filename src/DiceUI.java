import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class DiceUI {

    private Button roll3 = new Button("Roll D3");
    private Button roll4 = new Button("Roll D4");
    private Button roll6 = new Button("Roll D6");
    private Button roll8 = new Button("Roll D8");
    private Button roll10 = new Button("Roll D10");
    private Button roll12 = new Button("Roll D12");
    private Button roll20 = new Button("Roll D20");
    private Button roll100 = new Button("Roll D100");
    private Label modifier = new Label("Modifier");


    public GridPane getDicePane(){
        GridPane pane = new GridPane();
        Pane diceImage = new Pane();
        pane.add(diceImage,0,0,3,3);
        modifier.setPrefSize(roll3.getPrefHeight(),roll3.getPrefWidth());



        //allows the buttons to grow to desired size
        roll3.setMaxWidth(Double.MAX_VALUE);
        roll4.setMaxWidth(Double.MAX_VALUE);
        roll6.setMaxWidth(Double.MAX_VALUE);
        roll8.setMaxWidth(Double.MAX_VALUE);
        roll10.setMaxWidth(Double.MAX_VALUE);
        roll12.setMaxWidth(Double.MAX_VALUE);
        roll20.setMaxWidth(Double.MAX_VALUE);

        //has buttons fill to column width
        pane.setFillWidth(roll3,true);
        pane.setFillWidth(roll4,true);
        pane.setFillWidth(roll6,true);
        pane.setFillWidth(roll8,true);
        pane.setFillWidth(roll10,true);
        pane.setFillWidth(roll12,true);
        pane.setFillWidth(roll20,true);

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

        return pane;
    }
}
