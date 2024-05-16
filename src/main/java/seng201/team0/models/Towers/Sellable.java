package seng201.team0.models.Towers;

public interface Sellable {
    public default double getSellCost(Tower tower) {
        if (!tower.getStatus()) {
            return tower.getCost() * 0.7;
        } else {
            return 0.0;
        }
    }
}
