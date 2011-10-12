package mindcraft.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class ReadSaveGame {
	//private DbHelper helper;
	//private SQLiteDatabase db;
	
	public ReadSaveGame(){
		//this.helper = helper;
	}
	
	public Cursor readAllSaves(SQLiteDatabase db){
		//db = helper.getReadableDatabase();
		String sql = "SELECT * FROM "+DbHelper.TABLE3 +";";
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
		}catch(Exception e){
			//e.printStackTrace();
		}
		return null;
		
	}
	
	 /*
		Method readOption opens the database from a readable perspective, creates a cursor and fill 
		it with the data matching the name of the option
	 */
	public Cursor readSave(String saveName, SQLiteDatabase db){
		//db = helper.getReadableDatabase();
		String sql = "SELECT * FROM "+DbHelper.TABLE3 +" where "+ DbHelper.C_NAME_SAVE + " = '" + saveName+"';";
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			
			
			return cursor;
		}catch(Exception e){
			
			//e.printStackTrace();
		}
		return null;
	}
}
