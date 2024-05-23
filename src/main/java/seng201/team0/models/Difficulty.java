package seng201.team0.models;

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
     *  randomEventMultiplier affects how often random events are triggered.
     */
    private final double originalCostMultiplier;
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
        this.originalCostMultiplier = 1;
        this.originalRoundDifficultyMultiplier = 1;
    }

    /**
     *  This constructor can specify what values are set for each difficulty
     *  multiplier, making it easy to adjust aspects of gameplay to make things
     *  easier or harder.
     */
    public Difficulty(double costMultiplier, double cartAndRoundDifficultyMultiplier, double randomEventMultiplier){
        this.costMultiplier = costMultiplier;
        this.roundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
        this.originalCostMultiplier = costMultiplier;
        this.originalRoundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
    }

    /**
     * This function is used to modify the round difficulty based off of what style of round the player has chosen
     */
    public void updateDifficulty(String value) {
        if (value.equals("Risky")) {
            this.roundDifficultyMultiplier *= 1.2;
        } else if (value.equals("Safe")) {
            this.roundDifficultyMultiplier *= 0.8;
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
     * Gets the round difficulty multiple
     * @return the round difficulty multiplier as an integer
     */
    public double roundDifficultyMultiplier(){
        return this.roundDifficultyMultiplier;
    }

    /**
     * Resets the difficulty multipliers back to "normal" state
     */
    public void reset() {
        this.costMultiplier = originalCostMultiplier;
        this.roundDifficultyMultiplier = originalRoundDifficultyMultiplier;
    }
}

