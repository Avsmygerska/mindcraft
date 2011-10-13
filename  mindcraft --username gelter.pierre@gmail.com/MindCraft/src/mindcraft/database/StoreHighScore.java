package mindcraft.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class StoreHighScore {

	//private DbHelper helper;
	//private SQLiteDatabase db;

	public StoreHighScore(){
		//this.helper = helper;
		
	}
	/*
	Method storeHighscore opens the database from a writable perspective, and writes down all
	the highscores into the database.
 */
	public boolean storeHighscore(String text, SQLiteDatabase db){

		// Perform action on key press
		String[] data = text.split(",");
		String sql = "SELECT * FROM "+DbHelper.TABLE +" order by " + DbHelper.C_POINTS + " desc limit 0,5;";
		try{
			//db = helper.getWritableDatabase();
			Cursor cursor = db.rawQuery(sql, new String []{});
			cursor.moveToLast();
			int t = cursor.getColumnIndex(DbHelper.C_POINTS);
			System.out.println(cursor.getCount());
			if(cursor.getCount() < 5 || Integer.parseInt(cursor.getString(t)) < Integer.parseInt(data[1])){
				if(cursor.getCount() > 5){
					cursor.moveToPrevious();
					t = cursor.getColumnIndex(DbHelper.C_POINTS);
					String [] pew = {cursor.getString(t)};
					db.delete(DbHelper.TABLE,DbHelper.C_POINTS+ " <? ", pew);
					System.out.println("DELETAT");
				}
				ContentValues values = new ContentValues();
				System.out.println(data[0] + ", " + data[1]);
				values.put(DbHelper.C_NAME, data[0]);
				values.put(DbHelper.C_POINTS, data[1]);
				
				db.insertWithOnConflict(DbHelper.TABLE, null, values,
						SQLiteDatabase.CONFLICT_REPLACE);
				
				return true;

			}
			
		}catch(Exception e){
			
			//e.printStackTrace();
		}
	return false;


}

}
