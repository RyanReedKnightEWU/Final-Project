package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

import static FinalProject.Javafx.ApplicationMain.scene;

public class InventoryScene {
    private VBox items;
    private HBox options = new HBox();
    private BorderPane layout = new BorderPane();
    private Button exit = new Button("Exit");
    private MapScene map;
    private Entity player;
    private Label playerInfo = new Label();

    public void start(MapScene map,Entity player){
        this.map = map;
        this.player = player;
        exit.setOnAction(e -> exit());

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);
        options.getChildren().add(exit);

        playerInfo.setText(player.toString());
        playerInfo.setFont(new Font("System Regular", 25));
        playerInfo.setText(player.toString());

        makeButtons();

        layout.setTop(playerInfo);
        layout.setCenter(items);
        layout.setBottom(options);
        scene.setRoot(layout);

    }

    public void reset(){

    }

    public void makeButtons(){
        items = new VBox();
        items.setAlignment(Pos.CENTER);
        items.setSpacing(10);
        items.setStyle("-fx-background-color: rgb(80,40,10)");
        ArrayList<Items> stuff = player.getInventory();
        boolean currentWeapon = true;
        boolean currentArmor = true;

        for(Items i: stuff){
            Button b = new Button(i.toString());
            b.setTooltip(new Tooltip(i.getDescription()));
            if(player.getWeapon().toString().equals(i.toString())&&currentWeapon){
                b.setStyle("-fx-background-color: rgb(150,200,150)");
                currentWeapon = false;
            } else if(player.getArmor().toString().equals(i.toString())&&currentArmor){
                b.setStyle("-fx-background-color: rgb(120,200,120)");
                currentArmor = false;
            } else if(i.getType().equals("Weapon")){
                b.setStyle("-fx-background-color: rgb(175,150,120)");
                b.setOnAction(e -> {
                    player.setWeapon((Weapon) i);
                    makeButtons();
                    playerReset();
                });
            } else if(i.getType().equals("Armor")){
                b.setStyle("-fx-background-color: rgb(100,100,175)");
                b.setOnAction(e -> {
                    player.setArmor((Armor) i);
                    makeButtons();
                    playerReset();
                });
            } else {
                b.setStyle("-fx-background-color: rgb(150,190,200)");
                Consumable con = (Consumable) i;
                b.setOnAction(e -> {
                    con.use(player);
                    playerReset();
                    makeButtons();
                });
            }

            buttonEffects(b);
            items.getChildren().add(b);
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
        map.setScene();
    }

}
