package seng201.team32.models;

/**
 * This is used to determine how the difficulty affect the game
 */
public class Difficulty {
    /**
     * costMultiplier affects how much things cost in-game.
     */
    private double costMultiplier;

    /**
     * roundDifficultyMultiplier affects how the number of carts is calculated,
     * or how long a track is.
     */
    private double roundDifficultyMultiplier;
    /**
     * This is the original round difficulty multiplier stored, so difficulties can be reset after a round
     */
    private final double originalRoundDifficultyMultiplier;
    /**
     *  If no values are specified for difficulty multipliers, defaults of 1 will
     *  be set to leave the game in a "normal" difficulty.
     */
    public Difficulty(){
        this.costMultiplier = 1;
        this.roundDifficultyMultiplier = 1;
        this.originalRoundDifficultyMultiplier = 1;
    }

    /**
     *  This constructor can specify what values are set for each difficulty
     *  multiplier, making it easy to adjust aspects of gameplay to make things
     *  easier or harder.
     * @param cartAndRoundDifficultyMultiplier the multiplier for carts
     * @param costMultiplier the cost multiplier
     */
    public Difficulty(double costMultiplier, double cartAndRoundDifficultyMultiplier){
        this.costMultiplier = costMultiplier;
        this.roundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
        this.originalRoundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
    }

    /**
     * This function is used to modify the round difficulty based off of what style of round the player has chosen
     * @param value is the value of the update to the difficulty
     */
    public void updateDifficulty(String value) {
        if (value.equals("Risky")) {
            this.roundDifficultyMultiplier *= 1.33;
        } else if (value.equals("Safe")) {
            this.roundDifficultyMultiplier *= 0.66;
        }
    }

    /**
     * Gets the cost multiplier
     * @return the cost multiplier as a double
     */
    public double getCostMultiplier(){
        return this.costMultiplier;
    }

    /**
     * Increments the cost multiplier by 0.3, can be changed to help balance the game
     */
    public void incrementCostMultiplier() {
        this.costMultiplier += 0.3;
    }

    /**
     * Gets the round difficulty multiple
     * @return the round difficulty multiplier as an integer
     */
    public double roundDifficultyMultiplier(){
        return this.roundDifficultyMultiplier;
    }

    /**
     * Resets the round difficulty multiplier back to "normal" state
     */
    public void reset() {
        this.roundDifficultyMultiplier = originalRoundDifficultyMultiplier;
    }
}

