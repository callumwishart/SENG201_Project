package seng201.team32.exceptions;

public class TowerInventoryFullException extends Exception{
    TowerInventoryFullException(){
        super();
    }
    public TowerInventoryFullException(String message){
        super(message);
    }
}
