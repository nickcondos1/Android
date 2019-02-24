package com.example.nick.ncondoslab3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {

    private ArrayList<Manufacturer> manufacturers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manufacturers = new ArrayList<>();



        if (savedInstanceState != null) {
            manufacturers = (ArrayList<Manufacturer>) savedInstanceState.getSerializable("manufacturer");
            MyListAdapter listAdapter = new MyListAdapter(this, manufacturers);
            ExpandableListView eview = findViewById(R.id.expandList);
            eview.setAdapter(listAdapter);
        }
        else {
            try {
                boolean parsed = parseFile("information.txt");
                MyListAdapter listAdapter = new MyListAdapter(this, manufacturers);
                ExpandableListView eview = findViewById(R.id.expandList);
                eview.setAdapter(listAdapter);

            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Parsing the file failed", Toast.LENGTH_SHORT);
            }
        }



        ExpandableListView view = findViewById(R.id.expandList);
        view.setOnChildClickListener(this);


    }

    private boolean parseFile(String filename) throws IOException
    {
        AssetManager manager = getResources().getAssets();

        Scanner scanner = new Scanner(manager.open(filename));

        int k = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] str = line.split(",");

            for (int i = 0; i < str.length; i++)
            {
                if (i == 0) {
                    manufacturers.add(new Manufacturer(str[0]));
                }
                else
                    manufacturers.get(k).addNewModel(str[i]);
            }
            k++;

        }


        if (manufacturers.size() > 0)
            return true;
        else
            return false;


    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putSerializable("manufacturer", manufacturers);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
    {
        Toast.makeText(MainActivity.this,  manufacturers.get(groupPosition).getName() + " " +
                manufacturers.get(groupPosition).getModel(childPosition), Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Lab3, Nick Condos", Toast.LENGTH_SHORT).show();

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
