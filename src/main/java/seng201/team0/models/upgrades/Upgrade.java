package seng201.team0.models.upgrades;

import seng201.team0.models.Item;
import seng201.team0.models.towers.Tower;

public abstract class Upgrade extends Item {
    int upgradeCount;
    public Upgrade(String inputName, String inputDescription, int inputCost) {
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

    public abstract void apply(Tower tower, int playerPoints);
}
