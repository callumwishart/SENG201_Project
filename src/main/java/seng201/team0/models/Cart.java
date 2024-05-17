package seng201.team0.models;

import seng201.team0.models.Resources.Resource;

import java.util.List;

public class Cart {
    private int size;
    private Resource resourceType;
    private int speed;
    private boolean universal;
    private List<Resource> cargo;
    private double distance;
    private boolean finished;

    Cart(int inputSize, Resource inputResource, int inputSpeed) {
        this.size = inputSize;
        resourceType = inputResource;
        speed = inputSpeed;
    }
    public int getSize() {
        return size;
    }
    public Resource getResourceType() {
        return resourceType;
    }
    public boolean isUniversal() {
        return universal;
    }
    public int getSpeed() {
        return speed;
    }
    public void changeSpeed(int amount) {
        speed += amount;
    }
    public boolean isFull() {
        // Not sure how this works yet
        return true;
    }
    public void incrementDistance() {
        distance += speed;
    }
    public boolean getFinished() {
        return finished;
    }
    public void setFinished(boolean state) {
        finished = state;
    }
}
