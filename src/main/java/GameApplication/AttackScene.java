package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Consumable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import static FinalProject.Javafx.ApplicationMain.scene;

public class AttackScene {
    private static BorderPane layout = new BorderPane();
    private HBox options = new HBox();
    private Button attack, consumables, runAway;
    private static Label badGuyInfo;
    private static Label playerInfo;

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

        badGuyInfo = new Label();
        playerInfo = new Label();

        layout.setBottom(options);
        layout.setCenter(badGuyInfo);
        layout.setLeft(playerInfo);

    }

    public static void start(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        playerInfo.setText(player.toString());
        scene.setRoot(layout);
    }

    private void showStuff(){

    }

}
