package com.rcarrillocruz.android.openstackdroid;

import android.database.sqlite.SQLiteDatabase;

public class ConnectionProfileTable {
	
	public static final String TABLE_CONNECTION_PROFILE = "connectionProfile";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PROFILE_NAME = "profileName";
	public static final String COLUMN_ENDPOINT = "endpoint";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_TENANT_ID = "tenantId";

	  private static final String DATABASE_CREATE = "create table " 
		      + TABLE_CONNECTION_PROFILE
		      + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_PROFILE_NAME + " text not null, " 
		      + COLUMN_ENDPOINT + " text not null," 
		      + COLUMN_USERNAME + " text not null"
		      + COLUMN_PASSWORD + " text not null"
		      + COLUMN_TENANT_ID + " text not null"
		      + ");";

	  public static void onCreate(SQLiteDatabase database) {
		  database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		  database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONNECTION_PROFILE);
		  onCreate(database);
	  }
}
