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


/*
			This is the class which controls everything 
				that happens on the game board
 																	*/

public class Game_View extends Activity implements OnClickListener{
	

	// An arraylist which allocates which buttons that have been pressed in the pattern
	private ArrayList<ImageButton> bList = new ArrayList<ImageButton>();
	
	// An arraylist which contains all buttons and helps the AI create a pattern
	private ArrayList<ImageButton> allButtons = new ArrayList<ImageButton>();
	
	// This array contains all the different button-styles (in this case, different color).
	private Integer [] imglist = {R.drawable.button1,R.drawable.button2,
				R.drawable.button3,R.drawable.button4,R.drawable.button5,
				R.drawable.button6,R.drawable.button7,R.drawable.button8,
				R.drawable.button9};
	
	// All the buttons on the game board
	private ImageButton button1,button2,button3,button4,button5,
								button6,button7,button8,button9;
	private Button button10, button11;
	
	//A Handler subclass
	private RefreshShow mRefreshShow = new RefreshShow();
	
	// The selected difficulty
	private String currentDifficulty;
	private TextView countdown, timeToStart;
	private CountDownTimer tm;
	
	// Constants that are used with the dialog-windows. 
	private static final int YOU_LOST = 1; 
	private static final int WELL_DONE_MULTIPLAYER = 2;
	private static final int WELL_DONE_SINGLEPLAYER = 3;
	private static final int START = 4;
	private static final int HIGHSCORE = 5;
	private static final int SAVE_GAME = 6;
	private static final int OVERWRITE = 7; 
	private static final int DO_YOU_WANT_TO_QUIT = 8;
	
	// A lot of extra ints
	private int state = 1;
	private int current = 0;
	private int currentLength;
	private int normalbutton = R.drawable.buttonselector;
	private int lastbutton = 0;
	private int newbutton;
	private Integer maxScore = 1000000;
	private Integer score;
	private int showLength = 0;
	private int incrementing;
	private int completionTime;
	private int waittime;
	private int patternTime;
	private int speedInterval;
	private int mode;					// Distinguishes if multi-player(2) or single-player(1) mode is selected. 
	
