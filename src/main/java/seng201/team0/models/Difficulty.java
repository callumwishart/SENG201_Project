package seng201.team0.models;

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

    private double originalRoundDifficultyMultiplier;
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
     */
    public Difficulty(double costMultiplier, double cartAndRoundDifficultyMultiplier){
        this.costMultiplier = costMultiplier;
        this.roundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
        this.originalRoundDifficultyMultiplier = cartAndRoundDifficultyMultiplier;
    }

    /**
     * This function is used to modify the round difficulty based off of what style of round the player has chosen
     */
    public void updateDifficulty(String value) {
        if (value.equals("Risky")) {
            this.roundDifficultyMultiplier *= 1.33;
        } else if (value.equals("Safe")) {
            this.roundDifficultyMultiplier *= 0.66;
        }
    }

    public double getCostMultiplier(){
        return this.costMultiplier;
    }

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

