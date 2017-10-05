package com.example.ag6505.dynamicrps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ImageInputFragment extends Fragment implements Input {
	private Controller controller;
	private Button btnRock;
	private Button btnPaper;
	private Button btnScissors;
	private Button btnNewGame;
	private Button btnChangeUI;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.imageinput, container, false);
		initializeComponents(view);
		registerListeners();
		return view;
	}

	private void initializeComponents(View view) {
		btnRock = (Button)view.findViewById(R.id.btnRock);
		btnPaper = (Button)view.findViewById(R.id.btnPaper);
		btnScissors = (Button)view.findViewById(R.id.btnScissors);
		btnNewGame = (Button)view.findViewById(R.id.btnNewGame);
		btnChangeUI = (Button)view.findViewById(R.id.btnChangeUI);
	}

	private void registerListeners() {
		OnClickListener choiceButtons = new ChoiceButtonListener();
		OnClickListener gameButtons = new GameButtonListener();
		btnRock.setOnClickListener(choiceButtons);
		btnPaper.setOnClickListener(choiceButtons);
		btnScissors.setOnClickListener(choiceButtons);
		btnNewGame.setOnClickListener(gameButtons);
		btnChangeUI.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				controller.changeUI();
			}
		});
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void enableChoiceButtons(boolean enabled) {
		btnRock.setEnabled(enabled);
		btnPaper.setEnabled(enabled);
		btnScissors.setEnabled(enabled);
		btnChangeUI.setEnabled(enabled);
	}
	
	private class ChoiceButtonListener implements OnClickListener {
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.btnRock : controller.newChoice(Controller.ROCK); break;
			case R.id.btnPaper : controller.newChoice(Controller.PAPER); break;
			case R.id.btnScissors : controller.newChoice(Controller.SCISSORS); break;
			}
		}		
	}
	
	private class GameButtonListener implements OnClickListener {
		public void onClick(View v) {
			controller.newGame();
		}
	}
}