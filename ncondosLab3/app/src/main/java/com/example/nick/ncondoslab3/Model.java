package com.example.nick.ncondoslab3;

import java.io.Serializable;

public class Model implements Serializable
{
    private String modelName;
    private String modelYear;
    private String engineRange;
    private int idModel;

    public Model (String name, String modelyear, String enginerange, int picid)
    {
        modelName = name;
        modelYear = modelyear;
        engineRange = enginerange;
        idModel = picid;
    }

    public Model()
    {

    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getEngineRange() {
        return engineRange;
    }

    public void setEngineRange(String engineRange) {
        this.engineRange = engineRange;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }
}
