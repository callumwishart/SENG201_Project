package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.exceptions.UpgradeMaxException;
import seng201.team0.models.towers.Tower;

/**
 * Speed upgrade class which increases the speed of your towers
 * <p>
 * Extends upgrade class and inherits its methods
 * </p>
 */
public class SpeedUpgrade extends Upgrade{
    /**
     * Constructor of Speed upgrade to set the name, description and cost
     */
    public SpeedUpgrade() {
        super("Speed Upgrade", "Increases the reload speed of your towers", 150);
    }

    /**
     * Applies upgrade
     * <p>
     *     Checks if the player has enough points to use the upgrade, if not a {@code UpgradeException is thrown}.
     *     It then checks that you haven't maxed out the number of upgrades and then add the upgrade to the tower and decreases reload
     *     speed. If upgrade amount is maxed then {@code UpgradeMaxException} is thrown
     * </p>
     * @param tower Tower that the upgrade should be applied to
     * @param playerPoints The players current points.
     * @throws UpgradeException is thrown if the player does not have enough points to upgrade
     * @throws UpgradeMaxException is thrown if the upgrade has already caused tower to reach its max
     */
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
