package com.rcarrillocruz.android.openstackdroid.db.connectionprofile;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class ConnectionProfileContentProvider extends ContentProvider {
	private ConnectionProfileDatabaseHelper database;
	
	private static final int CONNECTION_PROFILES = 10;
	private static final int CONNECTION_PROFILE_ID = 20;
	
	private static final String AUTHORITY = "com.rcarrillocruz.android.openstackdroid";
	private static final String BASE_PATH = "ConnectionProfileContentProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/connection_profiles";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/connection_profile";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, CONNECTION_PROFILES);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CONNECTION_PROFILE_ID);
	}
	
	public int delete(Uri uri, String selection, String[] selectionArgs) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    
	    switch (uriType) {
		    case CONNECTION_PROFILES:
		    	rowsDeleted = sqlDB.delete(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, selection, selectionArgs);
		    	break;
		    case CONNECTION_PROFILE_ID:
		    	
		    	String id = uri.getLastPathSegment();
		      
		    	if (TextUtils.isEmpty(selection)) {
		    		rowsDeleted = sqlDB.delete(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, ConnectionProfileTable.COLUMN_ID + "=" + id, null);
		    	} else {
		    		rowsDeleted = sqlDB.delete(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, ConnectionProfileTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
		    	}
		      
		      break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	   
	    getContext().getContentResolver().notifyChange(uri, null);
	    
	    return rowsDeleted;
	}

	public String getType(Uri uri) {
		return null;
	}

	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    long id = 0;
	    
	    switch (uriType) {
	    case CONNECTION_PROFILES:
	    	id = sqlDB.insert(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, null, values);
	    	break;
	    default:
	    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    
	    getContext().getContentResolver().notifyChange(uri, null);
	 
	    return Uri.parse(BASE_PATH + "/" + id);
	}

	public boolean onCreate() {
		database = new ConnectionProfileDatabaseHelper(getContext());
		return false;
	}

	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
	    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
	    queryBuilder.setTables(ConnectionProfileTable.TABLE_CONNECTION_PROFILE);
	    int uriType = sURIMatcher.match(uri);
	    
	    switch (uriType) {
	    	case CONNECTION_PROFILES:
	    		break;
	    	case CONNECTION_PROFILE_ID:
	    		queryBuilder.appendWhere(ConnectionProfileTable.COLUMN_ID + "=" + uri.getLastPathSegment());	     
	    		break;
	    	default:
	    		throw new IllegalArgumentException("Unknown URI");
	    }
	   
	    SQLiteDatabase sqlDB = database.getReadableDatabase();
	    Cursor cursor = queryBuilder.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder);
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);
	    
	    return cursor;
	}

	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsUpdated = 0;
	    
	    switch (uriType) {
		    case CONNECTION_PROFILES:
		      rowsUpdated = sqlDB.update(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, values, selection, selectionArgs);
		      break;
		    case CONNECTION_PROFILE_ID:
		    	String id = uri.getLastPathSegment();
		    	if (TextUtils.isEmpty(selection)) {
		    		rowsUpdated = sqlDB.update(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, values, ConnectionProfileTable.COLUMN_ID + "=" + id, null);
		    	} else {
		    		rowsUpdated = sqlDB.update(ConnectionProfileTable.TABLE_CONNECTION_PROFILE, values, ConnectionProfileTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
		    	}
		    	break;
		    default:
		    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    
	    getContext().getContentResolver().notifyChange(uri, null);
	    
	    return rowsUpdated;
	}

}
