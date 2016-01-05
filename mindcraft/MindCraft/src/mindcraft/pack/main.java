package mindcraft.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class main extends Activity {
    /** Main creates the activity and controls all the
     *  buttons in the layout menus. The buttons/modules
     *  that doesn't take you between layouts is directed
     *  to respective controllers. */
	//
	//MainMenu buttons
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
	private Spinner P1CustomLevel;
	private Spinner P2CustomLevel;
	
	
	//LoadGame buttons
	private ImageButton resumeGameButton;
	
	//Options buttons
	
	//Generic buttons
	private ImageButton returnToMainButton;
	
	//CREATION
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Assigning modules
        //MainMenu
        this.newButton = (ImageButton)this.findViewById(R.id.newGameButton);
        this.loadButton = (ImageButton)this.findViewById(R.id.loadGameButton);
        this.highscoreButton = (ImageButton)this.findViewById(R.id.highScoresButton);
        this.optionsButton = (ImageButton)this.findViewById(R.id.optionsButton);
        this.aboutButton = (ImageButton)this.findViewById(R.id.aboutButton);
        
        //NewGame
        this.singlePButton = (ImageButton)this.findViewById(R.id.singlePButton);
        this.multiPButton = (ImageButton)this.findViewById(R.id.multiPButton);
        this.P1EasyButton = (ImageButton)this.findViewById(R.id.P1EasyButton);
        this.P2EasyButton = (ImageButton)this.findViewById(R.id.P2EasyButton);
        this.P1MediumButton = (ImageButton)this.findViewById(R.id.P1MediumButton);
        this.P2MediumButton = (ImageButton)this.findViewById(R.id.P2MediumButton);
        this.P1HardButton = (ImageButton)this.findViewById(R.id.P1HardButton);
        this.P2HardButton = (ImageButton)this.findViewById(R.id.P2HardButton);
        this.startNewGameButton = (ImageButton)this.findViewById(R.id.startNewGameButton);
        
        
        
        //Generic
        this.returnToMainButton = (ImageButton)this.findViewById(R.id.returnToMainButton);
        
        //METHODS
        //MainMenu methods
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
              main.this.setContentView(R.layout.scoreboard);
             }
           });
         this.optionsButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              main.this.setContentView(R.layout.options);
             }
           });
         this.aboutButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.about);
             }
           });
         
         /*this.singlePButton.setOnClickListener(new OnClickListener() { FÅR INTE DESSA FUNKTIONER ATT FUNGERA!
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.about);
             }
           });*/
         
         //Generic methods
        /*this.returnToMainButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              main.this.setContentView(R.layout.main);
             }
           });*/
    }
}