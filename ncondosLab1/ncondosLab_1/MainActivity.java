package com.example.nick.ncondoslab1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends TracerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = findViewById(R.id.resultText);

        Intent intent = getIntent();
        if (intent != null)
        {
            String action = intent.getAction();
            String type = intent.getType();

            if (action.equals(Intent.ACTION_SEND) && type.equals("text/plain"))
            {
                txt.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        // This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Lab 0, Winter 2019, Paul H Schimpf", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view)
    {
        int id = view.getId();
        if (id == R.id.buttonSurvey)
        {
            EditText text = findViewById(R.id.nameGrab);
            String txt = text.getText().toString();

            if (txt.length() > 0)
            {
                Intent intent = new Intent(this, SurveyActivity.class);
                intent.putExtra("name", txt);
                startActivityForResult(intent, 1);
            }
            else
                Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.buttonWebsite)
        {
            Uri link = Uri.parse("https://sites.google.com/site/pschimpf99");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(link);
            startActivity(intent);
                
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            int age = data.getIntExtra("result", 2);


            TextView txt = findViewById(R.id.resultText);
            if (age < 40)
            {
                txt.setText("You are under 40, so you're trustworthy");
            }
            else
            {
                txt.setText("You're NOT under 40, so you're NOT trustworthy");
            }
        }


    }
}
