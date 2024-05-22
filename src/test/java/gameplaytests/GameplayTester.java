package gameplaytests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.models.Difficulty;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.gameplay.GameRunner;
import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.Farm;
import seng201.team0.services.InventoryService;

public class GameplayTester {

    private static InventoryService inventoryService;
    private static Difficulty difficulty;
    private static int roundNum;
    private static Round round;

    @BeforeAll
    static void setupRound() throws TowerInventoryFullException, CloneNotSupportedException {
        GameplayTester.inventoryService = new InventoryService(new PlayerInventory());
        inventoryService.addActiveTower(new Farm());
        GameplayTester.difficulty = new Difficulty();
        GameplayTester.roundNum = 3;
        GameplayTester.round = new Round(inventoryService, difficulty, roundNum);
    }

    @Test
    void testGameRunnerWithObserver() throws InterruptedException {
        TestGameObserver observer = new TestGameObserver();
        GameRunner gameRunner = new GameRunner(round, observer, false);
        gameRunner.run();
        boolean gameSuccess = gameRunner.getGameSuccess();
        assertTrue(gameSuccess);
    }

}
