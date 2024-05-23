package seng201.team32.exceptions;

/**
 * This is thrown if the inventory is full and cannot except
 */
public class TowerInventoryFullException extends Exception{
    /**
     * Constructor of TowerInventoryFullException when message arguments is passed. Will create an extension with the message passed
     * @param message Message of the Extension.
     */
    public TowerInventoryFullException(String message){
        super(message);
    }
}
