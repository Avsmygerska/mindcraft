package mindcraft.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class main extends Activity {
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
	private ImageButton P1EasyButton;
	private ImageButton P2EasyButton;
	private ImageButton P1MediumButton;
	private ImageButton P2MediumButton;
	private ImageButton P1HardButton;
	private ImageButton P2HardButton;
	private ImageButton startNewGameButton;
	
	
	//LoadGame buttons
	private ImageButton resumeGameButton;
	
	//Options buttons
	
	//Generic buttons
	private ImageButton returnToMain;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.newButton = (ImageButton)this.findViewById(R.id.newGameButton);
        this.loadButton = (ImageButton)this.findViewById(R.id.loadGameButton);
        this.highscoreButton = (ImageButton)this.findViewById(R.id.highScoresButton);
        this.optionsButton = (ImageButton)this.findViewById(R.id.optionsButton);
        this.aboutButton = (ImageButton)this.findViewById(R.id.aboutButton);
        
         this.newButton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
           main.this.setContentView(R.layout.newgame);
          }
        });
         this.loadButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.loadgame);
             }
           });
         this.highscoreButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.scoreboard);
             }
           });
         this.optionsButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
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