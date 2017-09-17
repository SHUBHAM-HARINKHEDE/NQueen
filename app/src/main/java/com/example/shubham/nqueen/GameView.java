package com.example.shubham.nqueen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameView extends View {

	
	int screenHeight;
	int screenWidth;
	int level;
	int rows,columns;
	int cellSize;
	int totalQueens;
	int movesMade;
	
	static int startY;
	static int startX;
	
	boolean drawQueen[][];
	boolean redMark[][];
	
	Context context;
	Drawable queenIcon;
	Paint paint;
	TextView movesTV;
	
	NQueensDB DB;
	
	public GameView(Context context, int height, int width, int level, TextView movesTV) {
		super(context);
		
		this.context = context;
		this.screenHeight = height;
		this.screenWidth = width;
		this.level = level;
		this.movesTV = movesTV;
		DB = new NQueensDB(context);
		movesMade = 0;
		totalQueens = rows = columns = 3 + level;
		
		redMark = new boolean[rows][columns];
		drawQueen = new boolean[rows][columns];
		
		for(int i = 0 ; i < rows ; i++) {
			for( int j = 0 ; j < columns ; j++) {
				drawQueen[i][j] = false;
				redMark[i][j] = false;
			}
		}	
		
		queenIcon = getResources().getDrawable(R.drawable.queen);
		
		
		if( screenHeight/rows < screenWidth/columns) {
			
			cellSize = screenHeight/rows;
			startX = 0;
			startY = 0;
			
		} else {
			
			cellSize = screenWidth/columns;
			startX = 0;			
			startY = 0;
			
		}
		
		
		paint = new Paint();
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		

		int X = startX;
		int Y = startY;
		
		int k = 0;
		
		for(int i = 0 ; i < rows ; i++) {
			
			for( int j = 0 ; j < columns ; j++) {
				
				paint.setStyle(Paint.Style.FILL);
				
				if(k % 2 == 0) {
					
					paint.setColor(Color.rgb(130,82,1));
					canvas.drawRect( X, Y, X+cellSize, Y+cellSize, paint);
				}
				else {
					
					paint.setColor(Color.WHITE);
					canvas.drawRect( X, Y, X+cellSize, Y+cellSize, paint);
				}
				
				if(redMark[i][j])
					paint.setColor(Color.RED);
				else
					paint.setColor(Color.BLACK);
				
				paint.setStyle(Paint.Style.STROKE);
				paint.setStrokeWidth(5);
				canvas.drawRect( X, Y, X+cellSize, Y+cellSize, paint);
				
				if(drawQueen[i][j]) {
					queenIcon.setBounds(X, Y, X+cellSize, Y+cellSize);
					queenIcon.draw(canvas);
				}
				
				
				k++;
				X += cellSize;
			}
			X = startX;
			Y += cellSize;
			if(rows % 2 == 0)
				k++;
		}
		

		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction() & MotionEvent.ACTION_MASK) {
		
		case MotionEvent.ACTION_DOWN:
			markCell(event.getX(),event.getY());
			break;
		
		}
		
		return true;
	}
	
	public void markCell(float X, float Y) {
		
		int x = startX;
		int y = startY;
		
		for(int i = 0 ; i < rows ; i++) {
			
			for( int j = 0 ; j < columns ; j++) {
				
				
				if(X > x && X < x+cellSize && Y > y && Y < y+cellSize ) {
					
					
					
					if(drawQueen[i][j]) {
						drawQueen[i][j] = false;
						totalQueens++;
						movesMade++;
						checkQueenConfiguration();
						invalidate();
					}
					else if(totalQueens > 0){
						drawQueen[i][j] = true;
						totalQueens--;						
						movesMade++;
						if(totalQueens == 0 && checkGameOver())
							gameOver();
						checkQueenConfiguration();
						invalidate();
					}
				}
					
				
				x += cellSize;
			}
			x = startX;
			y += cellSize;
		}	
	}
	
	public void checkQueenConfiguration() {
		
		int flag  = 0;
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < columns ; j++) {
				
				flag = 0;
				if(drawQueen[i][j]) {
					
					for(int r = i + 1 ; r < rows ; r++)  { //down
						if(drawQueen[r][j]) {
							flag = 1;
							redMark[r][j] = true;
						}
					}
					
					for(int r = i - 1 ; r >= 0 ; r--) { //up
						if(drawQueen[r][j]) {
							flag = 1;
							redMark[r][j] = true;
						}
					}
					
					for(int c = j + 1 ; c < columns ; c++) { //right
						if(drawQueen[i][c]) {
							flag = 1;
							redMark[i][c] = true;
						}
					}
					
					for(int c = j - 1 ; c >= 0 ; c--) { //left
						if(drawQueen[i][c]) {
							flag = 1;
							redMark[i][c] = true;
						}
					}
					
					for(int r = i + 1,c = j + 1 ; r < rows && c < columns ; r++,c++) { //down,right
						if(drawQueen[r][c]) {
							flag = 1;
							redMark[r][c] = true;
						}
					}
					
					for(int r = i - 1,c = j + 1 ; r >= 0 && c < columns ; r--,c++) { //up,right
						if(drawQueen[r][c]) {
							flag = 1;
							redMark[r][c] = true;
						}
					}
					
					for(int r = i - 1,c = j - 1 ; r >= 0 && c >= 0 ; r--,c--) { //up,left
						if(drawQueen[r][c]) {
							flag = 1;
							redMark[r][c] = true;
						}
					}
					
					for(int r = i + 1,c = j - 1 ; r < rows && c >= 0 ; r++,c--) { //down,left
						if(drawQueen[r][c]) {
							flag = 1;
							redMark[r][c] = true;
						}
					}
					
					if(flag == 1)
						redMark[i][j] = true;
					else
						redMark[i][j] = false;
					
				} 
				else
					redMark[i][j] = false;
				
				
				
			}
		}
		
	}
	
	public boolean checkGameOver() {
		
		for(int i = 0 ; i < rows ; i++ ) {
			
			for( int j = 0; j < columns ; j++) {
				
				if(drawQueen[i][j]) {
					
					for(int r = i + 1 ; r < rows ; r++) //down
						if(drawQueen[r][j])
							return false;
					
					for(int r = i - 1 ; r >= 0 ; r--) //up
						if(drawQueen[r][j])
							return false;
					
					for(int c = j + 1 ; c < columns ; c++) //right
						if(drawQueen[i][c])
							return false;
					
					for(int c = j - 1 ; c >= 0 ; c--) //left
						if(drawQueen[i][c])
							return false;
					
					for(int r = i + 1,c = j + 1 ; r < rows && c < columns ; r++,c++) //down,right
						if(drawQueen[r][c])
							return false;
					
					for(int r = i - 1,c = j + 1 ; r >= 0 && c < columns ; r--,c++) //up,right
						if(drawQueen[r][c])
							return false;					
					
					for(int r = i - 1,c = j - 1 ; r >= 0 && c >= 0 ; r--,c--) //up,left
						if(drawQueen[r][c])
							return false;					
					
					for(int r = i + 1,c = j - 1 ; r < rows && c >= 0 ; r++,c--) //down,left
						if(drawQueen[r][c])
							return false;					
				}
				
			}
		}
		return true;
	}
	
	public void gameOver() {
		
		if(DB.getScore(level) > movesMade || DB.getScore(level) == 0) {
			DB.updateScore(level, movesMade);
		}
		
		
		Builder builder = new Builder(context);
		
		if(level+1 <= 10) 
			builder.setTitle("Good Job").setMessage("Level Complete!");
		else
			builder.setTitle("Congrats!").setMessage("You have successfully completed the game!");
		
		if(level+1 <= 10) {
			builder.setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
						
					Activity activity = (Activity) getContext();
					activity.finish();
					Intent intent = new Intent(context,GameActivity.class);
					intent.putExtra("level", level+1);
					context.startActivity(intent);
					
				}
			});
		}
		builder.setNegativeButton("Go back", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Activity activity = (Activity) getContext();
				activity.finish();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.setCancelable(true);
		dialog.show();
		
	}
}
