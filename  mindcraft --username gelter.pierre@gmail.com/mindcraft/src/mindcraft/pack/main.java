package mindcraft.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class main extends Activity {
    /** Called when the activity is first created. */
	private ImageButton newButton;
	private ImageButton loadButton;
	private ImageButton highscoreButton;
	private ImageButton optionsButton;
	private ImageButton aboutButton;
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
              //main.this.setContentView(R.layout.scoreboard);
             }
           });
         this.aboutButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
              //main.this.setContentView(R.layout.scoreboard);
             }
           });
    }
}