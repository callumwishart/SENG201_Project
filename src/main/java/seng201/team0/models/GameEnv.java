package seng201.team0.models;

public class GameEnv {
    Player player;
    Difficulties difficulties;
    Shop shop;

    int numRounds;
    int difficultyMultiplier;
    GameEnv(Player inputPlayer, Difficulties inputDifficulties, int inputNumRounds, int inputDifficultyMultiplier) {
        player = inputPlayer;
        difficulties = inputDifficulties;
        numRounds = inputNumRounds;
        difficultyMultiplier = inputDifficultyMultiplier;
    }

}
