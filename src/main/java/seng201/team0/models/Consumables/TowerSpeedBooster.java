package seng201.team0.models.Consumables;


import seng201.team0.models.Towers.Tower;

import static seng201.team0.models.PlayerInventory.getActiveTowers;


public class TowerSpeedBooster extends Booster{
    // This booster makes the tower production faster by some factor (maybe tied to difficulty?)

    public TowerSpeedBooster(String inputName, String inputDescription, double inputCost) {
        super("Tower Speed Boost", "This booster makes the tower production faster.", 20);
    }

    public void apply() {
        for (Tower tower : getActiveTowers()) {
            tower.increaseReloadSpeed(2);
        }
    }
    public void remove() {
        for (Tower tower : getActiveTowers()) {
            tower.increaseReloadSpeed(2);
        }
    }
}
