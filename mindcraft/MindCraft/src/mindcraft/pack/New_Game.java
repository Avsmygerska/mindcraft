package mindcraft.pack;

import java.util.ArrayList;
import java.util.List;

import mindcraft.database.DatabaseController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;


public class New_Game extends Activity implements OnClickListener, OnItemSelectedListener{
	
	private static final int NOT_SELECTED_MODE = 1;
	
	String currentDifficultySelected = "Default";

	List<CharSequence> difficulty;
	ArrayAdapter<CharSequence> adapter;
	
	CheckBox singleplayer, multiplayer;
	Button back;
	ImageButton start; 
	Spinner spinner;
	DatabaseController dc;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		
		//help = new DbHelper(this);
		//ro = new ReadOptions(help);
		dc = DatabaseController.initialize();
				
		singleplayer 	= (CheckBox) this.findViewById(R.id.checkBoxSingleplayer);
		multiplayer	 	= (CheckBox) this.findViewById(R.id.checkBoxMultiplayer);
		back 		 	= (Button) this.findViewById(R.id.backButton);
		start			= (ImageButton) this.findViewById(R.id.startButton);
		spinner		 	= (Spinner) this.findViewById(R.id.spinner1);
		
		difficulty = new ArrayList<CharSequence>();
		adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, difficulty);
		

		
		String[] options	= dc.readAllOptions();
				
		for(int i = 0; i< options.length; i++){
			difficulty.add(options[i]);
		}
		
		start.setOnClickListener(this);
		back.setOnClickListener(this);
		singleplayer.setOnClickListener(this);
		multiplayer.setOnClickListener(this);
		
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.startButton){
			if(singleplayer.isChecked() || multiplayer.isChecked() ){
				System.out.println(currentDifficultySelected);
				
				Intent intent = new Intent("Game_View");
				intent.putExtra("DifficultyName", currentDifficultySelected);
				if(singleplayer.isChecked())
					intent.putExtra("Mode", 1);
				else
					intent.putExtra("Mode", 2);
				intent.putExtra("Length", 5);
        		New_Game.this.startActivity(intent);
        		New_Game.this.finish();
			}else{
				showDialog(NOT_SELECTED_MODE);
			}
		}
		
		if(v.getId() == R.id.checkBoxSingleplayer){
			multiplayer.setChecked(false);
			
		}
		if(v.getId() == R.id.checkBoxMultiplayer){
			singleplayer.setChecked(false);
			
		}
		if(v.getId() == R.id.backButton){
			New_Game.this.finish();
			
		}
		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long arg3) {
		
		currentDifficultySelected = parent.getItemAtPosition(pos).toString();  
		System.out.println(currentDifficultySelected);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case NOT_SELECTED_MODE:
			return new AlertDialog.Builder(this)
			.setMessage("You have to select gamemode! \n				Do it NAO!")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.create();	
		default:
			break;
		}

		return null;
	
		}
	

}
