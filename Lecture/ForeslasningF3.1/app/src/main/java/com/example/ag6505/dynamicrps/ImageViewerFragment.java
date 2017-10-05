package com.example.ag6505.dynamicrps;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageViewerFragment extends Fragment implements Viewer {
	private Controller controller;
	private TextView tvHumanPoints;
	private TextView tvComputerPoints;
	private ImageView ivHumanChoice;
	private ImageView ivComputerChoice;
	private Drawable winner, loser;
	private Drawable[] humanImages = new Drawable[4];
	private Drawable[] computerImages = new Drawable[4];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.imageviewer, container, false);
		initializeResources();
		registerComponents(view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		controller.updateViewer();
	}

	private void initializeResources() {
		Activity activity = getActivity();
		Drawable empty = ContextCompat.getDrawable(activity, R.drawable.empty);
		humanImages[RPSController.ROCK] = ContextCompat.getDrawable(activity, R.drawable.rockleft);
		humanImages[RPSController.PAPER] = ContextCompat.getDrawable(activity, R.drawable.paperleft);
		humanImages[RPSController.SCISSORS] = ContextCompat.getDrawable(activity, R.drawable.scissorsleft);
		humanImages[RPSController.EMPTY] = empty;
		computerImages[RPSController.ROCK] = ContextCompat.getDrawable(activity, R.drawable.rockright);
		computerImages[RPSController.PAPER] = ContextCompat.getDrawable(activity, R.drawable.paperright);
		computerImages[RPSController.SCISSORS] = ContextCompat.getDrawable(activity, R.drawable.scissorsright);
		computerImages[RPSController.EMPTY] = empty;
		winner = ContextCompat.getDrawable(activity, R.drawable.winner);
		loser = ContextCompat.getDrawable(activity, R.drawable.loser);
	}

	private void registerComponents(View view) {
		tvHumanPoints = (TextView)view.findViewById(R.id.tvHumanPoints);
		tvComputerPoints = (TextView)view.findViewById(R.id.tvComputerPoints);
		ivHumanChoice = (ImageView)view.findViewById(R.id.ivHumanChoice);
		ivComputerChoice = (ImageView)view.findViewById(R.id.ivComputerChoice);
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
		ivHumanChoice.setImageDrawable(humanImages[choice]);
	}
	
	public void setComputerChoice(int choice) {
		ivComputerChoice.setImageDrawable(computerImages[choice]);
	}
	
	public void humanWinner() {
		LayerDrawable theWinner = new LayerDrawable(new Drawable[]{ivHumanChoice.getDrawable(),winner});
		LayerDrawable theLoser = new LayerDrawable(new Drawable[]{ivComputerChoice.getDrawable(),loser});		
		ivHumanChoice.setImageDrawable(theWinner);
		ivComputerChoice.setImageDrawable(theLoser);
	}
	
	public void computerWinner() {
		LayerDrawable theWinner = new LayerDrawable(new Drawable[]{ivComputerChoice.getDrawable(),winner});
		LayerDrawable theLoser = new LayerDrawable(new Drawable[]{ivHumanChoice.getDrawable(),loser});		
		ivComputerChoice.setImageDrawable(theWinner);
		ivHumanChoice.setImageDrawable(theLoser);
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
