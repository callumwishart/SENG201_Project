package seng201.team0.exceptions;

public class TowerInventoryFullException extends Exception{
    TowerInventoryFullException(){
        super();
    }
    public TowerInventoryFullException(String message){
        super(message);
    }
}
