package com.example.nick.ncondoslab3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
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
    private SectionPagerAdapter section;
    private ViewPager pager;
    private Model currentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manufacturers = new ArrayList<>();
        currentModel = new Model();

        if (savedInstanceState != null) {
            manufacturers = (ArrayList<Manufacturer>) savedInstanceState.getSerializable("manufacturer");

            section = new SectionPagerAdapter(getSupportFragmentManager(), manufacturers);
            pager = findViewById(R.id.viewPager);
            pager.setAdapter(section);
            currentModel = (Model)savedInstanceState.getSerializable("model");
        }
        else {
            try {
                boolean parsed = parseFile("information.txt");

                section = new SectionPagerAdapter(getSupportFragmentManager(), manufacturers);
                pager = findViewById(R.id.viewPager);
                pager.setAdapter(section);

            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Parsing the file failed", Toast.LENGTH_SHORT);
            }
        }
    }

    private boolean parseFile(String filename) throws IOException
    {
        AssetManager manager = getResources().getAssets();

        Scanner scanner = new Scanner(manager.open(filename));

        int k = 0;
        while (scanner.hasNextLine())
        {
            String manuName = scanner.nextLine();
            manufacturers.add(new Manufacturer(manuName));

            while (true) // <--- Sorry for this
            {
                String[] modelStuff = scanner.nextLine().split(",");
                if (modelStuff[0].equals("END"))
                    break;

                int picid = getResources().getIdentifier(modelStuff[0].toLowerCase().replaceAll(" ", ""),
                        "drawable", getPackageName());
                manufacturers.get(k).addNewModel(modelStuff[0], modelStuff[1], modelStuff[2], picid);
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
        outState.putSerializable("model", currentModel);
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
            Toast.makeText(MainActivity.this, "Lab6, Nick Condos", Toast.LENGTH_SHORT).show();

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

    public void changePage(Model model)
    {
        currentModel = model;
        section.notifyDataSetChanged();

        pager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
       if (pager.getCurrentItem() == 1)
           pager.setCurrentItem(0);
       else
           super.onBackPressed();
    }

    public Model getSelectedModel()
    {
        return currentModel;
    }



}
