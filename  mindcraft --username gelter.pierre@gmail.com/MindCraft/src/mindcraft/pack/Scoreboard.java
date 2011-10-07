package mindcraft.pack;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Scoreboard extends Activity implements OnClickListener {
	
	private TextView highscoreName;
	private TextView highscoreScore;
	DbHelper helper;
    ReadHighScore rs;
    ImageButton scoreBack;
		
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		setContentView(R.layout.scoreboard);
		this.highscoreName = (TextView) this.findViewById(R.id.firstName);
		this.highscoreScore = (TextView) this.findViewById(R.id.firstScore);
		this.scoreBack = (ImageButton) this.findViewById(R.id.scoreBackButton);
		this.scoreBack.setOnClickListener(this);
		
		//System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		
		//highscore.setText("Derp");
		
			 
		this.helper = new DbHelper(this);
		final SQLiteDatabase db = this.helper.getWritableDatabase();
		this.rs = new ReadHighScore(this.helper);
		Cursor cursor = this.rs.read();
		cursor.moveToFirst();
		System.out.println(cursor.getCount());
		for(int i = 0; i < cursor.getCount(); i++){
			int t = cursor.getColumnIndex(DbHelper.C_NAME);
			int z = cursor.getColumnIndex(DbHelper.C_POINTS);
			this.highscoreName.setText(cursor.getString(t), TextView.BufferType.EDITABLE );
			this.highscoreScore.setText(cursor.getString(z), TextView.BufferType.EDITABLE );
			//show.setText(cursor.getCount()+"", EditText.BufferType.EDITABLE );
			cursor.moveToNext();
			
		}
		db.close();
	}



	@Override
	public void onClick(View arg0) {
		Scoreboard.this.finish();
		
	}
}
