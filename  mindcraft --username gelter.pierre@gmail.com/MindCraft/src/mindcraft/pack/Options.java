package mindcraft.pack;

import java.util.ArrayList;
import java.util.List;

import mindcraft.database.DatabaseController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Options extends Activity implements OnSeekBarChangeListener, OnClickListener, 
																	OnItemSelectedListener{
	
	private List<CharSequence> difficulty;
	private ArrayList<Integer> currentSelectedOptions;
	private ArrayAdapter <CharSequence> adapter;
	private Spinner spinner;
	private String defaultValue = "";
	
	private int SPINNER_SELECTED;
	private static final int CATEGORY_DETAIL = 1;
	private static final int DELETE_OPTION = 2;
	private static final int UPDATE_OPTION = 3;
	private static final int TRIED_TO_DELETE_DEFAULT = 4;
	private static final int TRIED_TO_UPDATE_DEFAULT = 5;
	private SeekBar test, test1, test2, test3, test4;
	private TextView testView, testView1, testView2, testView3, testView4;
	 
	private Button createNew, optionDelete, optionEdit, optionCancel;
	boolean tryToDeleteDefault = false;
	private DatabaseController dc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		
		int testTime = 36;
		int max = 5;

		this.test = (SeekBar) this.findViewById(R.id.test);
		this.testView = (TextView) this.findViewById(R.id.testview);
		this.test1 = (SeekBar) this.findViewById(R.id.test1);
		this.testView1 = (TextView) this.findViewById(R.id.testview1);
		this.test2 = (SeekBar) this.findViewById(R.id.test2);
		this.testView2 = (TextView) this.findViewById(R.id.testview2);
		this.test3 = (SeekBar) this.findViewById(R.id.test3);
		this.testView3 = (TextView) this.findViewById(R.id.testview3);
		this.test4 = (SeekBar) this.findViewById(R.id.test4);
		this.testView4 = (TextView) this.findViewById(R.id.testview4);

		this.createNew = (Button) this.findViewById(R.id.createNew);
		this.optionDelete = (Button) this.findViewById(R.id.optionsDelete);
		this.optionCancel = (Button) this.findViewById(R.id.optionsCancel);
		this.optionEdit = (Button) this.findViewById(R.id.optionsEdit);



		createNew.setOnClickListener(this);
		optionDelete.setOnClickListener(this);
		optionCancel.setOnClickListener(this);
		optionEdit.setOnClickListener(this);

		test.setMax(max);
		test.setOnSeekBarChangeListener(Options.this);
		test1.setMax(max);
		test1.setOnSeekBarChangeListener(Options.this);
		test2.setMax(testTime);
		test2.setOnSeekBarChangeListener(Options.this);
		test3.setMax(max);
		test3.setOnSeekBarChangeListener(Options.this);
		test4.setMax(max);
		test4.setOnSeekBarChangeListener(Options.this);

		difficulty = new ArrayList<CharSequence>();
		dc = DatabaseController.initialize();
		
		String[] options = dc.readAllOptions();
		
		for(int i = 0; i < options.length; i++){ 
			difficulty.add(options[i]);
		}
		
		spinner = (Spinner) findViewById(R.id.spinner1); 
		adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, difficulty);
		
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);


	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		if(!adapter.getItem(SPINNER_SELECTED).toString().equals("Default")){

			if(seekBar == test){
				testView.setText(progress+ 1 + " extra patterns per turn");
			}
			if(seekBar == test1){
				Integer i = progress*500 + 500;
				testView1.setText(i.doubleValue()/1000+ " seconds");
			}
			if(seekBar == test2){
				Integer i = progress*5000+5000;
				testView2.setText((i.doubleValue())/1000+ " seconds");
			}
			if(seekBar == test3){
				Integer i = progress*500 + 500;
				testView3.setText(i.doubleValue()/1000+ " seconds");
			}
			if(seekBar == test4){
				testView4.setText(progress*10+ " percent");
			}
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if(adapter.getItem(SPINNER_SELECTED).toString().equals("Default")){
			if(seekBar == test){
				test.setProgress(currentSelectedOptions.get(0));
			}
			if(seekBar == test1){
				test1.setProgress(currentSelectedOptions.get(1));
			}
			if(seekBar == test2){
				test2.setProgress(currentSelectedOptions.get(2));
			}
			if(seekBar == test3){
				test3.setProgress(currentSelectedOptions.get(3));
			}
			if(seekBar == test4){
				test4.setProgress(currentSelectedOptions.get(4));
			} 

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.optionsCancel:
			Options.this.finish();
			break;
		case R.id.optionsDelete:
			if(adapter.getItem(SPINNER_SELECTED).toString().equals("Default"))
				showDialog(TRIED_TO_DELETE_DEFAULT);
			else
				showDialog(DELETE_OPTION);

			break;
		case R.id.optionsEdit:
			if(adapter.getItem(SPINNER_SELECTED).toString().equals("Default"))
				showDialog(TRIED_TO_UPDATE_DEFAULT);
			else
				showDialog(UPDATE_OPTION);
			break;

		case R.id.createNew:
			showDialog(CATEGORY_DETAIL); 
			break;
		}

	}


	@Override
	public void onItemSelected(AdapterView<?> parent,
			View view, int pos, long id) {

		SPINNER_SELECTED = pos;
		System.out.println("\n \n" + SPINNER_SELECTED +"\n \n");
		String[] cursor = dc.readOption(parent.getItemAtPosition(pos).toString());
		if(cursor.length==0){
			
		}
		else{
			currentSelectedOptions = new ArrayList<Integer>();
		
			Integer wait	= (Integer.parseInt(cursor[1])/5000);
			Integer incr	= Integer.parseInt(cursor[2]);
			Integer patt	= (Integer.parseInt(cursor[3])/500);
			Integer show	= Integer.parseInt(cursor[4])/1000;
			Integer speed	= Integer.parseInt(cursor[5])/10;
			
			test.setProgress(incr-1);
			test1.setProgress(patt-1);
			test2.setProgress(wait-1);
			test3.setProgress(show);
			test4.setProgress(speed);
			
			currentSelectedOptions.add(incr);
			currentSelectedOptions.add(patt);
			currentSelectedOptions.add(wait);
			currentSelectedOptions.add(show);
			currentSelectedOptions.add(speed*10);
			

			testView.setText((incr) + " extra patterns per turn"); // Retarded textview
			testView1.setText(patt.doubleValue()/2+ " seconds"); // Retarded textview
			testView2.setText(wait.doubleValue()*5+ " seconds"); // Retarded textview
			testView3.setText(show.doubleValue()/2 + 0.5+ " seconds"); // Retarded textview
			testView4.setText(speed*10+ " percent"); // Retarded textview
					
		}


	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CATEGORY_DETAIL:

			LayoutInflater li = LayoutInflater.from(this);
			View categoryDetailView = li.inflate(R.layout.category_detail, null);

			AlertDialog.Builder categoryDetailBuilder = new AlertDialog.Builder(this);
			categoryDetailBuilder.setTitle("Type in the new option name");
			categoryDetailBuilder.setView(categoryDetailView);
			AlertDialog categoryDetail = categoryDetailBuilder.create();

			categoryDetail.setButton("OK", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					AlertDialog categoryDetail = (AlertDialog)dialog;
					EditText et = (EditText)categoryDetail.findViewById(R.id.categoryEditText);
					if (et.getText().length()!=0 && !et.getText().toString().equals("Default") &&
							!et.getText().toString().equals("default")){

						String[] newOpt = {et.getText().toString(),"1","2000","5000","1000","0" };
						difficulty.add(et.getText().toString());
						dc.storeOption(newOpt);
					}
				}
			});

			categoryDetail.setButton2("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}}); 

			return categoryDetail;

		case DELETE_OPTION:
			return new AlertDialog.Builder(this)
			.setMessage("Do you want to delete the selected option?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dc.deleteOption(difficulty.get(SPINNER_SELECTED).toString());
					adapter.remove(adapter.getItem(SPINNER_SELECTED));

				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			}).create();

		case UPDATE_OPTION:
			return new AlertDialog.Builder(this)
			.setMessage("Do you want to update the selected option? \nAll your saves with this option will be deleted")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					String[] temp = {adapter.getItem(SPINNER_SELECTED).toString(),
							test.getProgress()+1 + "",
							test1.getProgress()*500+500 + "",
							test2.getProgress()*5000+5000 + "", 
							test3.getProgress()*500+500 + "", 
							test4.getProgress()*10 + ""};
					dc.updateOption(temp);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			}).create();

		case TRIED_TO_DELETE_DEFAULT:
			return new AlertDialog.Builder(this)
			.setMessage("You may not delete the default option")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.create();

		case TRIED_TO_UPDATE_DEFAULT:
			return new AlertDialog.Builder(this)
			.setMessage("You may not update the default option")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.create();	
		default:
			break;
		}

		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case CATEGORY_DETAIL:
			AlertDialog categoryDetail = (AlertDialog)dialog;
			EditText et = (EditText)categoryDetail.findViewById(R.id.categoryEditText);
			et.setText(defaultValue);
			break;
		default:
			break;
		}
	}

}
