package com.example.ag6505.dynamicrps;

public class RPSController implements Controller {
	private MainActivity activity;
	private RPSPlayer computerPlayer;
	private Input input;
	private Viewer viewer;
	private int humanPoints, computerPoints;
	private int humanChoice = RPSController.EMPTY;
    private int computerChoice = RPSController.EMPTY;

	public RPSController(MainActivity activity, RPSPlayer computerPlayer) {
		this.activity = activity;
		this.computerPlayer = computerPlayer;
		viewer = new ImageViewerFragment();
		input = new ImageInputFragment();
		viewer.setController(this);
		input.setController(this);
		activity.setViewer(viewer);
		activity.setInput(input);
	}

	public void newGame() {
		initGame();
	}

	public void newChoice(int humanChoice) {
		this.humanChoice = humanChoice;
		computerChoice = computerPlayer.nextChoice();
		rules(humanChoice, computerChoice);
		updateViewer();
		winner();
	}
	
	private void rules(int humanChoice, int computerChoice) {
		if (humanChoice != computerChoice) {
			if ((humanChoice == Controller.ROCK && computerChoice == Controller.SCISSORS)
					|| (humanChoice == Controller.PAPER && computerChoice == Controller.ROCK)
					|| (humanChoice == Controller.SCISSORS && computerChoice == Controller.PAPER))
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
	
	public void updateViewer() {
		viewer.setHumanPoints(humanPoints);
		viewer.setComputerPoints(computerPoints);
		viewer.setHumanChoice(humanChoice);
		viewer.setComputerChoice(computerChoice);
	}
	
	public void changeUI() {
		if(viewer instanceof TextViewerFragment)
		     viewer = new ImageViewerFragment();
		else
			viewer = new TextViewerFragment();
		viewer.setController(this);
		activity.setViewer(viewer);
		if(input instanceof TextInputFragment)
			input = new ImageInputFragment();
		else
		    input = new TextInputFragment();
		input.setController(this);
		activity.setInput(input);
	}

	private void initGame() {
		humanPoints = computerPoints = 0;
		humanChoice = computerChoice = Controller.EMPTY;
		input.enableChoiceButtons(true);
		updateViewer();
	}
}
