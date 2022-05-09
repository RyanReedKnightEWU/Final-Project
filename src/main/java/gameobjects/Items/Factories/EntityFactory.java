package gameobjects.Items.Factories;

import gameobjects.Entity.Entity;

public abstract class EntityFactory {

    public abstract Entity createEntity(String entityName);

}
