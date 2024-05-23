package seng201.team32.models.consumables;


import seng201.team32.models.gameplay.Round;
import seng201.team32.models.towers.Tower;

import java.util.ArrayList;

/**
 * Tower Speed booster which will boost the speed of all towers for the round
 */
public class TowerSpeedBooster extends Consumable {
    /**
     * list of towers that will be affected
     */
    private ArrayList<Tower> towersAffected = new ArrayList<>();

    /**
     * Constructor which sets the name and description and cost of the booster
     */
    public TowerSpeedBooster() {
        super("Tower Speed Boost", "This booster makes the tower production faster.", 350);
    }

    /**
     * Apply the booster to the towers
     * <p>
     *     This method will go through all the towers and for all towers that have a
     *     reload speed greater than one it will decrease reload speed which results in towers
     *     reloading faster
     * </p>
     * @param round the round that this is applied to
     */
    public void apply(Round round) {
        for (Tower tower : round.getTowers()) {
            if (tower.getReloadSpeed() > 1){
                tower.decreaseReloadSpeed();
                towersAffected.add(tower);
            }
        }
    }

    /**
     * Will remove the affect of the booster at the end of the round
     * <p>
     *     Gets all the towers in the towers affected and will increase their reload speed
     * </p>
     * @param round the round that this will be applied to
     * @return boolean to show that this has occurred
     */
    public boolean remove(Round round) {
        for (Tower tower : towersAffected) {
            tower.increaseReloadSpeed();
        }
        return true;
    }
}
