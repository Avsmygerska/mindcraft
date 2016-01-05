package mindcraft.pack;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class TestDBActivity extends ListActivity {
    /** Called when the activity is first created. */
	
	/*static final int DO_YOU_WANT = 1;
	private String[] COUNTRIES = {"Sverige", "Norge", "Finland", "Danmark", "Tyskland", "Polen", "Island"};
	PopupWindow popup;
	private StoreHighScore shs; 
	private ReadHighScore rhs;
	private StoreOptions so;
	private ReadOptions ro;
	private StoreSaveGame ssg;
	private ReadSaveGame rsg;
	private String[] SAVEGAMES = {"DERP", "ASD", "PEW", "DERKA", "HERRO", "HAI", "PEWPEW"};
	
	private EditText pew;
	private TextView show;
	private Button button;
	private EditText pewpew;
	private TextView showshow;
	private Button buttonbutton;
	private EditText pewpewpew;
	private Button buttonbuttonbutton;
	private TextView showshowshow;
	Button backButton;
	PopupWindow pw;
	Bundle savedInstanceState;
	
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));
        
        this.savedInstanceState = savedInstanceState;
        System.err.println("Här är jag nu3!");
        
        //DbHelperOptions dbHelperOptions = new DbHelperOptions(TestDBActivity.this);
        DbHelper dbHelper = new DbHelper(TestDBActivity.this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        rhs = new ReadHighScore(dbHelper, db);
        ro = new ReadOptions(dbHelper, db); 
        rsg = new ReadSaveGame(dbHelper, db);
        shs = new StoreHighScore(dbHelper, db);
        so = new StoreOptions(dbHelper, db);
        ssg = new StoreSaveGame(dbHelper, db, this);
        
        this.pew = (EditText)this.findViewById(R.id.editText1);
        this.show = (TextView) this.findViewById(R.id.textView1);
        this.button = (Button) this.findViewById(R.id.button1);
        this.pewpew = (EditText)this.findViewById(R.id.editText2);
        this.showshow = (TextView) this.findViewById(R.id.textView2);
        this.buttonbutton = (Button) this.findViewById(R.id.button2);
        this.pewpewpew = (EditText)this.findViewById(R.id.editText3);
        //this.showshowshow = (TextView) this.findViewById(R.id.textView3);
        this.buttonbuttonbutton = (Button) this.findViewById(R.id.button3);
        
        

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            // When clicked, show a toast with the TextView text
            Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();
          }
        });
      
        
        
        backButton = (Button) TestDBActivity.this.findViewById(R.id.cancelButton);
        
        pew.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
            	if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
            			 shs.store(pew.getText().toString());
            			 db.close();
            	}
            	return true;
            }
        });
        
        button.setOnClickListener(new View.OnClickListener(){
        	
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Cursor cursor = rhs.read();
				cursor.moveToFirst();
				System.out.println(cursor.getCount());
				for(int i = 0; i < cursor.getCount(); i++){
					int t = cursor.getColumnIndex(DbHelper.C_NAME);
					int z = cursor.getColumnIndex(DbHelper.C_POINTS);
					if(i == 0){
						
						show.setText(cursor.getString(t) + ", " + cursor.getString(z) + " \n", EditText.BufferType.EDITABLE );
					}
					else
						show.setText(show.getText() + cursor.getString(t) + ", " + cursor.getString(z) + " \n", EditText.BufferType.EDITABLE );
					//show.setText(cursor.getCount()+"", EditText.BufferType.EDITABLE );
					cursor.moveToNext();
					db.close();
				}
				
			}
        });
        
        
        pewpew.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                	so.store(pewpew.getText().toString());
                	db.close();
                  return true;
                }
                return false;
            }
        });
        
        buttonbutton.setOnClickListener(new View.OnClickListener(){
        	
			@Override
			public void onClick(View v) {
				System.out.println("Här är jag nu1!");
				// TODO Auto-generated method stub
				Cursor cursor = ro.readAll();
				cursor.moveToFirst();
				for(int i = 0; i < cursor.getCount(); i++){
					int a = cursor.getColumnIndex(DbHelper.C_NAME_OPT);
					int b = cursor.getColumnIndex(DbHelper.C_SPEED_INTERVAL_PATTERN);
					int c = cursor.getColumnIndex(DbHelper.C_WAITTIME);
					int d = cursor.getColumnIndex(DbHelper.C_INCREMENTING_DIFFICULTY);
					int e = cursor.getColumnIndex(DbHelper.C_SHOW_PATTERN_TIME);
					
					
					if(i == 0){
						showshow.setText(cursor.getString(a) + ", " + cursor.getString(b)+ ", "+ cursor.getString(c) + 
								", " + cursor.getString(d)+ ", " +cursor.getString(e) +
								" \n", EditText.BufferType.EDITABLE );
					}
					else{
						showshow.setText(showshow.getText() + cursor.getString(a) + ", " + cursor.getString(b) + ", "+ 
								cursor.getString(c) + ", " + cursor.getString(d)+ ", " 
								+cursor.getString(e) + " \n", EditText.BufferType.EDITABLE );
					//show.setText(cursor.getCount()+"", EditText.BufferType.EDITABLE );
					}
					cursor.moveToNext();
					
				}
				db.close();
				System.out.println("Här är jag nu!");
				
				
			}
        });
        
        pewpewpew.setOnKeyListener(new View.OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				 if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
		                  // Perform action on key press
		                	ssg.store(pewpewpew.getText().toString());
		                	db.close();
		                  return true;
		                }
		                return false;
			}});
        
        buttonbuttonbutton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				System.out.println("Här är jag nu1!");
				// TODO Auto-generated method stub
				Cursor cursor = rsg.readAll();
				cursor.moveToFirst();
				for(int i = 0; i < cursor.getCount(); i++){
					int a = cursor.getColumnIndex(DbHelper.C_NAME_SAVE);
					int b = cursor.getColumnIndex(DbHelper.C_DIFFICULTY);
					int c = cursor.getColumnIndex(DbHelper.C_LEVEL);					
					
					/*if(i == 0){
						showshowshow.setText(cursor.getString(a) + ", " + cursor.getString(b)+ ", "+ cursor.getString(c) + 
										" \n", EditText.BufferType.EDITABLE );
					}
					else{
						showshowshow.setText(showshow.getText() + cursor.getString(a) + ", " + cursor.getString(b) + ", "+ 
								cursor.getString(c) + ", \n", EditText.BufferType.EDITABLE );
					//show.setText(cursor.getCount()+"", EditText.BufferType.EDITABLE );
					}
					cursor.moveToNext();
					
				}
				db.close();
				System.out.println("Här är jag nu!");
				
			}});
        setContentView(R.layout.main);
      }
   
    /*private PopupWindow pw;
    private void initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) TestDBActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popup_layout,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 300, 470, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
     
            //mResultText = (TextView) layout.findViewById(R.id.server_status_text);
            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            //makeBlack(cancelButton);
            //Button b = (Button) findViewById(R.id.end_data_send_button);
            cancelButton.setOnClickListener(new View.OnClickListener(){
                

            	/*	@Override
            		public boolean onTouch(View v, MotionEvent event) {
            			 ;
            			return false;
            		}
            
            		@Override
            		public void onClick(View v) {
            			System.out.println("här är jag");
            			pw.dismiss();
            			
            		}
            		
            });
     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
    	
    	switch (id) {
        case DO_YOU_WANT:
        	return new AlertDialog.Builder(this)
    		       .setMessage("Are you sure you want to overwrite?")
    		       .setCancelable(false)
    		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		                //TestDBActivity.this.finish();
    		        	   ssg.overWrite();
    		           }
    		       })
    		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		                dialog.cancel();
    		           }
    		       }).create();
    	
    	
	 
    	}
    	return null;
    } */
}