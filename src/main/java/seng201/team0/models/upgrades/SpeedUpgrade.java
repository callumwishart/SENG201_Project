package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.models.towers.Tower;

public class SpeedUpgrade extends Upgrade{
    public SpeedUpgrade() {
        super("Speed Upgrade", "Increases the speed of your towers", 30);
    }

    @Override
    public void apply(Tower tower, int playerPoints) throws UpgradeException {
        if (playerPoints < tower.getUpgradePointLimit()){
            throw new UpgradeException();
        }
        else{
            tower.addUpgrade(new SpeedUpgrade());
            tower.increaseReloadSpeed(1);
        }
    }
}
