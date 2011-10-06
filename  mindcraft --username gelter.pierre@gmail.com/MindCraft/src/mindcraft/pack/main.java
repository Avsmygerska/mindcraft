package mindcraft.pack;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class main extends Activity {
	
	final Bundle savedInstanceState = null;
	
	DbHelper dbhelp;
	Scoreboard score;
	
    /** Called when the activity is first created. */
	//MainMenu buttons.
	private ImageButton newButton;
	private ImageButton loadButton;
	private ImageButton highscoreButton;
	private ImageButton optionsButton;
	private ImageButton aboutButton;
	
	//NewGame buttons
	private ImageButton singlePButton;
	private ImageButton multiPButton;
	private ImageButton startNewGameButton;
	
	//LoadGame buttons
	private ImageButton resumeGameButton;
	
	//Options buttons
	
	//Generic buttons
	private ImageButton returnToMain;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		//his.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
                        
        dbhelp = new DbHelper(main.this); 
        //final SQLiteDatabase db = dbhelp.getReadableDatabase();
        //db.close();
        
        score = new Scoreboard();
        
        this.newButton = (ImageButton)this.findViewById(R.id.newGameButton);
        this.loadButton = (ImageButton)this.findViewById(R.id.loadGameButton);
        this.highscoreButton = (ImageButton)this.findViewById(R.id.highScoresButton);
        this.optionsButton = (ImageButton)this.findViewById(R.id.optionsButton);
        this.aboutButton = (ImageButton)this.findViewById(R.id.aboutButton);
        
        this.newButton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) { 
           //main.this.setContentView(R.layout.newgame);
        	 try{
         		 Intent intent = new Intent(getBaseContext(), New_Game.class);
         		 main.this.startActivity(intent);
         		 //score.highscore(dbhelp);
         	 }catch(Exception e){
         		 e.printStackTrace(); 
         	 }
          }
        });
         this.loadButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 Intent intent = new Intent(getBaseContext(), Load_Game.class);
            	 main.this.startActivity(intent);
               //main.this.setContentView(R.layout.loadgame);
             }
           });
         this.highscoreButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 //main.this.setContentView(R.layout.scoreboard);
            	 try{
            		 Intent intent = new Intent(getBaseContext(), Scoreboard.class);
            		 main.this.startActivity(intent);
            		 //score.highscore(dbhelp);
            	 }catch(Exception e){
            		 e.printStackTrace();
            	 }
            	 
             }
           });
         this.optionsButton.setOnClickListener(new OnClickListener() {
             //private Bundle savedInstanceState2 = savedInstanceState;

			@Override
             public void onClick(View v) {
            	 Intent intent = new Intent(getBaseContext(), Options.class);
            	 main.this.startActivity(intent);
            	//score.onCreate(this.savedInstanceState );
            	 
              //main.this.setContentView(R.layout.options);
             }
           });
         this.aboutButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.about);
             }
           });
    }
	
	
}