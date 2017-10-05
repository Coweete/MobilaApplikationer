package com.mah.ag0071.rockpapersissor;

/**
 * Created by User on 2017-09-08.
 */

public class RPSController {

    public static final int ROCK = 0, PAPER = 1, SCISSORS = 2, EMPTY = 3,
            WINNER = 4, LOSER = 5;
    private RPSPlayer computerPlayer;
    private InputFragment input;
    private ViewerFragment viewer;
    private int humanPoints, computerPoints;

    public RPSController(RPSPlayer computerPlayer,ViewerFragment viewerFragment,InputFragment inputFragment){
        this.computerPlayer = computerPlayer;
        this.viewer = viewerFragment;
        this.input = inputFragment;
        input.setController(this);
    }

    public void newGame(){
        initGame();
    }

    private void initGame() {
        humanPoints = computerPoints = 0;
        input.enableChoiceButtons(true);
        viewer.setHumanPoints(humanPoints);
        viewer.setComputerPoints(computerPoints);
        viewer.setHumanChoice(RPSController.EMPTY);
        viewer.setComputerChoice(RPSController.EMPTY);
    }

    private void rules(int humanChoice, int computerChoice) {
        if (humanChoice != computerChoice) {
            if ((humanChoice == RPSController.ROCK && computerChoice == RPSController.SCISSORS)
                    || (humanChoice == RPSController.PAPER && computerChoice == RPSController.ROCK)
                    || (humanChoice == RPSController.SCISSORS && computerChoice == RPSController.PAPER))
                humanPoints++;
            else
                computerPoints++;
        }
    }

    private void winner() {
        if ((humanPoints == 3) || (computerPoints == 3)) {
            input.enableChoiceButtons(false);
            if (humanPoints == 3) {
                viewer.humanWinner();
            } else {
                viewer.computerWinner();
            }
        }
    }

    public void newChoice(int humanChoice){
        int computerChoice = computerPlayer.nextChoice();
        rules(humanChoice, computerChoice);
        viewer.setHumanChoice(humanChoice);
        viewer.setComputerChoice(computerChoice);
        viewer.setHumanPoints(humanPoints);
        viewer.setComputerPoints(computerPoints);
        winner();
    }
}
