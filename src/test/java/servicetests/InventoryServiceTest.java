package servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.towers.Factory;
import seng201.team0.models.towers.Farm;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.towers.WaterTower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private PlayerInventory inventory;
    private InventoryService inventoryService;

    @BeforeEach
    public void setUp() {
        inventory = new PlayerInventory();
        inventoryService = new InventoryService(inventory);
    }

    @Test
    public void testAddCoins() throws NegativeAdditionException {
        inventoryService.addCoins(100);
        assertEquals(100, inventoryService.getCoins());
    }

    @Test
    public void testAddCoinsNegativeAmount() throws NegativeAdditionException {
        assertThrows(NegativeAdditionException.class, () -> inventoryService.addCoins(-50));
    }

    @Test
    public void testAddPoints() throws NegativeAdditionException {
        inventoryService.addPoints(100);
        assertEquals(100, inventoryService.getPoints());
    }

    @Test
    public void testAddPointsNegativeAmount() {
        assertThrows(NegativeAdditionException.class, () -> inventoryService.addPoints(-100));
    }

    @Test
    public void testGetPoints() {
        assertEquals(0, inventoryService.getPoints());
    }

    @Test
    public void testGetCoins() {
        assertEquals(0, inventoryService.getCoins());
    }

    @Test
    public void testGetActiveTowers() throws TowerInventoryFullException {
        Tower testTower = new WaterTower();
        ArrayList<Tower> activeTowers = new ArrayList<>();
        assertEquals(activeTowers, inventoryService.getActiveTowers());
        activeTowers.add(testTower);
        inventoryService.addActiveTower(testTower);
        assertEquals(activeTowers, inventoryService.getActiveTowers());
    }

    @Test
    public void testGetStockpiledTowers() {
        List<Tower> stockpiledTowers = new ArrayList<>();
        assertEquals(stockpiledTowers, inventoryService.getStockpiledTowers());
    }

    @Test
    public void testAddActiveTower() throws TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        assertTrue(inventoryService.getActiveTowers().contains(tower));
    }

    @Test
    public void testAddTooManyActiveTower() {
        Tower tower = new Farm();
        assertThrows(TowerInventoryFullException.class, () -> {
           for (int i = 0; i < 6; i++){
               inventoryService.addActiveTower(tower);
           }
        });
    }

    @Test
    public void testRemoveActiveTower() throws TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        inventoryService.removeActiveTower(tower);
        assertFalse(inventoryService.getActiveTowers().contains(tower));
    }

    @Test
    public void testSwapTowers() throws TowerInventoryFullException {
        Tower activeTower = new Farm();
        Tower stockpiledTower = new Factory();
        inventoryService.addActiveTower(activeTower);
        inventoryService.addStockpiledTower(stockpiledTower);
        inventoryService.swapTowers(activeTower, stockpiledTower);
        assertTrue(inventoryService.getActiveTowers().contains(stockpiledTower));
        assertTrue(inventoryService.getStockpiledTowers().contains(activeTower));
    }

    @Test
    public void testSwapSingleTower() throws TowerInventoryFullException {
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
    public void testSellTower() throws NegativeAdditionException, TowerNotFoundException, TowerInventoryFullException {
        Tower tower = new Farm();
        inventoryService.addActiveTower(tower);
        inventoryService.sellTower(tower);
        assertFalse(inventoryService.getActiveTowers().contains(tower));
        assertEquals(tower.getSellCost(), inventoryService.getCoins()); // check balance against sell cost
    }

    @Test
    public void testRepairTower() throws Exception {
        Tower tower = new Farm();
        tower.setToBroken();
        assertTrue(tower.isBroken());
        inventoryService.addCoins(1000);
        inventoryService.repairTower(tower);
        assertFalse(tower.isBroken());
    }

    @Test
    public void testRepairTowerWithoutCoins(){
        Tower tower = new Farm();
        tower.setToBroken();
        assertTrue(tower.isBroken());
        assertEquals(0, inventoryService.getCoins());
        assertThrows(PurchaseException.class, () -> inventoryService.repairTower(tower));
        assertTrue(tower.isBroken());
    }

}
