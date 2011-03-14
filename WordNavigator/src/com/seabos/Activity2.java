package com.seabos;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        String country = extras!=null?extras.getString("Country"):"No Input Selected";
        
        setContentView(R.layout.word_list2);
                
        TextView tv = (TextView)findViewById(R.id.TxtCountry);;
        tv.setText(country);

        
        Button next = (Button) findViewById(R.id.BtnPrevious);
        next.setOnClickListener(prevButtonClickListener);
        
    }
    
    private View.OnClickListener prevButtonClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("Country", ((TextView)findViewById(R.id.TxtCountry)).getText().toString());
            setResult(RESULT_OK, intent);
            finish();
		}
	};
    	
    	 
    
}