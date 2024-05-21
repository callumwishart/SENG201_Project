package gameplay;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.models.Difficulty;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.consumables.Shield;
import seng201.team0.models.consumables.SlowCartBooster;
import seng201.team0.models.consumables.TowerSpeedBooster;
import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.Farm;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;

public class RoundTester {
    private static PlayerInventory inventory;
    private static InventoryService inventoryService;
    private static Difficulty difficulty;

    @BeforeAll
    static void setup() throws TowerInventoryFullException {
        RoundTester.inventory = new PlayerInventory();
        RoundTester.inventoryService = new InventoryService(inventory);
        inventoryService.addActiveTower(new Farm());
        RoundTester.difficulty = new Difficulty(); // basic difficulty multipliers set to 1
    }

    @Test
    void testBasicRoundInitialisation(){
        Round round = new Round(inventoryService, difficulty, 1);
        assertEquals(1, round.getRoundNum());
        assertEquals(1, round.getCarts().size());
    }

    @Test
    void testCartsSortedDescending(){
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
    void testAllConsumablesApply(int roundNum){

        inventoryService.addConsumable(new TowerSpeedBooster());
        inventoryService.addConsumable(new SlowCartBooster());
        inventoryService.addConsumable(new Shield());

        Round round = new Round(inventoryService, difficulty, roundNum);

        ArrayList<Integer> towerReloadSpeeds = new ArrayList<>();
        for (Tower tower : inventoryService.getActiveTowers()){
            towerReloadSpeeds.add(tower.getReloadSpeed());
        }

        ArrayList<Integer> cartSpeeds = new ArrayList<>();
        for (Cart cart : round.getCarts()){
            cartSpeeds.add(cart.getSpeed());
        }

        assertFalse(round.hasShield());

        round.applyConsumables();

        for (int i = 0; i < inventoryService.getActiveTowers().size(); i++){
            System.out.println(String.format("Tower reload speed before: %d Tower reload speed after: %d", towerReloadSpeeds.get(i), inventoryService.getActiveTowers().get(i).getReloadSpeed()));
            assertTrue(towerReloadSpeeds.get(i) < inventoryService.getActiveTowers().get(i).getReloadSpeed());
        }

        for (int i = 0; i < round.getCarts().size(); i++){
            System.out.println(String.format("Cart speed before: %d Cart speed after: %d", cartSpeeds.get(i), round.getCarts().get(i).getSpeed()));
            assertTrue(cartSpeeds.get(i) >= round.getCarts().get(i).getSpeed());
        }

        assertTrue(round.hasShield());

        round.cleanup();

    }

}
