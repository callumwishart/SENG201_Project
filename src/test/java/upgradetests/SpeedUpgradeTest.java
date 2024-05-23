package upgradetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seng201.team0.exceptions.UpgradeException;
import seng201.team0.exceptions.UpgradeMaxException;
import seng201.team0.models.towers.Factory;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.MoneyUpgrade;
import seng201.team0.models.upgrades.SpeedUpgrade;

public class SpeedUpgradeTest {
    private SpeedUpgrade speedUpgrade;
    private Tower testTower;


    @BeforeEach
    void setup(){
        speedUpgrade = new SpeedUpgrade();
        testTower = new Factory();
    }

    @Test
    void testApply() throws UpgradeException, UpgradeMaxException {
        assertEquals(5, testTower.getReloadSpeed());
        speedUpgrade.apply(testTower, 400);
        assertEquals(4, testTower.getReloadSpeed());
        assertThrows(UpgradeException.class, () -> speedUpgrade.apply(testTower, 0));
        speedUpgrade.apply(testTower, 10000);
        speedUpgrade.apply(testTower, 10000);
        speedUpgrade.apply(testTower, 10000);
        assertThrows(UpgradeMaxException.class, () -> speedUpgrade.apply(testTower, 10000));
    }


}

