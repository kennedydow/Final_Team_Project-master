package com.course.example.sqlitedemopro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "zoo.db";
	public static final int DATABASE_VERSION = 4;
	public static final String TABLE_NAME = "courses";
	public static final String KEY_NAME = "name";
	public static final String KEY_TEACHER = "teacher";
	public static final String KEY_TIME = "time";
	public static final String KEY_ID = "id integer primary key autoincrement";
	public static final String CREATE_TABLE = "CREATE TABLE courses ("
			+ KEY_ID + "," + KEY_NAME + " text," + KEY_TEACHER + " text, "
			+ KEY_TIME + " text);";
	
	private ContentValues values;
	private ArrayList<Course> CourseList;
	private Cursor cursor;

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
 
	//called to create table
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = CREATE_TABLE;
		Log.d("SQLiteDemo", "onCreate: " + sql);
		db.execSQL(sql);
	}

	//called when database version mismatch
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion >= newVersion) return;

		Log.d("SQLiteDemo", "onUpgrade: Version = " + newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	//add animal to database
	public void addCourse(Course item) {
		SQLiteDatabase db = this.getWritableDatabase();
		values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_TEACHER, item.getTeacher());
		values.put(KEY_TIME, item.getTime());
		db.insert(TABLE_NAME, null, values);
        Log.d("SQLiteDemo", item.getName() + " added");
        db.close();
	}
	
	//update Animal name in database
//	public void updateAnimal(Animal item, Animal newItem){
//		SQLiteDatabase db = this.getWritableDatabase();
//		values = new ContentValues();
//        values.put(KEY_NAME, newItem.getName());
//        db.update(TABLE_NAME, values, KEY_NAME + "=?", new String[] {item.getName()});
//        Log.d("SQLiteDemo", item.getName() + " updated");
//        db.close();
//	}
//
	// delete animal from database
	 public void deleteCourse(Course item){
    //  public void deleteCourse(int item){
		Log.i("ID:  ","Starting delete");
        SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME,  "name=?", new String[] {((item.getName())).trim()});

		 Log.i("SQLiteDemo",  " deleted");
	    db.close();

		}




	//query database and return ArrayList of all animals
	public ArrayList<Course> getCourseList () {
				
		SQLiteDatabase db = this.getWritableDatabase();
	    cursor = db.query(TABLE_NAME, 
    		new String[] {KEY_NAME, KEY_TEACHER,KEY_TIME},
    		null, null, null, null, KEY_NAME);
    
	    //write contents of Cursor to list
	    CourseList = new ArrayList<Course>();
	    int count = 1;
	    while (cursor.moveToNext()) {
	    	String str = cursor.getString(cursor.getColumnIndex(KEY_NAME));
	    	String teach = cursor.getString(cursor.getColumnIndex(KEY_TEACHER));
			String t = cursor.getString(cursor.getColumnIndex(KEY_TIME));

			CourseList.add(new Course(count,str,teach,t));
			count++;
	    };
	    db.close();
	    return CourseList;
    
	}
}

