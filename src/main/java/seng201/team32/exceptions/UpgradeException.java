package seng201.team32.exceptions;

/**
 * This is thrown if the user does not have enough points to upgrade the tower
 */
public class UpgradeException extends Exception{
    /**
     * Constructor of the exception if no arguments are taken
     */
    public UpgradeException(){
        super();
    }
}
