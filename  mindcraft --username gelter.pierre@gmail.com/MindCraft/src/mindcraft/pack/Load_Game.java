package mindcraft.pack;

import java.util.ArrayList;

import mindcraft.database.DatabaseController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Load_Game extends Activity implements OnItemClickListener{
	

	private static final int PRESSED_A_SAVE=1;
	static String lastClicked;
	private ListView singleplayer, multiplayer;
	ArrayList<CharSequence> loadSingleplayer,loadMultiplayer;
	DatabaseController dc;
	
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.loadgame);
		//dbHelp = new DbHelper(this);
		dc = DatabaseController.initialize();
		//rsg = new ReadSaveGame(dbHelp);
		
		loadSingleplayer = new ArrayList<CharSequence>();
		loadMultiplayer = new ArrayList<CharSequence>();
		String[][] saves = dc.readAllSaves();
		
		int name = 0;
		int mode = 3;
		
		for(int i = 0; i< saves.length; i++){
			String saveName = saves[i][name];
			if(Integer.parseInt(saves[i][mode]) == 1){
				loadSingleplayer.add(saveName);
			}
			else{
				loadMultiplayer.add(saveName);
			}
			
		}
		
		singleplayer	= (ListView)findViewById(R.id.listView_Singleplayer);
		multiplayer 	= (ListView)findViewById(R.id.listView_Multiplayer);
		
		// By using setAdpater method in listview we an add string array in list.
		singleplayer.setAdapter(new ArrayAdapter<CharSequence>(this,R.layout.list_small_text,R.id.list_content, loadSingleplayer));
		multiplayer.setAdapter(new ArrayAdapter<CharSequence>(this,R.layout.list_small_text,R.id.list_content, loadMultiplayer));
		
		singleplayer.setOnItemClickListener(this);
		multiplayer.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		lastClicked = arg0.getItemAtPosition(arg2).toString();
		System.out.println(lastClicked);
		showDialog(PRESSED_A_SAVE);
	}
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PRESSED_A_SAVE:
			AlertDialog.Builder adb = new AlertDialog.Builder(this)
			.setMessage("Do you want to load this save?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					String[] save = dc.readSave(lastClicked);
					
					Intent intent = new Intent(Load_Game.this, Game_View.class);
					intent.putExtra("DifficultyName", save[1]);
					intent.putExtra("Length",Integer.parseInt(save[2]));
					intent.putExtra("Mode",Integer.parseInt(save[3]));
			
					Load_Game.this.startActivity(intent);
					Load_Game.this.finish(); 
					
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			return adb.create();
		}

		return null;
	}



	
	public static String getLastClicked(){
		return lastClicked;
	}
	
}
