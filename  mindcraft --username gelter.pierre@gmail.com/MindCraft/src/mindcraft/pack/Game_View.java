package mindcraft.pack;

import java.util.ArrayList;
import java.util.Random;
import mindcraft.database.DatabaseController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game_View extends Activity implements OnClickListener{

	ArrayList<Integer> list = new ArrayList<Integer>();
	ArrayList<ImageButton> bList = new ArrayList<ImageButton>();
	ArrayList<ImageButton> allButtons = new ArrayList<ImageButton>();
	Integer [] imglist = {R.drawable.button1,R.drawable.button2,
			R.drawable.button3,R.drawable.button4,R.drawable.button5,
			R.drawable.button6,R.drawable.button7,R.drawable.button8,
			R.drawable.button9};
	
	private ImageButton button1,button2,button3,button4,button5,
								button6,button7,button8,button9;
	private Button button10, button11;
	private RefreshShow mRefreshShow = new RefreshShow();
	private String currentDifficulty;

	private TextView countdown, timeToStart;
	private CountDownTimer tm;

	private static final int YOU_LOST = 1; 
	private static final int WELL_DONE_MULTIPLAYER = 2;
	private static final int WELL_DONE_SINGLEPLAYER = 3;
	private static final int START = 4;
	private static final int HIGHSCORE = 5;
	private static final int SAVE_GAME = 6;
	private static final int OVERWRITE = 7; 
	private static final int DO_YOU_WANT_TO_QUIT = 8;
	private int state = 1;
	private int current = 0;
	private int currentLength;
	private int normalbutton = R.drawable.buttonselector;
	private int lastbutton = 0;
	private int newbutton;
	private Integer maxScore = 1000000;
	private Integer score;
	private int showLength = 0;
	private int mode;
	private int incrementing;
	private int completionTime;
	private int waittime;
	private int patternTime;
	private int speedInterval;
	
	private DatabaseController dc;
	
	
	


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.gameview);
		dc = DatabaseController.initialize();
		
		Bundle extras = getIntent().getExtras();
		currentDifficulty 		= extras.getString("DifficultyName");
		mode 					= extras.getInt("Mode");
		currentLength			= extras.getInt("Length");
		System.out.println(currentDifficulty);
		String[] options		= dc.readOption(currentDifficulty);
		
		waittime 				= Integer.parseInt(options[1]);
		incrementing 			= Integer.parseInt(options[2]);
		completionTime 			= Integer.parseInt(options[3]);
		patternTime				= Integer.parseInt(options[4]);
		speedInterval			= Integer.parseInt(options[5]);
		
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
		
		allButtons.add(button1);
		allButtons.add(button2);
		allButtons.add(button3);
		allButtons.add(button4);
		allButtons.add(button5);
		allButtons.add(button6);
		allButtons.add(button7);
		allButtons.add(button8);
		allButtons.add(button9);
		

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
		
		if(mode == 1){
			disableButtons();
			goAI();
		}
	}
