package seng201.team0.exceptions;

public class TowerNotFoundException extends Exception{
    public TowerNotFoundException(){
        super();
    }
    TowerNotFoundException(String message){
        super(message);
    }
}
