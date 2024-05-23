package gameplaytests;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import seng201.team32.exceptions.TowerInventoryFullException;
import seng201.team32.models.Difficulty;
import seng201.team32.models.PlayerInventory;
import seng201.team32.models.consumables.Shield;
import seng201.team32.models.consumables.SlowCartConsumable;
import seng201.team32.models.consumables.TowerSpeedBooster;
import seng201.team32.models.gameplay.Cart;
import seng201.team32.models.gameplay.Round;
import seng201.team32.models.towers.Farm;
import seng201.team32.models.towers.Tower;
import seng201.team32.services.InventoryService;

import java.util.ArrayList;

public class RoundTest {
    private static PlayerInventory inventory;
    private static InventoryService inventoryService;
    private static Difficulty difficulty;

    @BeforeAll
    static void setup() throws TowerInventoryFullException {
        RoundTest.inventory = new PlayerInventory();
        RoundTest.inventoryService = new InventoryService(inventory);
        inventoryService.addActiveTower(new Farm());
        RoundTest.difficulty = new Difficulty(); // basic difficulty multipliers set to 1
    }

    @Test
    void testBasicRoundInitialisation() throws CloneNotSupportedException {
        Round round = new Round(inventoryService, difficulty, 1);
        assertEquals(1, round.getRoundNum());
        assertEquals(1, round.getCarts().size());
    }

    @Test
    void testCartsSortedDescending() throws CloneNotSupportedException {
        Round round = new Round(inventoryService, difficulty, 10);
        ArrayList<Cart> carts = round.getCarts();
        for (int i = 0; i < carts.size(); i++){
            if (i > 0){
                Cart previous = carts.get(i - 1);
                Cart current = carts.get(i);
                assertTrue(previous.getSpeed() >= current.getSpeed());
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15})
    void testAllConsumablesApply(int roundNum) throws CloneNotSupportedException {
        inventoryService.addConsumable(new TowerSpeedBooster());
        inventoryService.addConsumable(new SlowCartConsumable());
        inventoryService.addConsumable(new Shield());

        Round round = new Round(inventoryService, difficulty, roundNum);
        ArrayList<Cart> testCarts = round.getCarts();
        ArrayList<Tower> testTowers = inventoryService.getActiveTowers();
        ArrayList<Integer> towerReloadSpeeds = new ArrayList<>();

        for (Tower tower : testTowers){
            towerReloadSpeeds.add(tower.getReloadSpeed());
        }

        ArrayList<Integer> cartSpeeds = new ArrayList<>();
        for (Cart cart : testCarts){
            cartSpeeds.add(cart.getSpeed());
        }

        assertFalse(round.hasShield());

        round.applyConsumables();

        for (int i = 0; i < testTowers.size(); i++) {
            if (towerReloadSpeeds.get(i) == 1) {
                assertEquals(towerReloadSpeeds.get(i), testTowers.get(i).getReloadSpeed());
            }
            else {
                assertTrue(towerReloadSpeeds.get(i) > testTowers.get(i).getReloadSpeed());
            }
        }

        for (int i = 0; i < round.getCarts().size(); i++){
            assertTrue(cartSpeeds.get(i) >= round.getCarts().get(i).getSpeed());
        }
        assertTrue(round.hasShield());

        round.cleanup();

        for (int i = 0; i < testTowers.size(); i++) {
            assertEquals((int) towerReloadSpeeds.get(i), testTowers.get(i).getReloadSpeed());
        }
    }

}
