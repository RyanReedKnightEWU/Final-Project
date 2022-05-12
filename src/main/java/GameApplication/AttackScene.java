package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Consumable;
import gameobjects.Navigator.Navigator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import static FinalProject.Javafx.ApplicationMain.scene;

public class AttackScene {
    private static BorderPane layout = new BorderPane();
    private HBox options = new HBox();
    private static Button attack;
    private Button consumables;
    private Button runAway;
    private static Label badGuyInfo;
    private static Label playerInfo;
    private static boolean consume;
    private static MapScene map;

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

        badGuyInfo = new Label();
        playerInfo = new Label();

        layout.setBottom(options);
        layout.setCenter(badGuyInfo);
        layout.setLeft(playerInfo);

        runAway.setOnAction(e -> useRunAway());

    }

    public static void start(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        playerInfo.setText(player.toString());

        attack.setOnAction(e -> attackBadGuy(player, badGuy));

        scene.setRoot(layout);
    }

    private static void attackBadGuy(Player player, Entity badGuy){
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
        Navigator nav = Navigator.getInstance();
        map.reset(nav);
    }

    private static void loot(){
        //send badguy stuff to player.
        Navigator nav = Navigator.getInstance();
        map.reset(nav);
    }

    private void showStuff(){
        //Shows players consumables
    }
}
