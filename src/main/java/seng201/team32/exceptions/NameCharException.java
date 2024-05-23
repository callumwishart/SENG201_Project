package seng201.team32.exceptions;

/**
 * Thrown if the name does not follow specifications
 */
public class NameCharException extends Exception{
    NameCharException(){
        super();
    }
    /**
     * Constructor of the exception if a message argument is passed
     * @param message is the message of the exception
     */
    public NameCharException(String message){
        super(message);
    }
    /**
     * Gets the message of the Exception
     * @return a string of the exception message
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
