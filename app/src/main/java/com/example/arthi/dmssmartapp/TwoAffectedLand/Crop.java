package com.example.arthi.dmssmartapp.TwoAffectedLand;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Crop implements Serializable {
  public String typesOfCrop;
  public String cropAreaA;
  public String cropAreaB;

    public Crop(String typesOfCrop, String cropAreaA, String cropAreaB) {
        this.typesOfCrop = typesOfCrop;
        this.cropAreaA = cropAreaA;
        this.cropAreaB = cropAreaB;
    }

    public String getTypesOfCrop() {
        return typesOfCrop;
    }

    public void setTypesOfCrop(String typesOfCrop) {
        this.typesOfCrop = typesOfCrop;
    }

    public String getCropAreaA() {
        return cropAreaA;
    }

    public void setCropAreaA(String cropAreaA) {
        this.cropAreaA = cropAreaA;
    }

    public String getCropAreaB() {
        return cropAreaB;
    }

    public void setCropAreaB(String cropAreaB) {
        this.cropAreaB = cropAreaB;
    }
}