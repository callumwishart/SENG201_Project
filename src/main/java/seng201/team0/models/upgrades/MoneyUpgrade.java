package seng201.team0.models.upgrades;

import seng201.team0.exceptions.UpgradeException;
import seng201.team0.models.towers.Tower;

/**
 * MoneyUpgrade class which extends upgrade and increases the value of your resource for that tower
 */
public class MoneyUpgrade extends Upgrade{
    /**
     * MoneyUpgradeConstructor which initialises the name, description, and cost
     */
    public MoneyUpgrade() {
        super("Money Upgrade", "Increases the value of your tower's resource.", 150);
    }

    /**
     * Applies the upgrade to the tower
     * <p>
     *     Checks if the player has enough points to use the upgrade, if not a {@code UpgradeException is thrown}.
     *     It then increases the coin value of the resource of the tower by 10
     * </p>
     * @param tower Tower that the upgrade should be applied to
     * @param playerPoints The players current points.
     * @throws UpgradeException is thrown if the player does not have enough points
     */
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
