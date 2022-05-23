package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Factories.ItemFactory;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static FinalProject.Javafx.ApplicationMain.scene;

public class StoreScene {
    private VBox yourItems = new VBox();
    private VBox hisItems = new VBox();
    private HBox both;
    private HBox options = new HBox();
    private BorderPane layout = new BorderPane();
    private Button exit = new Button("Exit");
    private Button stack = new Button("Stack");
    private MapScene map;
    private Entity player;
    private Label playerInfo = new Label();
    private Items[] shop;

    public StoreScene(MapScene map){
        Navigator nav = Navigator.getInstance();
        makeShopKeeper();
        this.map = map;
        this.player = nav.getPlayer();
        exit.setOnAction(e -> exit());
        stack.setOnAction(e -> {
            ItemFactory itemFactory = new ItemFactory();
            Items[] playerItems = itemFactory.Stacker(player.getInventory().toArray(new Items[0]));
            player.setInventory(playerItems);
            makeButtons();
        });

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);
        options.getChildren().add(exit);
        options.getChildren().add(stack);

        playerInfo.setFont(new Font("System Regular", 25));
        playerInfo.setText("Your gold: "+player.getGold());

        layout.setTop(playerInfo);
        layout.setBottom(options);
    }

    public void makeShopKeeper(){
        ItemFactory itemFactory = new ItemFactory();
        shop = itemFactory.makeRandomItems(3,3,3);
        shop = itemFactory.noDup(shop);
    }

    public void show(){
        playerReset();
        makeButtons();
        makeShopButtons();
        scene.setRoot(layout);
    }

    public void makeButtons(){
        yourItems = new VBox();
        yourItems.setAlignment(Pos.CENTER);
        yourItems.setSpacing(10);
        yourItems.setStyle("-fx-background-color: rgb(100,100,100)");
        ArrayList<Items> stuff = player.getInventory();
        boolean currentWeapon = true;
        boolean currentArmor = true;

        Label label = new Label(" Your items: ");
        label.setFont(new Font("System Regular", 20));
        label.setStyle("-fx-background-color: rgb(255,255,255);");
        yourItems.getChildren().add(label);

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
                    player.setGold(player.getGold() + i.getValue());
                    player.removeItem(i);
                    makeButtons();
                    playerReset();
                });
            } else if(i.getType().equals("Armor")){
                b.setStyle("-fx-background-color: rgb(100,100,175)");
                b.setOnAction(e -> {
                    player.setGold(player.getGold() + i.getValue());
                    player.removeItem(i);
                    makeButtons();
                    playerReset();
                });
            } else {
                b.setStyle("-fx-background-color: rgb(150,190,200)");
                Consumable con = (Consumable) i;
                b.setOnAction(e -> {
                    player.setGold(player.getGold() + i.getValue());
                    player.removeConsumable(con);
                    playerReset();
                    makeButtons();
                });
            }

            buttonEffects(b);
            yourItems.getChildren().add(b);
        }
        setBoth();
    }

    public void makeShopButtons(){
        hisItems = new VBox();
        hisItems.setAlignment(Pos.CENTER);
        hisItems.setSpacing(10);
        hisItems.setStyle("-fx-background-color: rgb(100,100,100)");
        ArrayList<Items> stuff = new ArrayList<Items>(Arrays.asList(shop));

        Label label = new Label(" ShopKeeper items: ");
        label.setFont(new Font("System Regular", 20));
        label.setStyle("-fx-background-color: rgb(255,255,255);");
        hisItems.getChildren().add(label);

        for(Items i: stuff){
            Button b = new Button(i.toString());
            b.setTooltip(new Tooltip(i.getDescription()));
            if(i.getType().equals("Weapon")){
                b.setStyle("-fx-background-color: rgb(175,150,120)");
                b.setOnAction(e -> buy(i));
            } else if(i.getType().equals("Armor")){
                b.setStyle("-fx-background-color: rgb(100,100,175)");
                b.setOnAction(e -> buy(i));
            } else {
                b.setStyle("-fx-background-color: rgb(150,190,200)");
                //Consumable con = (Consumable) i;
                //con.setAmount(1);
                b.setOnAction(e -> buy(i));
            }

            buttonEffects(b);
            hisItems.getChildren().add(b);
        }
        setBoth();
    }

    public void buy(Items i){
        if(player.getGold() - i.getValue() > 0){
            System.out.println("You bought: \n"+i.toString());
            System.out.println();
            player.addItem(i);
            player.setGold(player.getGold() - i.getValue());
            makeButtons();
            makeShopButtons();
            playerReset();
        }

    }

    public void setBoth(){
        both = new HBox();
        both.setStyle("-fx-background-color: rgb(100,100,100)");
        both.getChildren().add(yourItems);
        both.getChildren().add(hisItems);
        both.setAlignment(Pos.CENTER);
        both.setSpacing(100);
        layout.setCenter(both);
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
        playerInfo.setText("Your gold: "+player.getGold());
    }

    public void exit(){
        map.setScene();
    }

}
