package com.example.nick.ncondoslab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView txt = findViewById(R.id.greeting);
        txt.setText("Hello " + name);
    }

    public void onClick(View view)
    {
        int id = view.getId();
        if (id == R.id.submit)
        {
            EditText text = findViewById(R.id.ageGrab);
            String txt = text.getText().toString();

            if (text.length() == 0)
                Toast.makeText(SurveyActivity.this, "Please enter an age", Toast.LENGTH_SHORT).show();
            else {

                int age = Integer.parseInt(txt);

                Intent intent = new Intent();
                intent.putExtra("result", age);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
