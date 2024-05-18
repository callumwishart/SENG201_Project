package seng201.team0.models.gameplay;

import seng201.team0.exceptions.FullCartException;
import seng201.team0.models.resources.Resource;

public class Cart {
    private String name;
    private int size;
    private String resourceType;
    private int speed;
    private boolean universal;
    private Resource[] cargo;
    private int cargoSlotsFilled;
    private int distance;
    private boolean finished;

    Cart(int size, Resource resource, int speed) {
        this.size = size;
        this.cargo = new Resource[this.size];
        this.cargoSlotsFilled = 0;
        this.resourceType = resource.getResourceType();
        this.speed = speed;
        this.universal = false;
        this.distance = 0;
        this.name = String.format("%s Cart", this.resourceType);
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

    public String getResourceType() {
        return resourceType;
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

    public boolean fillCart(Resource resource) throws FullCartException {
        if (this.isFull()){
            throw new FullCartException(String.format("Cart %s is full", this.name));
        }
        else{
            this.cargo[cargoSlotsFilled++] = resource;
            return true;
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

}
