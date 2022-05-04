package Main;

import Entity.Entity;
import Entity.Goblin;
import Navigator.Navigator;
import Tile.TileBase;

public class Main {

    public static void main(String[] args) {

        Entity entity;
        entity = new Goblin("Grimble");
        System.out.println(entity);
        entity.basicAttack();
        entity.takeDamage(15);
        entity.takeDamage(0);
        entity.takeDamage(70);

        TileBase a = new Store("a"), b = new Store("b"), c = new Store("c");

        b.addEntity(entity);

        a.addLinkedTile(new TileBase[]{b, c});

        System.out.println(a.getLinkedTileKeys());

        Navigator nav = Navigator.getInstance(entity,a);
        nav.startTurn();
        System.out.println(nav.getCurrentTile().getLabel());
    }

}
