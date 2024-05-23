package modeltests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team32.models.Difficulty;

public class DifficultyTest {
    private Difficulty difficulty;

    @BeforeEach
    public void setUp() {
        difficulty = new Difficulty(1.2, 1);
    }

    @Test
    public void testUpdateDifficultyRisky() {
        difficulty.updateDifficulty("Risky");
        assertEquals((1 * 1.33), difficulty.roundDifficultyMultiplier());
    }

    @Test
    public void testUpdateDifficultySafe() {
        difficulty.updateDifficulty("Safe");
        assertEquals(1 * 0.66, difficulty.roundDifficultyMultiplier());
    }

    @Test
    public void testIncrementCostMultiplier() {
        difficulty.incrementCostMultiplier();
        assertEquals(1.2 + 0.3, difficulty.getCostMultiplier());
    }

    @Test
    public void testReset() {
        difficulty.updateDifficulty("Risky");
        difficulty.reset();
        assertEquals(1, difficulty.roundDifficultyMultiplier());
    }

}

