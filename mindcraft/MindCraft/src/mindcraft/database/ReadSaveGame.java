package mindcraft.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class ReadSaveGame {
	//private DbHelper helper;
	//private SQLiteDatabase db;
	
	public ReadSaveGame(){
		//this.helper = helper;
	}
	/*
	Method readAllSaves opens the database from a readable perspective, creates a cursor and fill 
	it with all the saves data.
	*/
	public Cursor readAllSaves(SQLiteDatabase db){
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
		Method readSave opens the database from a readable perspective, creates a cursor and fill 
		it with the data matching the name of the save
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
