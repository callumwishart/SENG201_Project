package seng201.team32.exceptions;

/**
 * This is thrown if the inventory is full of upgrades and cannot except anymore (cannot accept more than 3)
 */
public class UpgradesFullException extends Exception {
    /**
     * Constructor of the exception if no arguments are taken
     */
    public UpgradesFullException(){
        super();
    }
}
