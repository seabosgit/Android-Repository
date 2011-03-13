package com.basic;

import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WordListDisplay extends ListActivity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  String[] countries = getResources().getStringArray(R.array.countries_array);

	  setListAdapter(new ArrayAdapter<String>(this, R.layout.word_list, countries));

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
		    @SuppressWarnings("unused")
			public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		     
		    	System.out.println("test");
		     
		      String action = getIntent().getAction();	
		     	
		      /*if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
		            // The caller is waiting for us to return a note selected by
		            // the user.  The have clicked on one, so return it now.
		           // setResult(RESULT_OK, new Intent().setData(noteUri));
		            
		        } else {
		            // Launch activity to view/edit the currently selected item
		            startActivity(new Intent(Intent.ACTION_EDIT, noteUri));
		        }*/
		    	
		    	
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		         Toast.LENGTH_SHORT).show();
		     //launchNextUI();
		    }
		  });
	}
	
//	public void launchNextUI()
//	{
//		Intent i = new Intent(this,WordNavigator1.class);
//		startActivity(i);
//	}
	
	
	
}