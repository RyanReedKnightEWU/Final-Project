package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Consumable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AttackScene {
    private BorderPane layout = new BorderPane();
    private HBox options = new HBox();
    private Button attack, consumables, runAway;

    public AttackScene(){
        attack = new Button("Attack");
        consumables = new Button("Use Consumable");
        runAway = new Button("Run Away");

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);

        options.getChildren().add(attack);
        options.getChildren().add(consumables);



    }

    public static void start(Player player, Entity badGuy){


    }

    private void showStuff(){

    }

}
