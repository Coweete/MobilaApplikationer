package com.example.ag6505.dynamicrps;

public interface Controller {
	public static final int ROCK = 0, PAPER = 1, SCISSORS = 2, EMPTY = 3,
			WINNER = 4, LOSER = 5;
	public void newGame();
	public void newChoice(int choice);
	public void updateViewer();
	public void changeUI();
}
