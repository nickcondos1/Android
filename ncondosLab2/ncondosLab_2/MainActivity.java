package com.example.nick.ncondoslab2;

import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener
{
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(MainActivity.this, "Lab2, Nick Condos", Toast.LENGTH_SHORT).show();

        }


        actionBar.onOptionsItemSelected(item);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById(R.id.nav_view);

        String[] array = getResources().getStringArray(R.array.names);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.nav_list_row, R.id.uniqueName ,array);
        listView.setAdapter(arrayAdapter);
        
        listView.setOnItemClickListener(this);

        final String titleOne = "Select a Page";
        final String titleTwo = getTitle().toString();
        actionBar =
                new ActionBarDrawerToggle(this,drawerLayout, R.string.open_drawer, R.string.close_drawer)
                {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        setTitle(titleOne);
                        invalidateOptionsMenu();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        setTitle(titleTwo);
                        invalidateOptionsMenu();
                    }
                };

        drawerLayout.addDrawerListener(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBar.syncState();


        if (savedInstanceState != null) {
            int id = savedInstanceState.getInt("mainimage");
            ImageView iview = findViewById(R.id.imageOne);


                iview.setTag(id);
                iview.setImageResource(id);

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(drawerLayout.isDrawerOpen(listView))
        {
            menu.findItem(R.id.action_about).setVisible(false);
            return true;
        }
        else
           return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        ImageView iview = findViewById(R.id.imageOne);
        TypedArray images = getResources().obtainTypedArray(R.array.image_id);
        int[] imageID = new int[images.length()];

        for (int i = 0; i < images.length(); i++)
        {
            imageID[i] = images.getResourceId(i, 0);
        }
        images.recycle();

        for (int i = 0; i < imageID.length; i++)
        {
            if (i == position) {
                iview.setImageResource(imageID[i]);
                iview.setTag(imageID[i]);

                break;
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        ImageView view = findViewById(R.id.imageOne);
        Object somth = view.getTag();

        if (somth == null)
            outState.putInt("mainimage", R.drawable.fred);
        else
            outState.putInt("mainimage",(int)somth);

        super.onSaveInstanceState(outState);
    }
}
