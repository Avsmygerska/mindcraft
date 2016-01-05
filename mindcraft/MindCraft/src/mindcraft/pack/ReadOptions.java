package mindcraft.pack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReadOptions {
	

	private DbHelper helper;
	private SQLiteDatabase db;

	public ReadOptions(DbHelper helper){
		this.helper = helper;
		
	}

	/*
	Method readOption opens the database from a readable perspective, creates a cursor and fill 
	it with all the option data.
	*/
	public Cursor readAll(){
		db = helper.getReadableDatabase();
		String sql = "SELECT NAME FROM "+DbHelper.TABLE2 +";";
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	 /*
		Method readOption opens the database from a readable perspective, creates a cursor and fill 
		it with the data matching the name of the option
	 */
	public Cursor readOption(String optionName){
		db = helper.getReadableDatabase();
		String sql = "SELECT * FROM "+DbHelper.TABLE2 +" where "+ DbHelper.C_NAME_OPT + " = '" + optionName+"';";
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return null;
	}
}
