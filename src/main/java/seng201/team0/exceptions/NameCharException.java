package seng201.team0.exceptions;

public class NameCharException extends Exception{
    public NameCharException(){
        super();
    }
    public NameCharException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
