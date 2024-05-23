package seng201.team32.exceptions;

/**
 * This is thrown if the upgrade cannot be applied due to the max number of upgrades reached
 */
public class UpgradeMaxException extends Exception {
    /**
     * Constructor of the exception if no arguments are taken
     */
    public UpgradeMaxException() {
        super();
    }
}