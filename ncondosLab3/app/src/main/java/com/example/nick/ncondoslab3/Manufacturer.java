package com.example.nick.ncondoslab3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Manufacturer implements Serializable
{

    private String name;
    private ArrayList<Model> models;

    public Manufacturer(String name)
    {
        this.name = name;
        models = new ArrayList<Model>();
    }

    public void setName(String manu)
    {
        this.name = manu;
    }
    public String getName() {
        return name;
    }
    public String getModel(int position)
    {
        return models.get(position).getModelName();
    }
    public void deleteModel(int position)
    {
       models.remove(position);
    }
    public int numberOfModels()
    {
        return models.size();
    }
    public void addNewModel(String modelName, String modelYear, String engineRange, int picid)
    {
        models.add(new Model(modelName, modelYear, engineRange, picid));
    }
    public Model getModelObject(int position)
    {
        return models.get(position);
    }

}
