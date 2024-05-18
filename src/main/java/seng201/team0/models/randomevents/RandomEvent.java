package seng201.team0.models.randomevents;

public class RandomEvent {
    private String name;
    private String description;
    public RandomEvent (String inputName, String inputDescription) {
        name = inputName;
        description = inputDescription;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
