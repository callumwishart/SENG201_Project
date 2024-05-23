package seng201.team32.utils;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utilities class
 * <p>
 *     Holds functions to create random events for random event in game
 * </p>
 */
public class Utilities {
    /**
     * Default constructor for Utilities
     */
    public Utilities() {}

    /**
     * Performs a weighted coin toss.
     * @author ChatGPT
     * <a href="https://chatgpt.com/share/fd03064d-77f7-478d-a4e7-104fadb5d1d0">Chat Link</a>
     * @param probability The probability of returning true (between 0.0 and 1.0).
     * @return true if the toss results in true based on the given probability, false otherwise.
     */
    public static boolean weightedCoinToss(double probability) {
        Random random = new Random();
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        }
        return random.nextDouble() < probability;
    }

    /**
     * Generate number to determine random event
     * <p>
     *     Creates a random number between 1 and 3 and returns it
     * </p>
     * @return a random integer between 1 and 3
     */
    public static int randomEventSelector() {
        return ThreadLocalRandom.current().nextInt(1,4);
    }

}
