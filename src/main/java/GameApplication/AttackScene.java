package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Consumable;
import gameobjects.Navigator.Navigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static FinalProject.Javafx.ApplicationMain.scene;

public class AttackScene {
    private static BorderPane layout = new BorderPane();
    private HBox options = new HBox();
    private static Button attack;
    private Button consumables;
    private Button runAway;
    private Label badGuyInfo = new Label();
    private Label playerInfo = new Label();
    private boolean consume;
    private MapScene map;

    public AttackScene(MapScene map){
        this.map = map;
        attack = new Button("Attack");
        consumables = new Button("Use Consumable");
        runAway = new Button("Run Away");

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);

        options.getChildren().add(attack);
        options.getChildren().add(consumables);
        options.getChildren().add(runAway);

        badGuyInfo = new Label();
        badGuyInfo.setFont(new Font("System Regular", 15));
        playerInfo = new Label();
        playerInfo.setFont(new Font("System Regular", 15));

        layout.setBottom(options);
        layout.setCenter(badGuyInfo);
        layout.setLeft(playerInfo);

        runAway.setOnAction(e -> useRunAway());

    }

    public void start(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        playerInfo.setText(player.toString());

        attack.setOnAction(e -> attackBadGuy(player, badGuy));

        scene.setRoot(layout);
    }

    private void attackBadGuy(Player player, Entity badGuy){
        badGuy.takeDamage(player.getDamage());
        consume = true;
        if(!badGuy.isAlive()){
            loot();
        }else {
            badGuyTurn(player, badGuy);
        }
    }

    private static void badGuyTurn(Player player, Entity badGuy){
        player.takeDamage(badGuy.getDamage());
    }

    private void useConsumables(){
        if(consume){
            consume = false;
        }
    }

    private void useRunAway(){
        map.setScene();
    }

    private void loot(){
        map.setScene();
    }

    private void showStuff(){
        //Shows players consumables
    }
}
