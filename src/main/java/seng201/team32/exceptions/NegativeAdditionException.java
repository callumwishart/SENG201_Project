package seng201.team32.exceptions;

public class NegativeAdditionException extends Exception{
    public NegativeAdditionException(){
        super();
    }
    public NegativeAdditionException(String message){
        super(message);
    }
}
