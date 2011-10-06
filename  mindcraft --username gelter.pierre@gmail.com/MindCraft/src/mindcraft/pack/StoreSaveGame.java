package mindcraft.pack;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StoreSaveGame {
	private DbHelper helper;
	private SQLiteDatabase db;
	private TestDBActivity tdba;
	private String holder;

	public StoreSaveGame(DbHelper helper, SQLiteDatabase db, TestDBActivity tdba){
		this.helper = helper;
		this.db 	= db;
		this.tdba 	= tdba;
	}

	public boolean store(String text){

		String [] data = text.split(",");
		ContentValues values1 = new ContentValues();

		try{
			values1.put(DbHelper.C_NAME_SAVE, data[0]);
			values1.put(DbHelper.C_DIFFICULTY, data[1]);
			values1.put(DbHelper.C_LEVEL, Integer.parseInt(data[2]));
			
			String sql = "select * from " + DbHelper.TABLE3 + " where "+ DbHelper.C_NAME_OPT +" = '"+ data[0]+"';" ; 
			db = helper.getWritableDatabase();
			Cursor cursor = db.rawQuery(sql, new String []{});
			System.out.println(cursor.getCount());
			if(cursor.getCount() == 0){
				db.insertWithOnConflict(DbHelper.TABLE3, null, values1,
						SQLiteDatabase.CONFLICT_REPLACE);

				return true;
			}
			else{
				holder = text;
				//tdba.showDialog(TestDBActivity.DO_YOU_WANT);
				
			}

		}catch(Exception e){

			e.printStackTrace();
		}
		return false;
	}
	
	public boolean overWrite(){
		String [] data = holder.split(",");
		ContentValues values1 = new ContentValues();

		try{
			values1.put(DbHelper.C_NAME_SAVE, data[0]);
			values1.put(DbHelper.C_DIFFICULTY, data[1]);
			values1.put(DbHelper.C_LEVEL, Integer.parseInt(data[2]));
			 
			db = helper.getWritableDatabase();
			db.insertWithOnConflict(DbHelper.TABLE3, null, values1,
					SQLiteDatabase.CONFLICT_REPLACE);
						
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
