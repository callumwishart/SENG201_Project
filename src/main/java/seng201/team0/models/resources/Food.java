package seng201.team0.models.resources;

public class Food extends Resource{
    public Food() {
        super("Food", 15);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the obj is null
        if (obj == null) {
            return false;
        }
        // Check if both objects are of the same class
        return this.getClass() == obj.getClass();
    }

    @Override
    public Food clone() throws CloneNotSupportedException {
        return (Food) super.clone();
    }
}
