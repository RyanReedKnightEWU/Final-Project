package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Factories.ItemFactory;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;
import gameobjects.Navigator.Navigator;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.lang.annotation.Target;
import java.util.ArrayList;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

/**
 * Allows the player and enemy to interact.
 */
public class AttackScene {
    private BorderPane layout;
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

    private Navigator nav;
    private int row, column;


    /**
     * Sets up the attack scene's layout.
     * @param map Allows the player to go back to map scene.
     * @param row Where the enemy is.
     * @param column Where the enemy is.
     */
    public AttackScene(MapScene map, Navigator nav,int row, int column){
        layout = new BorderPane();
        this.map = map;
        attack = new Button("Attack");
        consumables = new Button("Use Consumable");
        runAway = new Button("Run Away");
        this.nav = nav;
        this.row = row;
        this.column = column;

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

        damageInfo.setFont(new Font("System Regular", 25));

        layout.setBottom(options);
        info.getChildren().add(badGuyInfo);
        info.getChildren().add(damageInfo);
        info.getChildren().add(playerInfo);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(100);
        layout.setCenter(info);

        runAway.setOnAction(e -> useRunAway());
    }

    /**
     * Loads the player and enemy into the attack scene.
     * @param player The player.
     * @param badGuy The enemy.
     */
    public void start(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        badGuyInfo.setStyle("-fx-background-color: rgb(250,200,200);");
        playerInfo.setText(player.toString());
        playerInfo.setStyle("-fx-background-color: rgb(200,200,250);");

        attack.setOnAction(e -> attackBadGuy(player, badGuy));
        consumables.setOnAction(e -> useConsumables(player, badGuy));
        scene.setRoot(layout);
    }

    /**
     * Allows the attack inventory scene to switch back to the attack scene.
     */
    public void back(Player player, Entity badGuy){
        scene.setRoot(layout);
        reset(player, badGuy);
        if(!badGuy.isAlive()){
            loot(player, badGuy);
        }
    }

    /**
     * Resets the player and enemy's info on the screen, such as health.
     */
    private void reset(Player player, Entity badGuy){
        badGuyInfo.setText(badGuy.toString());
        playerInfo.setText(player.toString());
    }

    /**
     * When the player attacks the enemy it will check if the enemy is alive, if so, they will attack back.
     */
    private void attackBadGuy(Player player, Entity badGuy){
        String currentInfo = "";
        int damage = player.getDamage();
        badGuy.takeDamage(damage);
        reset(player, badGuy);
        consume = true;
        currentInfo += player.getName()+" did "+damage+" damage to "+badGuy.getName();

        if(badGuy.getHealth() <= 0){
            badGuy.setAlive(false);
            System.out.println("You looted the bad guy");
            loot(player, badGuy);

        }else {
            damage = badGuyTurn(player, badGuy);
            reset(player, badGuy);
            currentInfo += "\n"+badGuy.getName()+" did "+damage+" damage to "+player.getName();
        }

        if(player.getHealth() <=0) {
            playerIsDeadAlert();
            return;
        }
        damageInfo.setText(currentInfo);
    }

    /**
     * Inform the player of their death and return to main menu.
     * */
    public void playerIsDeadAlert() {
        String message = "Regrettably, you have died.";
        Alert alert = new Alert(Alert.AlertType.INFORMATION,message);
        alert.showAndWait();
        MainMenuScene.returnToMainMenu();
    }

    /**
     * Adds the enemies inventory to the players inventory. It also tells the player what they got.
     */
    private void loot(Player player, Entity badGuy) {
        Popup pop = new Popup();
        //Adds gold and items to players inventory.
        player.addGold(badGuy.getGold());
        String stuff = "You got:\nGold: "+badGuy.getGold()+"\n";
        for (Items i: badGuy.getInventory()) {
            if(!i.getName().equals(new BareHands().getName())){
                stuff += i.getName()+"\n";
                player.addItem(i);
            }
        }
        //Stacks items
        ItemFactory itemFactory = new ItemFactory();
        Items[] playerItems = itemFactory.Stacker(player.getInventory().toArray(new Items[0]));
        player.setInventory(playerItems);
        //Tells user what they got
        Alert alert = new Alert(Alert.AlertType.INFORMATION, stuff);
        alert.showAndWait();
        nav.forceMove(row,column);
        map.reset(nav);
        map.setScene();
    }

    /**
     * Allow the enemy to attack the player.
     * @return The damage the enemy did.
     */
    private int badGuyTurn(Player player, Entity badGuy){
        int damage = badGuy.getDamage();
        player.takeDamage(damage);
        return damage;
    }

    /**
     * Switches the scene to the attack scene inventory.
     */
    private void useConsumables(Player player, Entity badGuy){
        AttackSceneInventory attackSceneInventory = new AttackSceneInventory();
        attackSceneInventory.start(this, player, badGuy);
    }

    /**
     * Goes back to the map scene if the player runs away.
     */
    private void useRunAway(){
        map.setScene();
    }

}

/**
 * A modified inventory scene for the attack scene. Allows the player to pick a target and allows them to use a consumable on the target.
 */
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

    /**
     * Creates the modified inventory scene.
     * @param back The attack scene is passed in so the player can go back to it when they are done here.
     */
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

    /**
     * Makes the buttons of the player consumables so that they can use them for the fight.
     */
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
                    con.setAmount(con.getAmount()+1);
                    player.removeConsumable(con);
                    playerReset();
                    makeButtons();
                    used = true;

                    if (player.getHealth() <= 0) {
                        attackScene.playerIsDeadAlert();
                    }else {
                        exit();
                    }
                });
                items.getChildren().add(b);
            }
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
        playerInfo.setText(player.toString());
    }

    /**
     * Exits back to the attack scene.
     */
    public void exit(){
        attackScene.back(player, badGuy);
    }
}
