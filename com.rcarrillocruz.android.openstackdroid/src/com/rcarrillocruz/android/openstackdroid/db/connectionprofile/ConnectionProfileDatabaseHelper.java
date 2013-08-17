package com.rcarrillocruz.android.openstackdroid.db.connectionprofile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionProfileDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "connection_profile.db";
	private static final int DATABASE_VERSION = 1;
	
	
	 public ConnectionProfileDatabaseHelper(Context context) {
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }
	 
	public ConnectionProfileDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase database) {
		ConnectionProfileTable.onCreate(database);
	}

	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		ConnectionProfileTable.onUpgrade(database, oldVersion, newVersion);
	}

}
