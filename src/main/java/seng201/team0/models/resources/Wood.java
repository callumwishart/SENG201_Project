package seng201.team0.models.resources;

public class Wood extends Resource{
    public Wood() {
        super("Wood", 20);
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
    public Wood clone() throws CloneNotSupportedException {
        return (Wood) super.clone();
    }
}
