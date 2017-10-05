package com.example.ag6505.dynamicrps;

import java.util.Random;

public class RPSPlayer {
	private Random random = new Random();
	public int nextChoice() {
		int choice = Controller.ROCK;
		switch(random.nextInt(3)) {
		    case 0: choice = Controller.PAPER; break;
		    case 1: choice = Controller.SCISSORS; break;
		}
		return choice;
	}
}
