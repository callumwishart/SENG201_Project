package upgradetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.exceptions.UpgradeException;
import seng201.team0.models.towers.Factory;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.CapacityUpgrade;
import static org.junit.jupiter.api.Assertions.*;

public class CapacityUpgradeTest {

    private CapacityUpgrade capacityUpgrade;
    private Tower testTower;

    @BeforeEach
    void setup(){
        capacityUpgrade = new CapacityUpgrade();
        testTower = new Factory();
    }

    @Test
    void testApply() throws UpgradeException {
        assertEquals(1, testTower.getResourceAmount());
        capacityUpgrade.apply(testTower, 10000);
        assertEquals(2, testTower.getResourceAmount());
        assertThrows(UpgradeException.class, () -> capacityUpgrade.apply(testTower, 0));
    }

}
