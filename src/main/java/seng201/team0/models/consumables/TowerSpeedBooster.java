package seng201.team0.models.consumables;


import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.Tower;

public class TowerSpeedBooster extends Consumable {
    // This booster makes the tower production faster by some factor (maybe tied to difficulty?)

    public TowerSpeedBooster() {
        super("Tower Speed Boost", "This booster makes the tower production faster.", 100);
    }

    public void apply(Round round) {
        for (Tower tower : round.getTowers()) {
            tower.decreaseReloadSpeed();
        }
    }
    public boolean remove(Round round) {
        for (Tower tower : round.getTowers()) {
            tower.increaseReloadSpeed();
        }
        return true;
    }
}
