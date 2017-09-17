package com.example.shubham.nqueen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    public EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e=(EditText) findViewById(R.id.editText);
    }

    public void solution(View view) {
        String s = e.getText().toString();
        if(!s.isEmpty()) {
            int N = Integer.parseInt(s);
            if(N >= 4 && N <= 20){
                Intent i = new Intent(Main3Activity.this, SolutionActivity.class);
                i.putExtra("dimension", s);
                startActivity(i);
            }
            else
                Toast.makeText(Main3Activity.this, "Range is from 4-20", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(Main3Activity.this, "Enter valid number", Toast.LENGTH_SHORT).show();
    }
}
