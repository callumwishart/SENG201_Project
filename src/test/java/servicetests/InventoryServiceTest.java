package servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.exceptions.*;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.towers.Factory;
import seng201.team0.models.towers.Farm;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.towers.WaterTower;
import seng201.team0.models.upgrades.SpeedUpgrade;
import seng201.team0.models.upgrades.Upgrade;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private PlayerInventory inventory;
    private InventoryService inventoryService;

    @BeforeEach
     void setUp() {
        inventory = new PlayerInventory();
        inventoryService = new InventoryService(inventory);
    }

    @Test
     void testAddCoins() throws NegativeAdditionException {
        inventoryService.addCoins(100);
        assertEquals(100, inventoryService.getCoins());
    }

    @Test
     void testAddCoinsNegativeAmount() throws NegativeAdditionException {
        assertThrows(NegativeAdditionException.class, () -> inventoryService.addCoins(-50));
    }

    @Test
     void testAddPoints() throws NegativeAdditionException {
        inventoryService.addPoints(100);
        assertEquals(100, inventoryService.getPoints());
    }

    @Test
     void testAddPointsNegativeAmount() {
        assertThrows(NegativeAdditionException.class, () -> inventoryService.addPoints(-100));
    }

    @Test
     void testGetPoints() {
        assertEquals(0, inventoryService.getPoints());
    }

    @Test
     void testGetCoins() {
        assertEquals(0, inventoryService.getCoins());
    }

    @Test
     void testGetActiveTowers() throws TowerInventoryFullException {
        Tower testTower = new WaterTower();
        ArrayList<Tower> activeTowers = new ArrayList<>();
        assertEquals(activeTowers, inventoryService.getActiveTowers());
        activeTowers.add(testTower);
        inventoryService.addActiveTower(testTower);
        assertEquals(activeTowers, inventoryService.getActiveTowers());
    }

    @Test
     void testGetStockpiledTowers() {
        List<Tower> stockpiledTowers = new ArrayList<>();
        assertEquals(stockpiledTowers, inventoryService.getStockpiledTowers());
    }

    @Test
     void testAddActiveTower() throws TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        assertTrue(inventoryService.getActiveTowers().contains(tower));
    }

    @Test
     void testAddTooManyActiveTower() {
        Tower tower = new Farm();
        assertThrows(TowerInventoryFullException.class, () -> {
           for (int i = 0; i < 6; i++){
               inventoryService.addActiveTower(tower);
           }
        });
    }

    @Test
     void testRemoveActiveTower() throws TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        inventoryService.removeActiveTower(tower);
        assertFalse(inventoryService.getActiveTowers().contains(tower));
    }

    @Test
     void testSwapTowers() throws TowerInventoryFullException {
        Tower activeTower = new Farm();
        Tower stockpiledTower = new Factory();
        inventoryService.addActiveTower(activeTower);
        inventoryService.addStockpiledTower(stockpiledTower);
        inventoryService.swapTowers(activeTower, stockpiledTower);
        assertTrue(inventoryService.getActiveTowers().contains(stockpiledTower));
        assertTrue(inventoryService.getStockpiledTowers().contains(activeTower));
    }

    @Test
     void testSwapSingleTower() throws TowerInventoryFullException {
        Tower testTower = new Farm();
        inventoryService.addActiveTower(testTower);
        inventoryService.swapTowers(testTower, true);
        assertTrue(inventoryService.getStockpiledTowers().contains(testTower));
        inventoryService.swapTowers(testTower, false);
        assertTrue(inventoryService.getActiveTowers().contains(testTower));
        for (int i = 0; i < 5; i++){
            inventoryService.addStockpiledTower(new Farm());
        }
        assertThrows(TowerInventoryFullException.class, () -> inventoryService.swapTowers(testTower, true));
    }

    @Test
     void testSellTower() throws NegativeAdditionException, TowerNotFoundException, TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        inventoryService.sellTower(tower);
        assertFalse(inventoryService.getActiveTowers().contains(tower));
        assertEquals(tower.getSellCost(), inventoryService.getCoins()); // check balance against sell cost
    }

    @Test
     void testRepairTower() throws Exception {
        Tower tower = new Farm();
        tower.setToBroken();
        assertTrue(tower.isBroken());
        inventoryService.addCoins(1000);
        inventoryService.repairTower(tower);
        assertFalse(tower.isBroken());
    }

    @Test
     void testRepairTowerWithoutCoins(){
        Tower tower = new Farm();
        tower.setToBroken();
        assertTrue(tower.isBroken());
        assertEquals(0, inventoryService.getCoins());
        assertThrows(PurchaseException.class, () -> inventoryService.repairTower(tower));
        assertTrue(tower.isBroken());
    }

    @Test
    void testRemoveTower() throws TowerInventoryFullException, TowerNotFoundException {
        Tower testTower = new Factory();
        assertThrows(TowerNotFoundException.class, () -> inventoryService.removeTower(testTower));
        inventoryService.addActiveTower(testTower);
        inventoryService.removeTower(testTower);
        assertFalse(inventoryService.getActiveTowers().contains(testTower));
        inventoryService.addStockpiledTower(testTower);
        inventoryService.removeTower(testTower);
        assertFalse(inventoryService.getStockpiledTowers().contains(testTower));
    }

    @Test
    void testApplyUpgrade() throws TowerInventoryFullException, UpgradeException, UpgradeMaxException {
        Tower testTower = new Factory();
        Upgrade speedUpgrade = new SpeedUpgrade();
        assertEquals(5, testTower.getReloadSpeed());
        inventoryService.addActiveTower(testTower);
        inventoryService.addUpgrade(speedUpgrade);
        assertTrue(inventoryService.getUpgrades().contains(speedUpgrade));
        inventoryService.applyUpgrade(testTower, speedUpgrade);
        assertFalse(inventoryService.getUpgrades().contains(speedUpgrade));
        assertEquals(4, testTower.getReloadSpeed());
        assertInstanceOf(speedUpgrade.getClass(), testTower.getUpgrades().get(0));
    }

}
