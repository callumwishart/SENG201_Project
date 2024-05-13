package seng201.team0.models.Upgrade;

import seng201.team0.models.Towers.Tower;

public class Capacity extends Upgrade{
    public Capacity(String inputName, String inputDescription, double inputCost) {
        super("Increase Capacity", "Increases the amount of resources a tower can unload at once", 40);
    }

    @Override
    public void applyUpgrade(Tower tower, int playerPoints) {
        // Needs to implemented
        // Don't forget to use UpgradeExists to check if it is already there!
    }
}
