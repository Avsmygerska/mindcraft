package mindcraft.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseController{
	
	private static DatabaseController dc = null;
	private DbHelper dbHelp;
	private SQLiteDatabase db;
	private ReadHighScore rhs;
	private ReadOptions ro;
	private ReadSaveGame rsg;
	private StoreHighScore shs;
	private StoreOptions so;
	private StoreSaveGame ssg;
	
	
	
	private DatabaseController(Context context){
		this.dbHelp = new DbHelper(context);
		rhs = new ReadHighScore();
		ro = new ReadOptions();
		rsg = new ReadSaveGame();
		shs = new StoreHighScore();
		so = new StoreOptions();
		ssg = new StoreSaveGame();
	}
	
	public static DatabaseController initialize(Context context){
		if(dc == null){
			dc = new DatabaseController(context);
		}
		return dc;
	}
	public static DatabaseController initialize(){
		if(dc == null){
			return null;
		}
		return dc;
	}
	
	
	public String[][] readHighscore(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rhs.read(db);
		cursor.moveToFirst();
		String[][] highscore = new String[cursor.getCount()][cursor.getColumnCount()];
		for (int i = 0; i < cursor.getCount(); i++) {
			highscore[i][0] = cursor.getString(0);
			highscore[i][1] = cursor.getString(1);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return highscore;
	}
	
	public String[] readOption(String text){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = ro.readOption(text, db);
		cursor.moveToFirst();
		String [] option = new String[cursor.getColumnCount()];
		for(int i = 0; i< cursor.getColumnCount(); i++){
			option[i] = cursor.getString(i);
		}
		cursor.close();
		db.close();
		return option;
	}
	
	public String[] readAllOptions(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = ro.readAllOptions(db);
		cursor.moveToFirst();
		String[] options = new String[cursor.getCount()];
		for(int i = 0; i < cursor.getCount(); i++){ 
			int a = cursor.getColumnIndex(DbHelper.C_NAME_OPT);
			options[i] = cursor.getString(a);
			cursor.moveToNext();

		}
		cursor.close();
		db.close();
		return options;
	}
	
	public String[] readSave(String name){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rsg.readSave(name, db);
		cursor.moveToFirst();
		String[] save = new String[cursor.getColumnCount()];
		for(int i = 0; i < save.length; i++ ){
			save[i] = cursor.getString(i);
		}
		cursor.close();
		db.close();
		return save;
	}
	
	public String[][] readAllSaves(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rsg.readAllSaves(db);
		cursor.moveToFirst();
		String[][] saves = new String[cursor.getCount()][cursor.getColumnCount()];
		int name 		= cursor.getColumnIndex(DbHelper.C_NAME_SAVE);
		int difficulty 	= cursor.getColumnIndex(DbHelper.C_DIFFICULTY);
		int level 		= cursor.getColumnIndex(DbHelper.C_LEVEL);
		int mode 		= cursor.getColumnIndex(DbHelper.C_MODE);
		for(int i = 0; i < cursor.getCount(); i++){
			saves[i][0] = cursor.getString(name);
			saves[i][1] = cursor.getString(difficulty);
			saves[i][2] = cursor.getString(level);
			saves[i][3] = cursor.getString(mode);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return saves;
	}
	
	public boolean storeHighscore(String text){
		db = dbHelp.getWritableDatabase();
		boolean store = shs.storeHighscore(text, db);
		db.close();
		return store;
	}
	
	public boolean storeOption(String[] data){
		db = dbHelp.getWritableDatabase();
		boolean store = so.store(data, db);
		db.close();
		return store;
		
	}
	public boolean deleteOption(String name){
		db = dbHelp.getWritableDatabase();
		boolean delete = so.delete(name, db);
		db.close();
		return delete;
	}
	
	public boolean updateOption(String[] data){
		db = dbHelp.getWritableDatabase();
		boolean update = so.update(data, db);
		db.close();
		return update;
	}
	public boolean storeSave(String text){
		db = dbHelp.getWritableDatabase();
		boolean store = ssg.store(text, db);
		db.close();
		return store;
	}
	public boolean overwriteSave(){
		db = dbHelp.getWritableDatabase();
		boolean overwrite = ssg.overWrite(db);
		db.close();
		return overwrite;
	}

}
