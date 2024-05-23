package towertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seng201.team32.models.resources.Goods;
import seng201.team32.models.towers.Factory;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.MoneyUpgrade;
import seng201.team32.models.upgrades.Upgrade;

import java.util.ArrayList;

public class TowerTest {
    private Tower testTower;

    @BeforeEach
    public void setUp() {
        testTower = new Factory();
    }

    @Test
    public void testTowerConstructor() {
        assertEquals(1, testTower.getResourceAmount());
        assertFalse(testTower.isBroken());
        assertEquals(new Goods(), testTower.getResource());
        assertEquals(5, testTower.getReloadSpeed());
        assertEquals("Factory", testTower.getName());
        assertEquals(0, testTower.getLevel());
    }

    @Test
    public void testIncreaseResourceAmount() {
        testTower.increaseResourceAmount(5);
        assertEquals(6, testTower.getResourceAmount());
    }

    @Test
    public void testLevelUp() {
        testTower.levelUp();
        assertEquals(1, testTower.getLevel());
    }

    @Test
    public void testAddUpgrade() {
        Upgrade upgrade = new MoneyUpgrade();
        testTower.addUpgrade(upgrade);
        ArrayList<Upgrade> upgrades = testTower.getUpgrades();
        assertEquals(1, upgrades.size());
        assertTrue(testTower.getUpgrades().contains(upgrade));
        assertEquals(1, testTower.getLevel());
    }

    @Test
    public void testIncreaseReloadSpeed() {
        testTower.increaseReloadSpeed();
        assertEquals(6, testTower.getReloadSpeed());
    }

    @Test
    public void testDecreaseReloadSpeed() {
        testTower.decreaseReloadSpeed();
        assertEquals(4, testTower.getReloadSpeed());
    }

    @Test
    public void testReloading() {
        testTower.setReloading(true);
        assertTrue(testTower.isReloading());
        testTower.incrementReloadTimeElapsed();
        assertEquals(1, testTower.getReloadTimeElapsed());
        testTower.setReloading(false);
        assertFalse(testTower.isReloading());
        assertEquals(0, testTower.getReloadTimeElapsed());
    }

    @Test
    public void testBreakFix() {
        testTower.setToBroken();
        assertTrue(testTower.isBroken());
        testTower.setToFixed();
        assertFalse(testTower.isBroken());
    }

    @Test
    public void testSellCost() {
        assertEquals((int)(testTower.getCost() / 2), testTower.getSellCost());
        testTower.setToBroken();
        assertEquals(0, testTower.getSellCost());
    }

    @Test
    public void testUsed() {
        testTower.incrementUsed();
        assertEquals(1, testTower.getUsed());
        testTower.resetUsed();
        assertEquals(0, testTower.getUsed());
    }
}