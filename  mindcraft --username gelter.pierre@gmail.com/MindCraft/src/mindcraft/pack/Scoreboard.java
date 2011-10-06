package mindcraft.pack;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Scoreboard extends Activity{
	
	private TextView highscore; 
	DbHelper helper;
    ReadHighScore rs;
		
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		setContentView(R.layout.scoreboard);
		this.highscore = (TextView) this.findViewById(R.id.firstName);
		
		//System.out.println("\n \n \n \n \n derp \n \n \n \n \n \n");
		
		//highscore.setText("Derp");
		
			
		helper = new DbHelper(this);
		final SQLiteDatabase db = helper.getWritableDatabase();
		rs = new ReadHighScore(helper);
		Cursor cursor = rs.read();
		cursor.moveToFirst();
		System.out.println(cursor.getCount());
		for(int i = 0; i < cursor.getCount(); i++){
			int t = cursor.getColumnIndex(DbHelper.C_NAME);
			int z = cursor.getColumnIndex(DbHelper.C_POINTS);
			if(i == 0){
				highscore.setText(cursor.getString(t) + ", " + cursor.getString(z) + " \n", TextView.BufferType.EDITABLE );
			}
			else
				highscore.setText(highscore.getText() + cursor.getString(t) + ", " + cursor.getString(z) + " \n", TextView.BufferType.EDITABLE );
			//show.setText(cursor.getCount()+"", EditText.BufferType.EDITABLE );
			cursor.moveToNext();
			
		}
		db.close();
	}
}
