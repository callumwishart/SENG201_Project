package seng201.team32.exceptions;

/**
 * Exception thrown if the Consumable is already active
 */
public class ActiveConsumableException extends Exception{
    /**
     * Constructor of ActiveConsumerException when no arguments are taken.
     */
    public ActiveConsumableException(){
        super();
    }

    /**
     * Constructor of ActiveConsumerException when message arguments is passed. Will create an extension with the message passed
     * @param message Message of the Extension.
     */
    public ActiveConsumableException(String message){
        super(message);
    }
}
