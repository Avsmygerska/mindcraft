package mindcraft.pack;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StoreOptions {

	private DbHelper helper;
	private SQLiteDatabase db;

	public StoreOptions(DbHelper helper){
		this.helper = helper;


	}

	public boolean store(String[]data){

		db = helper.getWritableDatabase();

		//String [] data = text.split(",");
		ContentValues values1 = new ContentValues();

		values1.put(DbHelper.C_NAME_OPT, data[0]);
		System.out.println(DbHelper.C_NAME_OPT + "," + data[0]);
		values1.put(DbHelper.C_INCREMENTING_DIFFICULTY,Integer.parseInt(data[1]));
		System.out.println(DbHelper.C_INCREMENTING_DIFFICULTY + ", " + Integer.parseInt(data[1]));
		values1.put(DbHelper.C_PATTERN_COMPLETION_TIME,Integer.parseInt(data[2]));
		System.out.println(DbHelper.C_PATTERN_COMPLETION_TIME + ", " + Integer.parseInt(data[2]));
		values1.put(DbHelper.C_WAITTIME, Integer.parseInt(data[3]));
		System.out.println(DbHelper.C_WAITTIME + ", " +  Integer.parseInt(data[3]));
		values1.put(DbHelper.C_SHOW_PATTERN_TIME, Integer.parseInt(data[4]));
		System.out.println(DbHelper.C_SHOW_PATTERN_TIME+ ", "+ Integer.parseInt(data[4]));
		values1.put(DbHelper.C_SPEED_INTERVAL_PATTERN, Integer.parseInt(data[5]));
		System.out.println(DbHelper.C_SPEED_INTERVAL_PATTERN + ", " + Integer.parseInt(data[5]));
		
		try{
			String sql = "select * from " + DbHelper.TABLE2 + " where "+ DbHelper.C_NAME_OPT +" = '"+ data[0]+"';" ; 
			db = helper.getWritableDatabase();
			Cursor cursor = db.rawQuery(sql, new String []{});
			if(cursor.getCount() == 0){
				db.insertWithOnConflict(DbHelper.TABLE2, null, values1,
						SQLiteDatabase.CONFLICT_REPLACE);

				return true;
			}

		}catch(Exception e){

			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String name){
		db = helper.getWritableDatabase(); 

		String [] s = {name};
		db.delete(DbHelper.TABLE2, DbHelper.C_NAME_OPT + " =? ", s );
		System.out.println("\n \n derp:" +  name + ": "+  "\n \n");		


		return true;


	}
	public boolean update(String[]data){
		db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DbHelper.C_INCREMENTING_DIFFICULTY, Integer.parseInt(data[1]));
		System.out.println(DbHelper.C_INCREMENTING_DIFFICULTY + ", " + Integer.parseInt(data[1]));
		cv.put(DbHelper.C_PATTERN_COMPLETION_TIME, Integer.parseInt(data[2]));
		System.out.println(DbHelper.C_PATTERN_COMPLETION_TIME + ", " + Integer.parseInt(data[2]));
		cv.put(DbHelper.C_WAITTIME,Integer.parseInt(data[3]));
		System.out.println(DbHelper.C_PATTERN_COMPLETION_TIME + ", " +  Integer.parseInt(data[3]));
		cv.put(DbHelper.C_SHOW_PATTERN_TIME,Integer.parseInt(data[4]));
		System.out.println(DbHelper.C_SHOW_PATTERN_TIME+ ", "+ Integer.parseInt(data[4]));
		cv.put(DbHelper.C_SPEED_INTERVAL_PATTERN, Integer.parseInt(data[5]));
		System.out.println(DbHelper.C_SPEED_INTERVAL_PATTERN + ", " + Integer.parseInt(data[5]));

		String[] temp = {data[0]};

		db.update(DbHelper.TABLE2, cv, DbHelper.C_NAME_OPT + " =? ",  temp);


		return true;
	}
}
