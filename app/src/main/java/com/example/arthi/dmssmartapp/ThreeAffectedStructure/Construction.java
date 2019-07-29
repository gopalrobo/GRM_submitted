package com.example.arthi.dmssmartapp.ThreeAffectedStructure;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Construction implements Serializable {
  public String types;
  public String roof;
  public String walls;
  public String floor;

    public Construction(String types, String roof, String walls, String floor) {
        this.types = types;
        this.roof = roof;
        this.walls = walls;
        this.floor = floor;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}