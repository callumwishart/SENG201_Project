package seng201.team0.models.resources;

public class Goods extends Resource{
    public Goods() {
        super("Goods", 50);
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
    public Goods clone() throws CloneNotSupportedException {
        return (Goods) super.clone();
    }

}
