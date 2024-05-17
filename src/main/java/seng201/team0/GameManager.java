package seng201.team0;

import java.util.function.Consumer;

public class GameManager {
    private final Consumer<GameManager> startLauncher;
    private final Consumer<GameManager> setupLauncher;
    private final Runnable clearScreen;
    public GameManager(Consumer<GameManager> startLauncher, Consumer<GameManager> setupLauncher, Runnable clearScreen) {
        this.startLauncher = startLauncher;
        this.setupLauncher = setupLauncher;
        this.clearScreen = clearScreen;
        launchStartScreen();
    }
    public void closeStartScreen() {
        clearScreen.run();
        launchSetupScreen();
    }
    public void launchStartScreen() {startLauncher.accept(this);}
    public void launchSetupScreen() {setupLauncher.accept(this);}
}