/*
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Button1:
			if(state == 1){
				list.add(1);
				bList.add(button1);
			}
			else{
				if(list.get(current) == 1)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button2:
			if(state == 1){
				list.add(2);
				bList.add(button2);
			}
			else{
				if(list.get(current) == 2)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button3:
			if(state == 1){
				list.add(3);
				bList.add(button3);
			}
			else{
				if(list.get(current) == 3)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button4:
			if(state == 1){
				list.add(4);
				bList.add(button4);
			}
			else{
				if(list.get(current) == 4)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button5:
			if(state == 1){
				list.add(5);
				bList.add(button5);
			}
			else{
				if(list.get(current) == 5)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button6:
			if(state == 1){
				list.add(6);
				bList.add(button6);
			}
			else{
				if(list.get(current) == 6)
					current++;
				else{
					tm.cancel();
					checkHighscore();
				}
			}
			break;
		case R.id.Button7:
			if(state == 1){
				list.add(7);
				bList.add(button7);
			}
			else{
				if(list.get(current) == 7)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button8:
			if(state == 1){
				list.add(8);
				bList.add(button8);
			}
			else{
				if(list.get(current) == 8)
					current++;
				else{
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;
		case R.id.Button9:
			if(state == 1){
				list.add(9);
				bList.add(button9);
			}
			else{
				if(list.get(current) == 9)
					current++;
				else{
					
					tm.cancel();
					checkHighscore();
					
				}
			}
			break;

		case R.id.Button10:
			
			showDialog(START);
			
			



		}

		if(state == 1 && list.size() == currentLength){ 
			disableButtons();
		}
		if(state == 2 && current == list.size()){
			tm.cancel();
			if(mode == 1)
				showDialog(WELL_DONE_SINGLEPLAYER);
			else
				showDialog(WELL_DONE_MULTIPLAYER);


			reset(); 


		}
		System.out.println(current + ", " + list.size() + "..." + currentLength + ", " + state);

	}
	*/
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
			
		case R.id.Button10:
			showDialog(START);
			break;
			
		default:
			if(state == 1){
				bList.add((ImageButton) this.findViewById(v.getId()));
			}
			else{
				if (bList.get(current) == this.findViewById(v.getId())){
					current++;
				}
				else{
					tm.cancel();
					checkHighscore();
				}
				
			}
			break;
		}
		if(state == 1 && list.size() == currentLength){ 
			disableButtons();
		}
		if(state == 2 && current == list.size()){
			tm.cancel();
			if(mode == 1)
				showDialog(WELL_DONE_SINGLEPLAYER);
			else
				showDialog(WELL_DONE_MULTIPLAYER);


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
		bList = new ArrayList<ImageButton>();
		state = 1;
		current = 0;
		showLength= 0;
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
			.setMessage("YOU LOST")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					//lösklödka
					
					Game_View.this.finish();
				}
			})
			.create();	


		case WELL_DONE_MULTIPLAYER:
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
			
		case WELL_DONE_SINGLEPLAYER:
			return new AlertDialog.Builder(this)
			.setMessage("WELL DONE! \n Next turn coming up")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					currentLength = currentLength + incrementing;
					goAI();
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
					button10.setEnabled(false);
					state = 2;
					dialog.cancel();
					show();
				}

			} ).setNegativeButton("No, I want to save", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					startSavegame(1);
					
				}
			}).create();
			
			
		case HIGHSCORE:
			LayoutInflater li = LayoutInflater.from(this);
			View categoryDetailView = li.inflate(R.layout.category_detail, null);

			AlertDialog.Builder categoryDetailBuilder = new AlertDialog.Builder(this);
			categoryDetailBuilder.setTitle("Congratz! You have earned a spot on the Highscore! \n" +
					"Please enter your name.");
			categoryDetailBuilder.setView(categoryDetailView);
			AlertDialog categoryDetail = categoryDetailBuilder.create();

			categoryDetail.setButton("To Glory!", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					AlertDialog categoryDetail = (AlertDialog)dialog;
					EditText et = (EditText)categoryDetail.findViewById(R.id.categoryEditText);
					//shs = new StoreHighScore(dbHelp);
					dc.storeHighscore(et.getText().toString()+","+score);
					Intent intent = new Intent(Game_View.this, Scoreboard.class);
					Game_View.this.startActivity(intent);
					Game_View.this.finish();
				}
			});
			return categoryDetail;
		
		 
	case SAVE_GAME:
		LayoutInflater lin = LayoutInflater.from(this);
		View categoryDetailView2 = lin.inflate(R.layout.category_detail, null);

		AlertDialog.Builder categoryDetailBuilder2 = new AlertDialog.Builder(this);
		categoryDetailBuilder2.setTitle("Please enter the name of your save.");
		categoryDetailBuilder2.setView(categoryDetailView2);
		AlertDialog categoryDetail2 = categoryDetailBuilder2.create();

		categoryDetail2.setButton(DialogInterface.BUTTON_POSITIVE,"Save!", new DialogInterface.OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				AlertDialog categoryDetail = (AlertDialog)dialog;
				EditText et = (EditText)categoryDetail.findViewById(R.id.categoryEditText);
				if(dc.storeSave(et.getText().toString() +"," +currentDifficulty + "," +currentLength + "," +mode))
					Game_View.this.finish();
				else
					startSavegame(3);
			}
		});
		categoryDetail2.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				startSavegame(2);
			}
		});
		return categoryDetail2;
		
	case OVERWRITE:
		return new AlertDialog.Builder(this)
		.setMessage("Save-name exists. Do you want to overwrite?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dc.overwriteSave();		
				Game_View.this.finish(); 
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				startSavegame(1);
			}
		})
		.create();
		
	case DO_YOU_WANT_TO_QUIT:
		return new AlertDialog.Builder(this)
		.setMessage("Do you wish to quit?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				tm.cancel();
				Game_View.this.finish();
			}

		} ).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}).create();
		
	default:
		break;
	}

		return null;

	}


	public void show(){
		if(showLength != bList.size() ){
			if(showLength == 0)
				mRefreshShow.sleep(500 + 500);
			else{
				mRefreshShow.sleep(patternTime);
			}
			newbutton = changeButton();
			while (newbutton == lastbutton)
				newbutton = changeButton();
			bList.get(showLength).setImageResource(imglist[newbutton]);
			lastbutton = newbutton;
			
		}
		else{
			tm = new CountDownTimer(waittime, 1000) {

				public void onTick(long millisUntilFinished) {
					timeToStart.setText("Seconds untill you may start: "+ millisUntilFinished / 1000);
				}

				public void onFinish() {
					state = 2;
					enableButtons();
					timeToStart.setText("");
					tm = new CountDownTimer(completionTime * bList.size(), 1000) {

						public void onTick(long millisUntilFinished) {
							
							countdown.setText(""+ millisUntilFinished / 1000);
						}

						public void onFinish() {
							countdown.setText("1");
							countdown.setText("You Lost!");
							showDialog(YOU_LOST);

						}
					}.start();

				}
			}.start();
		}
	}
	
	public void goAI(){ 
		Random r = new Random();
		for(int i = 0; i < currentLength; i++){
			int next = r.nextInt(8);
			bList.add(allButtons.get(next));
			list.add(next+1);
		}
		showDialog(START);
	}
	
	public void checkHighscore(){
		if(mode == 2){
			showDialog(YOU_LOST);
		}
		else{
			String[][] highscore = dc.readHighscore();
			int points 	= 1;		 
			Integer i 	= Integer.parseInt(highscore[highscore.length-1][points]);
			calculateScore();
			System.out.println("\n" + score + "\n");
			if(i < score || highscore.length < 5){
				showDialog(HIGHSCORE);
			} 
			else{
				showDialog(YOU_LOST);
			}
		}
	}
	
	public void startSavegame(int i){
		if(i == 1)
			showDialog(SAVE_GAME); 
		else if(i ==2)
			showDialog(START);
		else
			showDialog(OVERWRITE);
	}	
	private int changeButton() {
		Random rand = new Random();
		return (rand.nextInt(8));
	}
	
	@Override
	public void onBackPressed() {
		
	    showDialog(DO_YOU_WANT_TO_QUIT);
	    
	}

	
	private void calculateScore(){ 
		System.out.println(currentLength + ", " + waittime);
		score = maxScore/(100/currentLength)/(1000/(waittime/1000));
		
	}



	private class RefreshShow extends Handler {

		@Override
		public void handleMessage(Message m){
			bList.get(showLength).setImageResource(normalbutton);
			showLength++;
			Game_View.this.show();

		}


		public void sleep(long delayMillis) {  
		    this.removeMessages(0);  
		    sendMessageDelayed(obtainMessage(0), delayMillis);  
		} 
	};
		
}



