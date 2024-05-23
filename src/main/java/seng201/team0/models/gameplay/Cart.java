package seng201.team0.models.gameplay;

import seng201.team0.exceptions.FullCartException;
import seng201.team0.models.resources.Resource;

public class Cart {
    private String name;
    private int size;
    private Resource resource;
    private int speed;
    private boolean universal;
    private Resource[] cargo;
    private int cargoSlotsFilled;
    private int distance;
    private boolean finished;

    public Cart(int size, int speed, Resource resource) {
        this.size = size;
        this.cargo = new Resource[this.size];
        this.cargoSlotsFilled = 0;
        this.resource = resource;
        this.speed = speed;
        this.universal = false;
        this.distance = 0;
        this.name = String.format("%s Cart", this.resource.getResourceType());
    }

    Cart(int size, int speed) {
        this.name = "Universal Cart";
        this.size = size;
        this.cargo = new Resource[this.size];
        this.cargoSlotsFilled = 0;
        this.speed = speed;
        this.universal = true;
        this.distance = 0;
    }

    public int getSize() {
        return size;
    }

    public Resource getResource() {
        return resource;
    }

    public boolean isUniversal() {
        return universal;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDistance(){
        return this.distance;
    }

    public void changeSpeed(int amount) {
        speed += amount;
    }

    public boolean isFull() {
        return this.cargoSlotsFilled == this.size;
    }

    public void fillCart(Resource resource) throws FullCartException {
        if (this.isFull()){
            throw new FullCartException(String.format("Cart %s is full", this.name));
        }
        else{
            this.cargo[cargoSlotsFilled++] = resource;
        }
    }

    /**
     * increments cart distance based on 1 second of travel
     */
    public void incrementDistance() {
        distance += speed;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean state) {
        finished = state;
    }

    public String getName() {
        return this.name;
    }

    public int getCargoSlotsFilled() {
        return cargoSlotsFilled;
    }

    public Resource[] getCargo() {
        return this.cargo;
    }
}
