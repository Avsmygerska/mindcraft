package mindcraft.pack;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Load_Game extends Activity implements OnItemClickListener{
	
	
	private static final int PRESSED_A_SAVE=1;
	static String lastClicked;
	private ListView singleplayer, multiplayer;
	private String lv_arr[]={"Android","iPhone","BlackBerry","AndroidPeople"};
	ArrayList<CharSequence> loadSingleplayer,loadMultiplayer;
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.loadgame);
		
		
		loadSingleplayer = new ArrayList<CharSequence>();
		loadMultiplayer = new ArrayList<CharSequence>();
		loadMultiplayer.add("perp");
		loadMultiplayer.add("kerp");
		loadMultiplayer.add("merp");
		loadMultiplayer.add("perp");
		loadMultiplayer.add("kerp");
		loadMultiplayer.add("merp");
		loadMultiplayer.add("perp");
		loadMultiplayer.add("kerp");
		loadMultiplayer.add("merp");
		loadMultiplayer.add("perp");
		loadMultiplayer.add("kerp");
		loadMultiplayer.add("merp");
		loadSingleplayer.add("derp");
		loadSingleplayer.add("herp");
		loadSingleplayer.add("lerp");
		loadSingleplayer.add("derp");
		loadSingleplayer.add("herp");
		loadSingleplayer.add("lerp");
		loadSingleplayer.add("derp");
		loadSingleplayer.add("herp");
		loadSingleplayer.add("lerp");
		loadSingleplayer.add("derp");
		loadSingleplayer.add("herp");
		loadSingleplayer.add("lerp");
		
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
			.setMessage("Do you want to load " + getLastClicked() +"?")
			.setCancelable(false)
			.setPositiveButton(lastClicked, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss(); 
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
