package seng201.team32.models.gameplay;

import seng201.team32.exceptions.FullCartException;
import seng201.team32.models.resources.Resource;

/**
 * The Cart class encapsulates all cart-related data for use in round creation and gameplay.
 */
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

    /**
     * The Cart constructor
     * @param size is an int that represents how many resources a cart can accomodate
     * @param speed is the distance per second that the cart travels at
     * @param resource is the resource that will be used by the towers to determine whether it can be filled by a particular tower
     */
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

    /**
     * The Universal Cart constructor, providing no resource to the {@code Cart()} constructor will make it universal, so all towers can fill it.
     * @param size is an int that represents how many resources a cart can accomodate
     * @param speed is the distance per second that the cart travels at
     */
    Cart(int size, int speed) {
        this.name = "Universal Cart";
        this.size = size;
        this.cargo = new Resource[this.size];
        this.cargoSlotsFilled = 0;
        this.speed = speed;
        this.universal = true;
        this.distance = 0;
    }

    /**
     * Gets the size of the cart
     * @return size of cart
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the resource of the cart
     * @return the resource of cart
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Returns a boolean where true means that the cart is universal
     * @return boolean
     */
    public boolean isUniversal() {
        return universal;
    }

    /**
     * Gets the speed of the cart
     * @return cart speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the distance that the cart has traveled
     * @return int distance travelled
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * Changes the speed of the cart
     * @param amount how much to change the speed
     */
    public void changeSpeed(int amount) {
        speed += amount;
    }

    /**
     * Returns a boolean that indicates whether the cart is full or not
     * @return boolean indicating if cart is full
     */
    public boolean isFull() {
        return this.cargoSlotsFilled == this.size;
    }

    /**
     * Attempts to fill the cart with the resource provided, adding it to the cargo list
     * @param resource the resource to be added to cargo
     * @throws FullCartException cannot add, the cart is full
     */
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

    /**
     * Returns whether the cart is finished or not
     * @return cart's finished property
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the finished state of the cart to indicate if it's finished going the distance
     * @param state indicates if the cart is finished
     */
    public void setFinished(boolean state) {
        finished = state;
    }

    /**
     * Returns the cart's name
     * @return cart name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the cargo slots that have been filled according to the cargoSlotsFilled property
     * @return cart cargoSlotsFilled
     */
    public int getCargoSlotsFilled() {
        return cargoSlotsFilled;
    }

    /**
     * Gets the cargo list of the cart
     * @return cart cargo list
     */
    public Resource[] getCargo() {
        return this.cargo;
    }
}
