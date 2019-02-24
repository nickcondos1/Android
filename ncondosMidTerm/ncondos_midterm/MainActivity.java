package com.ncondos.nick.ncondosmidterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    ImageView one;
    ImageView two;
    ImageView three;
    ImageView four;
    ImageView five;
    ImageView six;
    ImageView seven;
    ImageView eight;
    ImageView nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView o = findViewById(R.id.o);
        ImageView x = findViewById(R.id.x);

        one = findViewById(R.id.blank1);
        two = findViewById(R.id.blank2);
        three = findViewById(R.id.blank3);
        four = findViewById(R.id.blank4);
        five = findViewById(R.id.blank5);
        six = findViewById(R.id.blank6);
        seven = findViewById(R.id.blank7);
        eight = findViewById(R.id.blank8);
        nine = findViewById(R.id.blank9);

        if (savedInstanceState != null)
        {
            one.setImageResource(savedInstanceState.getInt("one"));
            two.setImageResource(savedInstanceState.getInt("two"));
            three.setImageResource(savedInstanceState.getInt("three"));
            four.setImageResource(savedInstanceState.getInt("four"));
            five.setImageResource(savedInstanceState.getInt("five"));
            six.setImageResource(savedInstanceState.getInt("six"));
            seven.setImageResource(savedInstanceState.getInt("seven"));
            eight.setImageResource(savedInstanceState.getInt("eight"));
            nine.setImageResource(savedInstanceState.getInt("nine"));

            one.setTag(savedInstanceState.getInt("one"));
            two.setTag(savedInstanceState.getInt("two"));
            three.setTag(savedInstanceState.getInt("three"));
            four.setTag(savedInstanceState.getInt("four"));
            five.setTag(savedInstanceState.getInt("five"));
            six.setTag(savedInstanceState.getInt("six"));
            seven.setTag(savedInstanceState.getInt("seven"));
            eight.setTag(savedInstanceState.getInt("eight"));
            nine.setTag(savedInstanceState.getInt("nine"));

            one.setOnDragListener(this);
            two.setOnDragListener(this);
            three.setOnDragListener(this);
            four.setOnDragListener(this);
            five.setOnDragListener(this);
            six.setOnDragListener(this);
            seven.setOnDragListener(this);
            eight.setOnDragListener(this);
            nine.setOnDragListener(this);
        }
        else {

            one.setTag(R.drawable.blank);
            two.setTag(R.drawable.blank);
            three.setTag(R.drawable.blank);
            four.setTag(R.drawable.blank);
            five.setTag(R.drawable.blank);
            six.setTag(R.drawable.blank);
            seven.setTag(R.drawable.blank);
            eight.setTag(R.drawable.blank);
            nine.setTag(R.drawable.blank);

            one.setOnDragListener(this);
            two.setOnDragListener(this);
            three.setOnDragListener(this);
            four.setOnDragListener(this);
            five.setOnDragListener(this);
            six.setOnDragListener(this);
            seven.setOnDragListener(this);
            eight.setOnDragListener(this);
            nine.setOnDragListener(this);
        }
        o.setTag(R.drawable.o);
        x.setTag(R.drawable.x);

        o.setOnTouchListener(this);
        x.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            View.DragShadowBuilder drag = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, drag, v, 0);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event)
    {

        if (event.getAction() == DragEvent.ACTION_DROP)
        {
            if ((int)v.getTag() != R.drawable.blank)
            {
                Toast.makeText(this,"You cannot play on this cell", Toast.LENGTH_SHORT).show();
                return true;
            }

            ImageView view = (ImageView) event.getLocalState();
            v = (ImageView) v;
            ((ImageView) v).setImageResource((int)view.getTag());
            v.setTag((int)view.getTag());
            return true;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("one", (int)one.getTag());
        outState.putInt("two", (int)two.getTag());
        outState.putInt("three", (int)three.getTag());
        outState.putInt("four", (int)four.getTag());
        outState.putInt("five", (int)five.getTag());
        outState.putInt("six", (int)six.getTag());
        outState.putInt("seven", (int)seven.getTag());
        outState.putInt("eight", (int)eight.getTag());
        outState.putInt("nine", (int)nine.getTag());

        super.onSaveInstanceState(outState);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Midterm, Nick Condos", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        // This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
