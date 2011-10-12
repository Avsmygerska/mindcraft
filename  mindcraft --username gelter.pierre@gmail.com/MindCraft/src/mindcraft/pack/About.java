package mindcraft.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class About extends Activity implements OnClickListener {

	ImageButton aboutBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		this.aboutBack = (ImageButton) this.findViewById(R.id.aboutBackButton);
		this.aboutBack.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		About.this.finish();
	}

}
