package seng201.team0.models.consumables;


import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

public class TowerSpeedBooster extends Consumable {
    // This booster makes the tower production faster by some factor (maybe tied to difficulty?)
    private ArrayList<Tower> towersAffected = new ArrayList<>();

    public TowerSpeedBooster() {
        super("Tower Speed Boost", "This booster makes the tower production faster.", 350);
    }

    public void apply(Round round) {
        for (Tower tower : round.getTowers()) {
            if (tower.getReloadSpeed() > 1){
                tower.decreaseReloadSpeed();
                towersAffected.add(tower);
            }
        }
    }
    public boolean remove(Round round) {
        for (Tower tower : towersAffected) {
            tower.increaseReloadSpeed();
        }
        return true;
    }
}
