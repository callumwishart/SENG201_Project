package seng201.team0.exceptions;

public class NegativeAdditionException extends Exception{
    NegativeAdditionException(){
        super();
    }
    NegativeAdditionException(String message){
        super(message);
    }
}
