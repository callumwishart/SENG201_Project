package seng201.team0.models.resources;

public class Metal extends Resource{
    public Metal() {
        super("Metal", 30);
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
    public Metal clone() throws CloneNotSupportedException {
        return (Metal) super.clone();
    }

}
