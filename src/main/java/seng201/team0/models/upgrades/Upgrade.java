package seng201.team0.models.upgrades;

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
     * Checks to see if the given tower already has the upgrade applied to it.
     * @param tower Tower the upgrade should be checked with
     * @param upgradeType Type of the upgrade you want to check for
     * @return Returns boolean of true if the upgrade exists and false otherwise.
     */
    public static Boolean upgradeExists(Tower tower, Class<? extends Upgrade> upgradeType) {
        for (Upgrade upgrade : tower.getUpgrades()) {
            if (upgrade.getClass().equals(upgradeType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Constructor of apply upgrade method
     * @param tower Tower that the upgrade should be applied to
     * @param playerPoints The players current points.
     */

    public abstract void apply(Tower tower, int playerPoints);
}
