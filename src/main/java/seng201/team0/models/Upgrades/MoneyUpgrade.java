package seng201.team0.models.Upgrades;

import seng201.team0.models.Towers.Tower;

public class MoneyUpgrade extends Upgrade{
    public MoneyUpgrade(String inputName, String inputDescription, double inputCost) {
        super("Money Upgrade", "Increases the value of your resource.", 50);
    }

    @Override
    public void applyUpgrade(Tower tower, int playerPoints) {
        // Needs to implemented
        // Don't forget to use UpgradeExists to check if it is already there!
        if (upgradeExists(tower, MoneyUpgrade.class)) {
            increaseCount();
        }
        tower.getResourceType().increaseCoinValue(1);
    }

}
