package mindcraft.pack;

import mindcraft.database.DatabaseController;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Scoreboard extends Activity implements OnClickListener{
	
	private TextView highscore; 
    ImageButton back;
    DatabaseController dc;
		
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		setContentView(R.layout.scoreboard);
		this.highscore = (TextView) this.findViewById(R.id.firstName);
		back = (ImageButton) this.findViewById(R.id.returnToMainButton);
		back.setOnClickListener(this);
		
		//System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		
		//highscore.setText("Derp");
		
			
		//helper = new DbHelper(this);
		//final SQLiteDatabase db = helper.getWritableDatabase();
		//rs = new ReadHighScore(helper);
		dc = DatabaseController.initialize();
		String[][] highscoreTable = dc.readHighscore();
				
		for(int i = 0; i < highscoreTable.length; i++){
			if(i == 0){
				String name  = highscoreTable[i][0];
				String score = highscoreTable[i][1]; 
				highscore.setText(name +", "+ score + " \n", TextView.BufferType.EDITABLE );
			}
			else{
				String name  = highscoreTable[i][0];
				String score = highscoreTable[i][1]; 
				highscore.setText(highscore.getText().toString() + name +", "+ score + " \n", TextView.BufferType.EDITABLE );
			}
		}
		
	}



	@Override
	public void onClick(View v) {
		
		Scoreboard.this.finish();
		
	}
}
