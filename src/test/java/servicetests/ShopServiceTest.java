package servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import seng201.team32.exceptions.*;
import seng201.team32.models.Difficulty;
import seng201.team32.models.PlayerInventory;
import seng201.team32.models.Shop;
import seng201.team32.models.consumables.Consumable;
import seng201.team32.models.consumables.Shield;
import seng201.team32.models.consumables.SlowCartConsumable;
import seng201.team32.models.consumables.TowerSpeedBooster;
import seng201.team32.models.towers.Factory;
import seng201.team32.models.towers.Farm;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.CapacityUpgrade;
import seng201.team32.models.upgrades.MoneyUpgrade;
import seng201.team32.models.upgrades.SpeedUpgrade;
import seng201.team32.models.upgrades.Upgrade;
import seng201.team32.services.InventoryService;
import seng201.team32.services.ShopService;


public class ShopServiceTest {

    private static Shop shop;
    private static ShopService shopService;
    private static PlayerInventory inventory;
    private static InventoryService inventoryService;
    private static Difficulty difficulty;

    @BeforeEach
    void setup(){
        ShopServiceTest.shop = new Shop()
        ;
        ShopServiceTest.inventory = new PlayerInventory();
        ShopServiceTest.inventoryService = new InventoryService(inventory);
        ShopServiceTest.shopService = new ShopService(shop, inventoryService);
        ShopServiceTest.difficulty = new Difficulty();
        shopService.setDifficulty(difficulty);
    }

    @Test
    void testPurchaseTowers() throws NegativeAdditionException, PurchaseException, TowerInventoryFullException {
        // try purchasing 5 towers for activeTowers
        for (int i = 0; i < 5; i++){
            Tower testTower = new Factory();
            inventoryService.addCoins(testTower.getCost());
            shopService.purchaseTower(testTower);
            assertEquals(0, inventoryService.getCoins());
            assertTrue(inventoryService.getActiveTowers().contains(testTower));
        }
        // try purchasing 5 more towers for stockpile
        for (int i = 0; i < 5; i++){
            Tower testTower = new Farm();
            inventoryService.addCoins(testTower.getCost());
            shopService.purchaseTower(testTower);
            assertEquals(0, inventoryService.getCoins());
            assertTrue(inventoryService.getStockpiledTowers().contains(testTower));
        }

    }

    @Test
    void testPurchaseTowerFullInventory() throws NegativeAdditionException, PurchaseException, TowerInventoryFullException {

        // try purchasing 5 towers for activeTowers
        for (int i = 0; i < 10; i++){
            Tower testTower = new Factory();
            inventoryService.addCoins(testTower.getCost());
            shopService.purchaseTower(testTower);
        }

        assertThrows(TowerInventoryFullException.class, () -> inventoryService.addActiveTower(new Factory()));

    }

    @Test
    void testPurchaseUpgrade() throws PurchaseException, UpgradesFullException, NegativeAdditionException {

        Upgrade capacityUpgrade = new CapacityUpgrade();
        Upgrade speedUpgrade = new SpeedUpgrade();
        Upgrade moneyUpgrade = new MoneyUpgrade();

        inventoryService.addCoins(capacityUpgrade.getCost());
        inventoryService.addCoins(speedUpgrade.getCost());
        inventoryService.addCoins(moneyUpgrade.getCost());

        shopService.purchaseUpgrade(capacityUpgrade);
        assertTrue(inventoryService.getUpgrades().contains(capacityUpgrade));
        shopService.purchaseUpgrade(speedUpgrade);
        assertTrue(inventoryService.getUpgrades().contains(speedUpgrade));
        shopService.purchaseUpgrade(moneyUpgrade);
        assertTrue(inventoryService.getUpgrades().contains(moneyUpgrade));
    }

    @Test
    void testPurchaseConsumable() throws PurchaseException, NegativeAdditionException, ActiveConsumableException {

        Consumable shield = new Shield();
        Consumable towerSpeed = new TowerSpeedBooster();
        Consumable cartSlow = new SlowCartConsumable();

        inventoryService.addCoins(shield.getCost());
        inventoryService.addCoins(towerSpeed.getCost());
        inventoryService.addCoins(cartSlow.getCost());

        int balance = inventoryService.getCoins();

        shopService.purchaseConsumable(shield);
        assertEquals(balance - shield.getCost(), inventoryService.getCoins());
        assertTrue(inventoryService.getConsumables().contains(shield));
        balance = inventoryService.getCoins();
        shopService.purchaseConsumable(towerSpeed);
        assertEquals(balance - towerSpeed.getCost(), inventoryService.getCoins());
        assertTrue(inventoryService.getConsumables().contains(towerSpeed));
        balance = inventoryService.getCoins();
        shopService.purchaseConsumable(cartSlow);
        assertEquals(balance - towerSpeed.getCost(), inventoryService.getCoins());
        assertTrue(inventoryService.getConsumables().contains(cartSlow));

    }

}