	//The link to the database
	private DatabaseController dc;

	
	
	
	//Instantiation of all buttons etc. 
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
	 	If a button is pressed, this method will be called. It also checks if
	 	the player has pressed the correct button in pattern.   				
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
		if(state == 1 && bList.size() == currentLength){ 
			disableButtons();
		}
		if(state == 2 && current == bList.size()){
			tm.cancel();
			if(mode == 1)
				showDialog(WELL_DONE_SINGLEPLAYER);
			else
				showDialog(WELL_DONE_MULTIPLAYER);


			reset(); 


		}
	}
	 
	// A method to disable the buttons which create a pattern and enable the "done"-button
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
	// A method to enable the buttons which create a pattern and disable the "done"-button
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
	
	// A method which resets all the states which affect the game
	public void reset(){
		bList = new ArrayList<ImageButton>();
		state = 1;
		current = 0;
		showLength= 0;
		countdown.setText("");
		enableButtons();
		button10.setVisibility(View.VISIBLE);
		button11.setVisibility(View.GONE);

	}
	
	
	/* Creates dialogs which either asks the user to type in information, if
	 * they are ready to see the pattern or similar.
	 * 
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case YOU_LOST:
			
			// If a player has pressed the wrong sequence of the shown pattern, this
			// dialog will be show. The player can only press a button "ok" from here.
			
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
			
			// If a player have completed a pattern in multiplayer mode, this dialog
			// will be shown. It also increments the sequence of the next pattern
			
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
			
			// If a player have completed a pattern in singleplayer mode, this dialog
			// will be shown. It also increments the sequence of the next pattern
			
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
			
			// Asks the player if he/she is ready to see the pattern that the opponent have
			// created. From here, the player can either press yes or press the save button.
			// However, the pattern which is created will not be saved. Just the length of the pattern
			// and the difficulty.
			
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
					saveGameController(1);
					
				}
			}).create();
			
			
		case HIGHSCORE:
			
			// When a game is over and a player has managed to get enough points to earn
			// a spot on the Highscore, this dialog will be show. Here the player will enter a
			// name, press "ok"-button and then he or she will be directed to the Highscore table
			// to bask in the glory of victory! :)
			
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
		
		// If the player has pressed the "save"-button in the START-dialog. This is where he will
		// be directed. Here he can enter a name for the save and then press save or press cancel and
		// be redirected back to the START-dialog.
		
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
					saveGameController(3);
			}
		});
		categoryDetail2.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				saveGameController(2);
			}
		});
		return categoryDetail2;
		
	case OVERWRITE:
			
		// If the name that the player have entered in the SAVE_GAME-dialog then this dialog will be
		// shown that asks if the player wish to overwrite the previous save or return to SAVE_GAME-dialog
		// to type in a different save name.
		
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
				saveGameController(1);
			}
		})
		.create();
		
	case DO_YOU_WANT_TO_QUIT:
		
		// If the player have pressed the 'back'-button, this dialog will be shown. Which asks the player
		// if he wants to exit the current game. If yes, then he will be directed to the main menu. If no,
		// the game will continue.
		
		return new AlertDialog.Builder(this)
		.setMessage("Do you wish to quit?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				if(tm != null){
					tm.cancel();
				}
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

	/*	
	 *  This method shows the pattern. It calls the handler to sleep for x seconds (set in options),
	 *	randomly selects a button-style. if the button-style is not the same as the last one shown
	 *	it will set the selected style to the current button in the pattern. This is to make it easier
	 *	for the player to distinguish if a button has been pressed more then once in a rown in the pattern. 
	 *
	 *	This method will be called again from the handler until all buttons in the pattern have been shown.
	 *	When all buttons have been shown, two CountDownTimers will start. One that counts down the 
	 *	wait-time (set in options) and one for the time the player have to replicate the shown pattern. 
	 *	
	 */
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
	
	// A method to create a pattern for singleplayer mode. 
	public void goAI(){ 
		Random r = new Random();
		for(int i = 0; i < currentLength; i++){
			int next = r.nextInt(8);
			bList.add(allButtons.get(next));
			
		}
		showDialog(START);
	}
	
	// Checks if the score is enough to earn a spot on the Highscore. If so, then the HIGHSCORE-dialog will
	// be shown. If not, then the YOU_LOST-dialog will be shown.
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
	
	// This method helps to control the flow when a player is moving between dialogs when saving.
	// Had to implement this due moving between dialogs in onCreateDialog() seemed impossible.
	public void saveGameController(int i){
		if(i == 1)
			showDialog(SAVE_GAME); 
		else if(i ==2)
			showDialog(START);
		else
			showDialog(OVERWRITE);
	}	
	
	// Creates a random number (0-8) and returns it.
	private int changeButton() {
		Random rand = new Random();
		return (rand.nextInt(8));
	}
	
	// If the player presses the "back"-button on the phone, this method will run and open a
	// "DO_YOU_WANT_TO_QUIT"-dialog.
	@Override
	public void onBackPressed() {
		showDialog(DO_YOU_WANT_TO_QUIT);
	}

	// Algorithm to calculate the score.
	private void calculateScore(){ 
		System.out.println(currentLength + ", " + waittime);
		score = maxScore/(100/currentLength)/(1000/(waittime/1000));
		
	}


	// Subclass which handles the updates of button-styles.
	private class RefreshShow extends Handler {
		
		// Sets the button back to "normal"-style and calls the show method to change the next button
		// in the pattern.
		@Override
		public void handleMessage(Message m){
			bList.get(showLength).setImageResource(normalbutton);
			showLength++;
			Game_View.this.show();

		}

		// Removes the first message in the chain and makes the handler sleep for a while (set in options).
		public void sleep(long delayMillis) {  
		    this.removeMessages(0);  
		    sendMessageDelayed(obtainMessage(0), delayMillis);  
		} 
	};
		
}



