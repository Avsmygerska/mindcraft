package mindcraft.pack;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Game_View extends Activity implements OnClickListener{

	ArrayList<Integer> list = new ArrayList<Integer>();


	ImageButton button1,button2,button3,button4,button5,
	button6,button7,button8,button9;
	Button button10, button11;

	TextView countdown, timeToStart;

	CountDownTimer tm;
	private static final int YOU_LOST = 1; 
	private static final int WELL_DONE_MultiPlayer = 2;
	private static final int START = 3;
	private int state = 1;
	private int current = 0;
	private int currentLength = 5;
	private int incrementing;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.gameview);
		
		//Bundle extras = getIntent().getExtras();

		//incrementing = extras.getInt("Incrementing");
		
		System.out.println(incrementing);
		
		button1  = (ImageButton) this.findViewById(R.id.Button1);
		button2  = (ImageButton) this.findViewById(R.id.Button2);
		button3  = (ImageButton) this.findViewById(R.id.Button3);
		button4  = (ImageButton) this.findViewById(R.id.Button4);
		button5  = (ImageButton) this.findViewById(R.id.Button5);
		button6  = (ImageButton) this.findViewById(R.id.Button6);
		button7  = (ImageButton) this.findViewById(R.id.Button7);
		button8  = (ImageButton) this.findViewById(R.id.Button8);
		button9  = (ImageButton) this.findViewById(R.id.Button9);
		button10 = (Button) this.findViewById(R.id.Button10);
		button11 = (Button) this.findViewById(R.id.Button11);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);
		button10.setOnClickListener(this);
		button10.setEnabled(false);
		button11.setOnClickListener(this);
		button11.setVisibility(View.GONE);

		countdown = (TextView) this.findViewById(R.id.mTextField);
		timeToStart = (TextView) this.findViewById(R.id.mTextField2);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Button1:
			if(state == 1)
				list.add(1);
			else{
				if(list.get(current) == 1)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button2:
			if(state == 1)
				list.add(2);
			else{
				if(list.get(current) == 2)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button3:
			if(state == 1)
				list.add(3);
			else{
				if(list.get(current) == 3)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button4:
			if(state == 1)
				list.add(4);
			else{
				if(list.get(current) == 4)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button5:
			if(state == 1)
				list.add(5);
			else{
				if(list.get(current) == 5)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button6:
			if(state == 1)
				list.add(6);
			else{
				if(list.get(current) == 6)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button7:
			if(state == 1)
				list.add(7);
			else{
				if(list.get(current) == 7)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button8:
			if(state == 1)
				list.add(8);
			else{
				if(list.get(current) == 8)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;
		case R.id.Button9:
			if(state == 1)
				list.add(9);
			else{
				if(list.get(current) == 9)
					current++;
				else{
					tm.cancel();
					showDialog(YOU_LOST);
				}
			}
			break;

		case R.id.Button10:
			Context context = getApplicationContext();
			StringBuffer str = new StringBuffer();
			for(int i = 0; i< list.size(); i++){
				str.append(list.get(i) + " ");

			}
			CharSequence text = "The Numbers that have been pressed is: \n" + str.toString();
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			button10.setVisibility(View.GONE);
			showDialog(START);
		
		}

		if(state == 1 && list.size() == currentLength){ 
			disableButtons();
		}
		if(state == 2 && current == list.size()){
			tm.cancel();
			showDialog(WELL_DONE_MultiPlayer);
			reset();
			
		}

	}
	public void disableButtons(){

		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		button9.setEnabled(false);
		button10.setEnabled(true);
		button11.setEnabled(true);


	}
	public void enableButtons(){
		button1.setEnabled(true);
		button2.setEnabled(true);
		button3.setEnabled(true);
		button4.setEnabled(true);
		button5.setEnabled(true);
		button6.setEnabled(true);
		button7.setEnabled(true);
		button8.setEnabled(true);
		button9.setEnabled(true);
		button10.setEnabled(false);
		button11.setEnabled(false);
	}
	public void reset(){
		list = new ArrayList<Integer>();
		state = 1;
		current = 0;
		countdown.setText("");
		enableButtons();
		button10.setVisibility(View.VISIBLE);
		button11.setVisibility(View.GONE);
		
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case YOU_LOST:
			return new AlertDialog.Builder(this)
			.setMessage("")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					//lösklödka
					
					Game_View.this.finish();
				}
			})
			.create();	


		case WELL_DONE_MultiPlayer:
			return new AlertDialog.Builder(this)
			.setMessage("WELL DONE \n\n Next players turn")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					currentLength = currentLength + incrementing;
					dialog.cancel();
				}
			})
			.create();
			
		case START:
			return new AlertDialog.Builder(this)
			.setMessage("READY TO START?")
			.setCancelable(false)
			.setPositiveButton("Start!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					tm = new CountDownTimer(5000, 1000) {

						public void onTick(long millisUntilFinished) {
							timeToStart.setText("Seconds untill you may start: "+ millisUntilFinished / 1000);
						}

						public void onFinish() {
							state = 2;
							enableButtons();
							timeToStart.setText("");
							tm = new CountDownTimer(30000, 1000) {

								public void onTick(long millisUntilFinished) {
									countdown.setText(""+ millisUntilFinished / 1000);
								}

								public void onFinish() {
									countdown.setText("You Lost!");
									showDialog(YOU_LOST);

								}
							}.start();

						}
					}.start();
				}
			})
			.create();
		default:
			break;
		}

		return null;

	}

	//private class 
}
