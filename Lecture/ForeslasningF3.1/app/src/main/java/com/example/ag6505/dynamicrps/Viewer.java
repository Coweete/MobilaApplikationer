package com.example.ag6505.dynamicrps;

public interface Viewer {
	public void setController(Controller controller);
	public void setHumanPoints(int points);
	public void setComputerPoints(int points);
	public void setHumanChoice(int choice);
	public void setComputerChoice(int choice);
	public void humanWinner();
	public void computerWinner();
}
