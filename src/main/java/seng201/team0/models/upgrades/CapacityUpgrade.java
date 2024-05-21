package seng201.team0.models.upgrades;

import seng201.team0.models.towers.Tower;

public class CapacityUpgrade extends Upgrade{
    public CapacityUpgrade() {
        super("Increase Capacity", "Increases the amount of resources a tower can unload at once", 40);
    }

    @Override
    public void apply(Tower tower, int playerPoints) {
        // Needs to implemented
        // Don't forget to use UpgradeExists to check if it is already there!
        if (upgradeExists(tower, CapacityUpgrade.class)) {
            increaseCount();
        }
        tower.increaseResourceAmount(1);
    }
}
