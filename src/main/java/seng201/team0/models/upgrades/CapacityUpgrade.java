package seng201.team0.models.upgrades;

import seng201.team0.models.towers.Tower;

/**
 * Upgrade to increase the amount of resources a tower can unload at once.
 * Can be purchased and applied from shop and inventory respectively
 */
public class CapacityUpgrade extends Upgrade{
    /**
     * Constructor of Capacity upgrade which creates a new instance with the inputName and inputDescription being
     * initialized for this.
     */
    public CapacityUpgrade() {
        super("Increase Capacity", "Increases the amount of resources a tower can unload at once", 40);
    }

    /**
     * Function is called when upgrade is applied to tower. This will increase the count of the upgrade being used and
     * will increase the resource amount param of the selected tower
     * @param tower tower passed as an instance such that its values can be updated
     * @param playerPoints gets the playersPoints
     */
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
