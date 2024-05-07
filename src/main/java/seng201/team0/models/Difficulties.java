package seng201.team0.models;

public enum Difficulties {
    EASY(1),
    HARD(2);

    private int value;

    private Difficulties(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
