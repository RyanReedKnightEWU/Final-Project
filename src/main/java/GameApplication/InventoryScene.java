package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Factories.ItemFactory;
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
import java.util.Arrays;

import static FinalProject.Javafx.ApplicationMain.scene;

/**
 * Allows the player to equip items or use items.
 */
public class InventoryScene {
    private VBox items;
    private HBox options = new HBox();
    private BorderPane layout = new BorderPane();
    private Button exit = new Button("Exit");
    private Button stack = new Button("Stack");
    private MapScene map;
    private Entity player;
    private Label playerInfo = new Label();
    ItemFactory itemFactory = new ItemFactory();

    /**
     * Sets up the player inventory scene.
     * @param map
     * @param player
     */
    public void start(MapScene map,Entity player){
        this.map = map;
        this.player = player;
        exit.setOnAction(e -> exit());
        stack.setOnAction(e -> {
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
        playerReset();

        makeButtons();

        layout.setTop(playerInfo);
        layout.setCenter(items);
        layout.setBottom(options);
        scene.setRoot(layout);

    }

    /**
     * Creates buttons for the player to be able to interact with their inventory.
     */
    public void makeButtons(){
        items = new VBox();
        items.setAlignment(Pos.CENTER);
        items.setSpacing(10);
        items.setStyle("-fx-background-color: rgb(80,40,10)");
        ArrayList<Items> stuff = player.getInventory();


        for(Items i : stuff) {
            System.out.println(i);
        }
        System.out.println("Contains NULL " + stuff.contains(null));


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
                    con.setAmount(con.getAmount()+1);
                    player.removeConsumable(con);
                    playerReset();
                    makeButtons();

                    if(player.getHealth() <=0) {
                        MainMenuScene.returnToMainMenu();
                    }

                });
            }

            buttonEffects(b);
            items.getChildren().add(b);
        }
        layout.setCenter(items);
    }

    /**
     * Turns the buttons green if the mouse os hovering over the button.
     * @param b the button that is getting the effect.
     */
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

    /**
     * Resets the player information at the top of the screen.
     */
    public void playerReset(){
        playerInfo.setText(player.toString()+", Gold: "+player.getGold());
    }

    /**
     * Exits back to the map scene.
     */
    public void exit(){
        map.setScene();
    }

}
