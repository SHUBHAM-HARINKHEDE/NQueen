package com.example.shubham.nqueen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NQueensDB extends SQLiteOpenHelper{

	
	static String DB_NAME = "nQueensDB";
	String NQueensTable = "NQUEENSTABLE";
	String Level = "LEVEL";
	String Score = "SCORE";
	
	
	public NQueensDB(Context context) {
		super(context, DB_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE "+NQueensTable+" ( "+Level+" integer, "+Score+" integer)";
		db.execSQL(query);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS "+NQueensTable);
		
	}

	
	public void updateScore(int level, int score) {
		

		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Score, score);
		
		int rowsUpdated = db.update(NQueensTable, values, Level +" = ?", new String[] {String.valueOf(level)});
		

		
		if(rowsUpdated == 0) {
		
			values.put(Level,level);
			
			db.insert(NQueensTable, null, values);

		}
		
		db.close();
	}
	
	public int getScore(int level) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		String query = "SELECT * FROM "+NQueensTable+" where "+Level+"="+level;

		Cursor cursor = db.rawQuery(query, null);
		
		int score = 0;
		if(cursor.moveToFirst()) {
			
			score = cursor.getInt(1); 
		}
		
		db.close();

		return score;
	}
	
}
