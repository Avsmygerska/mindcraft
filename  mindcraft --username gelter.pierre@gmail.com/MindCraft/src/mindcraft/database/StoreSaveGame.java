package mindcraft.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class StoreSaveGame {
	//private DbHelper helper;
	//private SQLiteDatabase db;
	
	private String holder;

	public StoreSaveGame(){
			
	}

	public boolean store(String text, SQLiteDatabase db){

		String [] data = text.split(",");
		ContentValues values1 = new ContentValues();

		try{
			values1.put(DbHelper.C_NAME_SAVE, data[0]);
			values1.put(DbHelper.C_DIFFICULTY, data[1]);
			values1.put(DbHelper.C_LEVEL, Integer.parseInt(data[2]));
			values1.put(DbHelper.C_MODE, Integer.parseInt(data[3]));
			
			String sql = "select * from " + DbHelper.TABLE3 + " where "+ DbHelper.C_NAME_OPT +" = '"+ data[0]+"';" ; 
			//db = helper.getWritableDatabase();
			Cursor cursor = db.rawQuery(sql, new String []{});
			System.out.println(cursor.getCount());
			if(cursor.getCount() == 0){
				db.insertWithOnConflict(DbHelper.TABLE3, null, values1,
						SQLiteDatabase.CONFLICT_REPLACE);

				return true;
			}
			else{
				holder = text;
				
				
			}

		}catch(Exception e){

			//e.printStackTrace();
		}
		db.close();
		return false;
	}
	
	public boolean overWrite(SQLiteDatabase db){
		String [] data = holder.split(",");
		ContentValues values1 = new ContentValues();

		try{ 
			values1.put(DbHelper.C_NAME_SAVE, data[0]);
			values1.put(DbHelper.C_DIFFICULTY, data[1]);
			values1.put(DbHelper.C_LEVEL, Integer.parseInt(data[2]));
			values1.put(DbHelper.C_MODE, Integer.parseInt(data[3]));
			 
			//db = helper.getWritableDatabase();
			db.insertWithOnConflict(DbHelper.TABLE3, null, values1,
					SQLiteDatabase.CONFLICT_REPLACE);
			//db.close();			
			return true;
		}catch(Exception e){
			//e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteAllWithSelectedDifficulty(String name, SQLiteDatabase db){
		try{
			//db = helper.getWritableDatabase(); 
			
			String [] s = {name};
			db.delete(DbHelper.TABLE3, DbHelper.C_DIFFICULTY + " =? ", s );
			System.out.println("\n \n derp:" +  name + ": "+  "\n \n");		
		}catch(Exception e){
			return false; 
		}
		
		return true;


	}
	
	
}
