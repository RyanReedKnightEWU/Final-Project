package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Consumable;
import gameobjects.Navigator.Navigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static FinalProject.Javafx.ApplicationMain.scene;

public class AttackScene {
    private static BorderPane layout = new BorderPane();
    private HBox options = new HBox();
    private VBox info = new VBox();
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
        badGuyInfo.setFont(new Font("System Regular", 25));
        playerInfo = new Label();
        playerInfo.setFont(new Font("System Regular", 25));

        layout.setBottom(options);
        info.getChildren().add(badGuyInfo);
        info.getChildren().add(playerInfo);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(100);
        layout.setCenter(info);

        runAway.setOnAction(e -> useRunAway());
    }

    public void start(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        badGuyInfo.setStyle("-fx-background-color: rgb(250,200,200);");
        playerInfo.setText(player.toString());
        playerInfo.setStyle("-fx-background-color: rgb(200,200,250);");

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
