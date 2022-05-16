package gameobjects.Navigator;

import gameobjects.Entity.Entity;

// Just something I am using for the time being
public abstract class Attack {

    public static boolean attack(Entity offender, Entity defender) {

        // Assault defender
        defender.takeDamage(offender.getDamage());

        // Check if defender survives.
        if (defender.getHealth() > 0) {

            // Defender fights back.
            offender.takeDamage(defender.getDefense());

            if (defender.isAlive()) {
                return attack(offender, defender);
            }

            return offender.isAlive();
        }
        else {
            return true;
        }

    }

}
