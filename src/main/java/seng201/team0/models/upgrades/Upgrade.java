package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.models.Item;
import seng201.team0.models.towers.Tower;

/**
 * Upgrade class which extends Item and houses the upgrade constructor, increaseCount() and upgradeExists() and apply()
 * methods.
 */
public abstract class Upgrade extends Item {
    /**
     * How many times this upgrade has been applied on a tower
     */
    int upgradeCount;

    /**
     * Constructor of Upgrade class.
     * @param inputName Name of Upgrade
     * @param inputDescription Description of Upgrade
     * @param inputCost Cost of Upgrade
     */
    public Upgrade(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }

    /**
     * Increases the upgradeCount variable
     */
    public void increaseCount() {
        upgradeCount += 1;
    }

    /**
     * Constructor of apply upgrade method
     * @param tower Tower that the upgrade should be applied to
     * @param playerPoints The players current points.
     */
    public abstract void apply(Tower tower, int playerPoints) throws UpgradeException;
}
