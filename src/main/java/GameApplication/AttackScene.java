package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Navigator.Navigator;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.lang.annotation.Target;
import java.util.ArrayList;

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
    private Label damageInfo = new Label();
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
        consumables.setOnAction(e -> useConsumables(player, badGuy));

        scene.setRoot(layout);
    }

    public void back(Player player, Entity badGuy){
        scene.setRoot(layout);
        reset(player, badGuy);
        if(!badGuy.isAlive()){
            loot();
        }
    }

    private void reset(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        playerInfo.setText(player.toString());
    }

    private void attackBadGuy(Player player, Entity badGuy){
        badGuy.takeDamage(player.getDamage());
        reset(player, badGuy);
        consume = true;
        if(!badGuy.isAlive()){
            System.out.println("You looted the bad guy");
            loot();
        }else {
            badGuyTurn(player, badGuy);
            reset(player, badGuy);
        }
    }

    private static void badGuyTurn(Player player, Entity badGuy){
        player.takeDamage(badGuy.getDamage());
    }

    private void useConsumables(Player player, Entity badGuy){
        AttackSceneInventory attackSceneInventory = new AttackSceneInventory();
        attackSceneInventory.start(this, player, badGuy);
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

class AttackSceneInventory{
    private VBox items;
    private HBox options = new HBox();
    private BorderPane layout = new BorderPane();
    private Button exit = new Button("Exit");
    private Player player;
    private Entity target, badGuy;
    private Label playerInfo = new Label();
    private AttackScene attackScene;
    private Button you, them;
    boolean used = false;

    public void start(AttackScene back,Player player, Entity badGuy){
        this.attackScene = back;
        this.player = player;
        this.badGuy = badGuy;
        exit.setOnAction(e -> exit());

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);
        options.getChildren().add(exit);

        playerInfo.setText(player.toString());
        playerInfo.setFont(new Font("System Regular", 25));
        playerInfo.setText(player.toString());

        you = new Button("You");
        String style = you.getStyle();
        you.setStyle("-fx-background-color: rgb(100,255,100)");
        target = player;
        you.setOnAction(e -> {
            target = player;
            you.setStyle("-fx-background-color: rgb(100,255,100)");
            them.setStyle(style);
        });
        them = new Button("Them");
        them.setOnAction(e -> {
            target = badGuy;
            them.setStyle("-fx-background-color: rgb(100,255,100)");
            you.setStyle(style);
        });

        makeButtons();

        layout.setTop(playerInfo);
        layout.setCenter(items);
        layout.setBottom(options);
        scene.setRoot(layout);

    }

    public void makeButtons(){
        items = new VBox();
        items.getChildren().add(you);
        items.getChildren().add(them);
        items.setAlignment(Pos.CENTER);
        items.setSpacing(10);
        items.setStyle("-fx-background-color: rgb(80,40,10)");
        ArrayList<Items> stuff = player.getInventory();

        for(Items i: stuff){
            if(i.getType().equals("Consumable")){
                Button b = new Button(i.toString());
                b.setTooltip(new Tooltip(i.getDescription()));
                b.setStyle("-fx-background-color: rgb(150,190,200)");
                buttonEffects(b);
                Consumable con = (Consumable) i;
                b.setOnAction(e -> {
                    con.use(target);
                    playerReset();
                    makeButtons();
                    used = true;
                    exit();
                });
                items.getChildren().add(b);
            }
        }
        layout.setCenter(items);
    }

    public void buttonEffects(Button b){
        b.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        b.setStyle("-fx-background-color: rgb(100,255,100)");
                    }
                });
        String before = b.getStyle();
        b.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        b.setStyle(before);
                    }
                });
    }

    public void playerReset(){
        playerInfo.setText(player.toString());
    }

    public void exit(){
        attackScene.back(player, badGuy);
    }
}
