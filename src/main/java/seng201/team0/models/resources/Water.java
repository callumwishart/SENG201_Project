package seng201.team0.models.resources;

public class Water extends Resource{
    public Water() {
        super("Water", 10);
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
    public Water clone() throws CloneNotSupportedException {
        return (Water) super.clone();
    }

}
