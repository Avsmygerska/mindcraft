package mindcraft.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

class DbHelper extends SQLiteOpenHelper {
  private static final String TAG = DbHelper.class.getSimpleName();
  
  // Highscore variables
  public static final String DB_NAME = "mindcraft.db";
  public static final int DB_VERSION = 20;
  public static final String TABLE = "Highscore";
  public static final String C_ID = BaseColumns._ID; // Special for id
  public static final String C_NAME = "Name";
  public static final String C_POINTS = "Points";
  
  // Options variables 
  public static final String TABLE2 = "Options";  
  public static final String C_NAME_OPT = "Name"; // 
  public static final String C_WAITTIME = "Waittime"; //5sec - 180 sec
  public static final String C_INCREMENTING_DIFFICULTY = "Incrimenting_Difficulty"; //1-6
  public static final String C_PATTERN_COMPLETION_TIME = "Pattern_Completion_Time"; //0,5-3.0 sec * antal
  public static final String C_SHOW_PATTERN_TIME = "Showing_Time_of_Pattern"; //0.5 - 3.0 sec 
  public static final String C_SPEED_INTERVAL_PATTERN = "Speed"; //0% - 50% 
  
  // Save Game variables
  public static final String TABLE3 		= "Saved_Games";
  public static final String C_NAME_SAVE	= "Name";
  public static final String C_LEVEL 		= "Level";
  public static final String C_DIFFICULTY 	= "Difficulty";
  public static final String C_MODE 		= "Mode";
  
  Context context;

  public DbHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
	  // SQL query to create Highscore table
	  String sql_Highscore = String.format(
			  			"create table %s (%s TEXT primary key, %s INT)",
			  			 TABLE, C_NAME, C_POINTS 
			  			 );

	  // SQL query to create Options table
	  String sql_Options = String.format(
			  			"create table %s (%s TEXT primary key, %s INT,%s INT,%s INT,%s INT,%s INT)",
			  			 TABLE2, C_NAME_OPT, C_WAITTIME, C_INCREMENTING_DIFFICULTY,
			  			 C_PATTERN_COMPLETION_TIME, C_SHOW_PATTERN_TIME, C_SPEED_INTERVAL_PATTERN
			  			 );
	  
	  // SQL query to create Save_Game table
	  String sql_Save_Game = String.format(
			  			"create table %s (%s TEXT primary key, %s TEXT, %s INT, %s INT)",
			  			 TABLE3, C_NAME_SAVE ,C_DIFFICULTY, C_LEVEL, C_MODE 
			  			 );
	  
	  Log.d(TAG, "onCreate sql: \n"+sql_Highscore +"\n" + sql_Options + "\n" + sql_Save_Game);
	  
	  //db = this.getWritableDatabase();
	  db.execSQL(sql_Highscore);
	  db.execSQL(sql_Options);
	  db.execSQL(sql_Save_Game);
	  testHighscoreStart(db);
	  testOptionsStart(db);
	  
  }
  
  public boolean testHighscoreStart(SQLiteDatabase db){
	  ContentValues values = new ContentValues();
	  values.put(DbHelper.C_NAME, "Christian");
	  values.put(DbHelper.C_POINTS, 2000);
	  db.insertWithOnConflict(DbHelper.TABLE, null, values,
				SQLiteDatabase.CONFLICT_REPLACE);
	  return true;
  }
  
  public boolean testOptionsStart(SQLiteDatabase db){
	  ContentValues values = new ContentValues();
	  values.put(DbHelper.C_NAME_OPT, "Default");
	  values.put(DbHelper.C_WAITTIME, 5000);
	  values.put(DbHelper.C_INCREMENTING_DIFFICULTY, 1);
	  values.put(DbHelper.C_PATTERN_COMPLETION_TIME, 2000);
	  values.put(DbHelper.C_SHOW_PATTERN_TIME, 1000);
	  values.put(DbHelper.C_SPEED_INTERVAL_PATTERN, 0);
	  db.insertWithOnConflict(DbHelper.TABLE2, null, values,
				SQLiteDatabase.CONFLICT_REPLACE);
	  return true;
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Typically you do ALTER TABLE... here
    db.execSQL("drop table if exists " + TABLE);
    db.execSQL("drop table if exists " + TABLE2);
    db.execSQL("drop table if exists " + TABLE3);
    Log.d(TAG, "onUpdate dropped table "+TABLE);
    this.onCreate(db);
  }
  
 
}
