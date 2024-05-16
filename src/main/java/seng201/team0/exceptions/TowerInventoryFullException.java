package seng201.team0.exceptions;

public class TowerInventoryFullException extends Exception{
    TowerInventoryFullException(){
        super();
    }
    TowerInventoryFullException(String message){
        super(message);
    }
}
