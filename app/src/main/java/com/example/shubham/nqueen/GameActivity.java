package com.example.shubham.nqueen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class GameActivity extends Activity {

	
	int height;
	int width;
	int level;
    int N;
	LinearLayout mainLayout,topBar;
	TextView movesTV;
	Button nextLevelBT,back;
    Button solution;
	NQueensDB DB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);   
        height = metrics.heightPixels;
        width = metrics.widthPixels;
		
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 1);

        DB = new NQueensDB(getApplicationContext());
        
        mainLayout = new LinearLayout(getApplicationContext());
        mainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        
        topBar = new LinearLayout(getApplicationContext());
        topBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,0,0.1f));
        topBar.setOrientation(LinearLayout.HORIZONTAL);

        
       /* movesTV = new TextView(getApplicationContext());
        movesTV.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 0.5f));
        movesTV.setTextAppearance(this, android.R.style.TextAppearance_Large);
        movesTV.setText("Moves Made : 0");
        movesTV.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        movesTV.setTextColor(Color.BLACK);
        topBar.addView(movesTV);
        */


        N=level+3;
         back=new Button(getApplicationContext());
        nextLevelBT = new Button(getApplicationContext());
        solution = new Button(getApplicationContext());

        back.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,0.5f));
        back.setTextAppearance(this,android.R.style.TextAppearance_Large);
        back.setText("<-Main");
        back.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        back.setTextColor(Color.BLACK);
        topBar.addView(back);
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(),Main4Activity.class);
                startActivity(i);
            }
        });

        nextLevelBT.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 0.5f));
        nextLevelBT.setBackgroundColor(Color.WHITE);
        nextLevelBT.setTextColor(Color.BLACK);
        nextLevelBT.setText(N+" X "+N);


        solution.setText("Solution");
        solution.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL);

        solution.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
                String s = Integer.toString(N);
				Intent i = new Intent(getApplicationContext(),SolutionActivity.class);
                i.putExtra("dimension",s);
                startActivity(i);
			}
		});


        topBar.addView(nextLevelBT);
        mainLayout.addView(topBar);
     
		GameView view = new GameView(this, height, width, level, movesTV);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,0,0.8f));
		
		mainLayout.addView(view);
        mainLayout.addView(solution);

		setContentView(mainLayout);
	}

    @Override
    public void onBackPressed() {

        if(level==1)
        {
            Intent i=new Intent(this,Main2Activity.class);
            startActivity(i);
        }
        else {
            Activity activity = (Activity) this;
            activity.finish();
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("level", level -1);
            this.startActivity(intent);
        }

    }
}
