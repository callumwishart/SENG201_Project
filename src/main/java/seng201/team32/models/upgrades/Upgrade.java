package seng201.team32.models.upgrades;

import seng201.team32.exceptions.UpgradeException;
import seng201.team32.exceptions.UpgradeMaxException;
import seng201.team32.models.Item;
import seng201.team32.models.towers.Tower;

/**
 * Upgrade class which extends Item and houses the upgrade constructor, increaseCount() and upgradeExists() and apply()
 * methods.
 */
public abstract class Upgrade extends Item {
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
     * Constructor of apply upgrade method
     * @param tower Tower that the upgrade should be applied to
     * @param playerPoints The players current points.
     * @throws UpgradeException is thrown if the player doesn't have enough points to upgrade the tower
     * @throws UpgradeMaxException is thrown if the upgrade is already at its max level
     */
    public abstract void apply(Tower tower, int playerPoints) throws UpgradeException, UpgradeMaxException;
}
