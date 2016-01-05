package mindcraft.pack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReadSaveGame {
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public ReadSaveGame(DbHelper helper, SQLiteDatabase db){
		this.helper = helper;
		this.db 	= db;
	}
	
	public Cursor readAll(){
		db = helper.getReadableDatabase();
		String sql = "SELECT * FROM "+DbHelper.TABLE3 +";";
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
	public Cursor readOption(String saveName){
		db = helper.getReadableDatabase();
		String sql = "SELECT * FROM "+DbHelper.TABLE3 +" where "+ DbHelper.C_NAME_SAVE + " =? " + saveName+";";
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return null;
	}
}
