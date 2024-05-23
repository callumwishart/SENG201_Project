package seng201.team32.exceptions;

/**
 * This is thrown if you can't purchase something
 */
public class PurchaseException extends Exception{
    /**
     * Constructor of PurchaseException when message arguments is passed. Will create an extension with the message passed
     * @param message Message of the Extension.
     */
    public PurchaseException(String message){
        super(message);
    }
}
