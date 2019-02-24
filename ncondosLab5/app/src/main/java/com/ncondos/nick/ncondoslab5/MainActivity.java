package com.ncondos.nick.ncondoslab5;

import android.animation.TimeAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Clock clock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);


        clock.setListener(new GetTimeListener() {
            @Override
            public void getTime(int hour, int minute, int second) {
                TextView view = findViewById(R.id.timeDisplay);

                String hours = hour < 10 ? ("0" + hour) : hour + "";
                String minutes = minute < 10 ? ("0" + minute) : minute + "";
                String seconds = second < 10 ? ("0" + second) : second + "";

                view.setText(hours + ":" + minutes + ":" + seconds);



            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Lab5, Nick Condos", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        // This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
