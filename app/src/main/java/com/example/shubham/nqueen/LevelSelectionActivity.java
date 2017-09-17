package com.example.shubham.nqueen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LevelSelectionActivity extends AppCompatActivity {
    public EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        e=(EditText) findViewById(R.id.editText);
    }

    public void solution(View view) {
        String s = e.getText().toString();
        if(!s.isEmpty()) {
            int N = Integer.parseInt(s);
            if(N >= 4 && N <= 30){
                Intent intent = new Intent(getApplicationContext(),GameActivity.class);
                intent.putExtra("level", N-3);
                startActivity(intent);
            }
            else
                Toast.makeText(LevelSelectionActivity.this, "Range is from 4-20", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(LevelSelectionActivity.this, "Enter valid number", Toast.LENGTH_SHORT).show();
    }
}
