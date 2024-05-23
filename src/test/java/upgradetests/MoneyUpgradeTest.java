package upgradetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seng201.team32.exceptions.UpgradeException;
import seng201.team32.models.towers.Factory;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.MoneyUpgrade;

public class MoneyUpgradeTest {
    private MoneyUpgrade moneyUpgrade;
    private Tower testTower;


    @BeforeEach
    void setup(){
        moneyUpgrade = new MoneyUpgrade();
        testTower = new Factory();
    }

    @Test
    void testApply() throws UpgradeException {
        assertEquals(50, testTower.getResource().getResourceCoinValue());
        moneyUpgrade.apply(testTower, 400);
        assertEquals(52, testTower.getResource().getResourceCoinValue());
        assertThrows(UpgradeException.class, () -> moneyUpgrade.apply(testTower, 0));
    }


}
