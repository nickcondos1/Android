package com.ncondos.nick.ncondoslab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private SevenSegment sevenSegment;
    private SevenSegment sevenSegment2;
    private SevenSegment sevenSegment3;
    private SevenSegment sevenSegment4;
    private SevenSegment sevenSegment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sevenSegment = findViewById(R.id.sevenSegment6);


        Button btn = findViewById(R.id.increaseValue);
        sevenSegment2 = findViewById(R.id.sevenSegment2);
        sevenSegment3 = findViewById(R.id.sevenSegment3);
        sevenSegment4 = findViewById(R.id.sevenSegment4);
        sevenSegment5 = findViewById(R.id.sevenSegment5);

        if (savedInstanceState != null)
        {
            sevenSegment.set(savedInstanceState.getInt("cur") - 1);
            sevenSegment.increment();
            sevenSegment.invalidate();

        }

        sevenSegment2.set(sevenSegment.get() == 10 ? 0 : sevenSegment.get() + 1);
        sevenSegment3.set(sevenSegment2.get() == 10 ? 0 : sevenSegment2.get() + 1);
        sevenSegment4.set(sevenSegment3.get() == 10 ? 0 : sevenSegment3.get() + 1);
        sevenSegment5.set(sevenSegment4.get() == 10 ? 0 : sevenSegment4.get() + 1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sevenSegment.increment();
                sevenSegment.invalidate();

                sevenSegment2.increment();
                sevenSegment2.invalidate();

                sevenSegment3.increment();
                sevenSegment3.invalidate();

                sevenSegment4.increment();
                sevenSegment4.invalidate();

                sevenSegment5.increment();
                sevenSegment5.invalidate();


            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        int currentValue = sevenSegment.get();
        outState.putInt("cur", currentValue);


        super.onSaveInstanceState(outState);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Lab4, Nick Condos", Toast.LENGTH_SHORT).show();

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
