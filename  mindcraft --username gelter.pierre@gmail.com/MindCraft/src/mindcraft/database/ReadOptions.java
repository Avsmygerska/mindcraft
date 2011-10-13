package mindcraft.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class ReadOptions {
	
	public ReadOptions(){
		//this.helper = helper;	
	}

	/*
	Method readOption opens the database from a readable perspective, creates a cursor and fill 
	it with all the option data.
	*/
	public Cursor readAllOptions(SQLiteDatabase db){
		try{
			//if(db == null)
				//db = helper.getReadableDatabase();
			
			String sql = "SELECT NAME FROM "+DbHelper.TABLE2 +";";
		
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
	public Cursor readOption(String optionName, SQLiteDatabase db){
		try{
			//if(db == null)
				//db = helper.getReadableDatabase();
		
			String sql = "SELECT * FROM "+DbHelper.TABLE2 +" where "+ DbHelper.C_NAME_OPT + " = '" + optionName+"';";
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
		}catch(Exception e){
			
			//e.printStackTrace();
		}
		return null;
	}
}
