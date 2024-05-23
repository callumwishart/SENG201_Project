package seng201.team32.exceptions;

/**
 * Thrown if the cart is full
 */
public class FullCartException extends Exception{
    /**
     * Constructor of the exception if no arguments are taken
     */
    public FullCartException(){
        super();
    }
    /**
     * Constructor of FullCartException when message arguments is passed. Will create an extension with the message passed
     * @param message Message of the Extension.
     */
    public FullCartException(String message){
        super(message);
    }
}
