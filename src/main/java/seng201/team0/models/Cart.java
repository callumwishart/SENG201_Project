package seng201.team0.models;

public class Cart {
    private int size;
    private String resourceType;
    private int speed;
    Cart(int inputSize, String inputResource, int inputSpeed) {
        size = inputSize;
        resourceType = inputResource;
        speed = inputSpeed;
    }
    public int getSize() {
        return size;
    }
    public String getResourceType() {
        return resourceType;
    }

    public int getSpeed() {
        return speed;
    }
}
