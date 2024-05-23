package seng201.team32.exceptions;

/**
 * This is thrown if a negative number is added to something that should only be adding positive
 */
public class NegativeAdditionException extends Exception{
    /**
     * Constructor of the exception if no arguments are taken
     */
    public NegativeAdditionException(){
        super();
    }
    /**
     * Constructor of NegativeAdditionException when message arguments is passed. Will create an extension with the message passed
     * @param message Message of the Extension.
     */
    public NegativeAdditionException(String message){
        super(message);
    }
}
