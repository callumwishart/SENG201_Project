package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.models.towers.Tower;

public class MoneyUpgrade extends Upgrade{
    public MoneyUpgrade() {
        super("Money Upgrade", "Increases the value of your tower's resource.", 150);
    }

    @Override
    public void apply(Tower tower, int playerPoints) throws UpgradeException {
        if (playerPoints < tower.getUpgradePointLimit()){
            throw new UpgradeException();
        }
        else{
            tower.addUpgrade(new MoneyUpgrade());
            tower.getResource().increaseCoinValue(2);
        }
    }

}
