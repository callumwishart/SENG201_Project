package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.exceptions.UpgradeMaxException;
import seng201.team0.models.towers.Tower;

public class SpeedUpgrade extends Upgrade{
    public SpeedUpgrade() {
        super("Speed Upgrade", "Increases the reload speed of your towers", 150);
    }

    @Override
    public void apply(Tower tower, int playerPoints) throws UpgradeException, UpgradeMaxException {
        if (playerPoints < tower.getUpgradePointLimit()){
            throw new UpgradeException();
        }
        else{
            if (tower.getReloadSpeed() > 1){
                tower.addUpgrade(new SpeedUpgrade());
                tower.decreaseReloadSpeed();
            }
            else{
                throw new UpgradeMaxException();
            }
        }
    }
}
