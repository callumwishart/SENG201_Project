package seng201.team0.models.Upgrade;

import seng201.team0.models.Towers.Tower;

public class SpeedUpgrade extends Upgrade{
    public SpeedUpgrade(String inputName, String inputDescription, double inputCost) {
        super("Speed Upgrade", "Increases the speed of your towers", 30);
    }

    @Override
    public void applyUpgrade(Tower tower, int playerPoints) {
        //Needs to be implemented
        // Don't forget to use UpgradeExists to check if it is already there!
    }
}
