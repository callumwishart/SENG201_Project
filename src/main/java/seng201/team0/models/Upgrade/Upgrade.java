package seng201.team0.models.Upgrade;

import seng201.team0.models.Item;
import seng201.team0.models.Tower;

public abstract class Upgrade extends Item {
    int upgradeCount;
    public Upgrade(String inputName, String inputDescription, double inputCost) {
        super(inputName, inputDescription, inputCost);
    }
    public void increaseCount() {
        upgradeCount += 1;
    }
    public static Boolean upgradeExists(Tower tower, Class<? extends Upgrade> upgradeType) {
        for (Upgrade upgrade : tower.getUpgrades()) {
            if (upgrade.getClass().equals(upgradeType)) {
                return true;
            }
        }
        return false;
    }

    public abstract void applyUpgrade(Tower tower, int playerPoints);
}
