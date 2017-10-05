package com.example.ag6505.dynamicrps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewerFragment extends Fragment implements Viewer {
	private Controller controller;
	private TextView tvHumanPoints;
	private TextView tvComputerPoints;
	private TextView tvHumanChoice;
	private TextView tvComputerChoice;
	private String[] choiceStr = new String[4];  //{"ROCK","PAPER","SCISSORS"," "};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.textviewer, container, false);
		choiceStr[Controller.ROCK] = "ROCK";
		choiceStr[Controller.PAPER] = "PAPER";
		choiceStr[Controller.SCISSORS] = "SCISSORS";
		choiceStr[Controller.EMPTY] = "";
		registerComponents(view);
		return view;
	}	

	@Override
	public void onResume() {
		super.onResume();
		controller.updateViewer();
	}

	private void registerComponents(View view) {
		tvHumanPoints = (TextView)view.findViewById(R.id.tvHumanPoints);
		tvComputerPoints = (TextView)view.findViewById(R.id.tvComputerPoints);
		tvHumanChoice = (TextView)view.findViewById(R.id.tvHumanChoice);
		tvComputerChoice = (TextView)view.findViewById(R.id.tvComputerChoice);
	}
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setHumanPoints(int points) {
		tvHumanPoints.setText(String.valueOf(points));
	}

	public void setComputerPoints(int points) {
		tvComputerPoints.setText(String.valueOf(points));
	}
	
	public void setHumanChoice(int choice) {
		tvHumanChoice.setText(choiceStr[choice]);
	}
	
	public void setComputerChoice(int choice) {
		tvComputerChoice.setText(choiceStr[choice]);
	}
	
	public void humanWinner() {
		tvHumanChoice.setText(tvHumanChoice.getText()+" WINS");
		tvComputerChoice.setText(tvComputerChoice.getText()+" LOSES");
	}
	
	public void computerWinner() {
		tvComputerChoice.setText(tvComputerChoice.getText()+" WINS");
		tvHumanChoice.setText(tvHumanChoice.getText()+" LOSES");
		
	}
	@Override
	public void onDestroy() {
//		Toast.makeText(getActivity(),"I onDestroy", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	public void onDetach() {
//		Toast.makeText(getActivity(),"I onDetach", Toast.LENGTH_SHORT).show();
		super.onDetach();
	}

	@Override
	public void onPause() {
//		Toast.makeText(getActivity(),"I onPause", Toast.LENGTH_SHORT).show();
		super.onPause();
	}

	@Override
	public void onStop() {
//		Toast.makeText(getActivity(),"I onStop", Toast.LENGTH_SHORT).show();
		super.onStop();
	}
	
}
