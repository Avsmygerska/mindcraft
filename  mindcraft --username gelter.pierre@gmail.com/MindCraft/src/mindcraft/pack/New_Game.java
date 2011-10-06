package mindcraft.pack;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
	
	ReadOptions ro;
	DbHelper help;
	List<CharSequence> difficulty;
	ArrayAdapter<CharSequence> adapter;
	
	CheckBox singleplayer, multiplayer;
	Button back;
	ImageButton start; 
	Spinner spinner;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		
		help = new DbHelper(this);
		ro = new ReadOptions(help);
				
		singleplayer 	= (CheckBox) this.findViewById(R.id.checkBoxSingleplayer);
		multiplayer	 	= (CheckBox) this.findViewById(R.id.checkBoxMultiplayer);
		back 		 	= (Button) this.findViewById(R.id.backButton);
		start			= (ImageButton) this.findViewById(R.id.startButton);
		spinner		 	= (Spinner) this.findViewById(R.id.spinner1);
		
		difficulty = new ArrayList<CharSequence>();
		adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, difficulty);
		

		
		Cursor cursor	= ro.readAll();
		cursor.moveToFirst();
		
		for(int i = 0; i< cursor.getCount(); i++){
			int pos = cursor.getColumnIndex(DbHelper.C_NAME_OPT);
			difficulty.add(cursor.getString(pos));
			cursor.moveToNext();
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
				//StartGame
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
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
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
