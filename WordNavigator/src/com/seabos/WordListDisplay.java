package com.seabos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WordListDisplay extends ListActivity {

	//Possible Actions
	private final int COUNTRY_SELECTED = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Bind the data
		String[] countries = getResources().getStringArray(R.array.countries_array);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.word_list, countries));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		// Set listener for onClick event.
		lv.setOnItemClickListener(mListItemClickListener);
	}

	// ListItem onClick listener
	private OnItemClickListener mListItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			// Grab the text from user selection
			String selectedCountry = ((TextView) view).getText().toString();

			// Create the intent
			Intent currentIntent = new Intent(view.getContext(), Activity2.class);

			// Pass the parameters
			currentIntent.putExtra("Country", selectedCountry);

			// Start Activity
			startActivityForResult(currentIntent, COUNTRY_SELECTED);

		}
	};

	// Listen for results.
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// See which child activity is calling us back.
		switch (requestCode) {
		case COUNTRY_SELECTED:
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(), data.getExtras().getString("Country"), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
			}
		default:
			break;
		}
	}

}