package com.example.shubham.nqueen;


import android.content.Context;
        import android.graphics.Color;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RelativeLayout;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.Toast;

public class SolutionActivity extends AppCompatActivity {
    TableLayout table;

    Context context=this;
    int board[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        table = (TableLayout) findViewById(R.id.view_root);

        String s = getIntent().getStringExtra("dimension");
        int N = Integer.parseInt(s);
        int TABLE_WIDTH = N;

        NQueenProblem Queen = new NQueenProblem(N);
        board=Queen.solveNQ();


        // Populate the table with stuff
        for (int y = 0; y < TABLE_WIDTH ; y++) {
            final int row = y;
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                final int col = x;
                //final ImageButton b = new ImageButton(this);
                final Button b=new Button(this);
                b.setEnabled(false);
                b.setText("("+(y+1)+","+(x+1)+")");
                //   b.setImageResource(R.drawable.blank);
                if(board[row][col]>0){
                  //  final Handler handler = new Handler();
                  //  handler.postDelayed(new Runnable() {
                   //     @Override
                   //     public void run() {
                            // Do something after 5s = 5000ms
                            b.setBackgroundResource(R.drawable.q8);
                  //      }
                  //  }, 500);
                }

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You clicked (" + row + "," + col + ")",
                                Toast.LENGTH_SHORT).show();
                        //    b.setImageResource(R.drawable.q);
                    }
                });
                r.addView(b);

            }
        }
    }
}
