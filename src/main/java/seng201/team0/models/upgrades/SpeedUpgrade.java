package seng201.team0.models.upgrades;

import seng201.team0.models.towers.Tower;

public class SpeedUpgrade extends Upgrade{
    public SpeedUpgrade() {
        super("Speed Upgrade", "Increases the speed of your towers", 30);
    }

    @Override
    public void applyUpgrade(Tower tower, int playerPoints) {
        //Needs to be implemented
        // Don't forget to use UpgradeExists to check if it is already there!
        if (upgradeExists(tower, SpeedUpgrade.class)) {
            increaseCount();
        }
        tower.increaseReloadSpeed(1);
    }
}
