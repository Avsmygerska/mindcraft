package mindcraft.pack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ReadHighScore {

	

	private DbHelper helper;
	private SQLiteDatabase db;

	public ReadHighScore(DbHelper helper){
		this.helper = helper;
		//this.db 	= db;
	}
	
	
	public Cursor read(){
		
		System.out.println(helper);
		
		db = helper.getReadableDatabase();
		
		String sql = "SELECT * FROM "+DbHelper.TABLE +" order by " + DbHelper.C_POINTS + " desc limit 0,5;";
		/*String sql = "SELECT * FROM " + 
			  			DbHelper.TABLE +  
					  " order by " + 
					  DbHelper.C_POINTS + " desc;";
		*/
		db = helper.getReadableDatabase();
		
		System.out.println("Här är jag nu!");
		try{
			Cursor cursor = db.rawQuery(sql, new String []{});
			
			return cursor;
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
