package com.rcarrillocruz.android.openstackdroid;

import android.content.Context;
import android.database.DatabaseErrorHandler;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		ConnectionProfileTable.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		ConnectionProfileTable.onUpgrade(database, oldVersion, newVersion);
	}

}
