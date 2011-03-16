package com.seabos.content;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CountryContentProvider extends ContentProvider {

	public static final Uri CONTENT_URI = Uri
			.parse("content://com.seabos.content.countryprovider");

	public static final String _id = "_ID";
	public static final String NAME = "NAME";

	private static final String DB_NAME = "countries-db.sqlite";
	private static final String DATABASE_TABLE_NAME = "COUNTRIES";

	private static HashMap<String, String> sProjectionMap;

	static {

		sProjectionMap = new HashMap<String, String>();
		sProjectionMap.put(_id, _id);
		sProjectionMap.put(NAME, NAME);
	}

	// Handle to a new DatabaseHelper.
	private DatabaseHelper mOpenHelper;

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		return null;
	}

	@Override
	public boolean onCreate() {
		// Creates a new helper object. Note that the database itself isn't
		// opened until
		// something tries to access it, and it's only created if it doesn't
		// already exist.
		mOpenHelper = new DatabaseHelper(getContext());

		// Assumes that any failures will be reported by a thrown exception.
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// SQLiteQueryBuilder is a helper class that creates the
		// proper SQL syntax for us.
		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();

		// Set the table we're querying.
		qBuilder.setTables(DATABASE_TABLE_NAME);
		qBuilder.setProjectionMap(sProjectionMap);

		// Opens the database object in "read" mode, since no writes need to be
		// done.
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		// Make the query.
		Cursor c = qBuilder.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);

		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	/**
	 * 
	 * This class helps open, create, and upgrade the database file. Set to
	 * package visibility for testing purposes.
	 */
	static class DatabaseHelper extends SQLiteOpenHelper {

		private static String DB_PATH = "";
		private static final String DB_NAME = "countries-db.sqlite";
		private SQLiteDatabase myDataBase;
		private final Context myContext;
		
		DatabaseHelper(Context context) {
			// calls the super constructor, requesting the default cursor
			// factory.
			super(context, DB_NAME, null, 1);
			this.myContext = context;
			DB_PATH = "/data/data/"
					+ context.getApplicationContext().getPackageName()
					+ "/databases/";
		}

		/**
		 * 
		 * Creates the underlying database with table name and column names
		 * taken from the NotePad class.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		/**
		 * 
		 * Demonstrates that the provider must consider what happens when the
		 * underlying datastore is changed. In this sample, the database is
		 * upgraded the database by destroying the existing data. A real
		 * application should upgrade the database in place.
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

}
