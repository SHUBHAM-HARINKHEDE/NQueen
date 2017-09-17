package com.example.shubham.nqueen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Fragment;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv1;
    Button b1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("NQueen");
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Main()).commit();

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView image = (ImageView)findViewById(R.id.frontimage);
                Animation animation1 =
                        AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.fade);
                image.startAnimation(animation1);
                tv1.startAnimation(animation1);
                launchdelay();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
/*
        tv1=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.fadebtn);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/MinecraftEvenings.otf");
        tv1.setTypeface(face);
        b1.setTypeface(face);
*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Main()).commit();
         //  super.onBackPressed();
       }
        


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           this.finishAffinity();
            super.onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        android.app.FragmentManager fragmentManager = getFragmentManager();
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.content_main4);
        tv1=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.fadebtn);
        img=(ImageView)findViewById(R.id.frontimage);
        relativeLayout.removeView(b1);
        relativeLayout.removeView(tv1);
        relativeLayout.removeView(img);


        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.instruction) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Instruction()).commit();
        } else if (id == R.id.credit) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Credit()).commit();

        } else if (id == R.id.about) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new About()).commit();

        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(Main4Activity.this,LevelSelectionActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"Under Construction",Toast.LENGTH_LONG);

        } else if (id == R.id.nav_send) {
            this.finishAffinity();
            super.onBackPressed();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /***************************************************************************/
    public void launchdelay()
    {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                       Intent i=new Intent(Main4Activity.this,Main2Activity.class);
                       startActivity(i);
                    }
                }, 1000);
    }
    public void fade(View view){
        ImageView image = (ImageView)findViewById(R.id.frontimage);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        image.startAnimation(animation1);
        tv1.startAnimation(animation1);
        launchdelay();
    }

    /****************************************/





}

