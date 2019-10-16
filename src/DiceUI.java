import javafx.scene.control.Button;
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


    public GridPane getDicePane(){
        GridPane pane = new GridPane();
        Pane diceImage = new Pane();
        pane.add(diceImage,0,0,3,3);

        pane.add(roll3, 0,3);
        pane.add(roll4, 1,3);
        pane.add(roll6, 2,3);
        pane.add(roll8, 0,4);
        pane.add(roll10, 1,4);
        pane.add(roll12, 2,4);
        pane.add(roll20, 0,5);
        pane.add(roll100, 1,5);

        return pane;
    }
}
