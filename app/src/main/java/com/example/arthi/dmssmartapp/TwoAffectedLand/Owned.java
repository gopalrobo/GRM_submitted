package com.example.arthi.dmssmartapp.TwoAffectedLand;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Owned implements Serializable {
 public String typesOfOwned;
 public String ownedAreaA;
 public String ownedAreaB;

    public Owned(String typesOfOwned, String ownedAreaA, String ownedAreaB) {
        this.typesOfOwned = typesOfOwned;
        this.ownedAreaA = ownedAreaA;
        this.ownedAreaB = ownedAreaB;
    }

    public String getTypesOfOwned() {
        return typesOfOwned;
    }

    public void setTypesOfOwned(String typesOfOwned) {
        this.typesOfOwned = typesOfOwned;
    }

    public String getOwnedAreaA() {
        return ownedAreaA;
    }

    public void setOwnedAreaA(String ownedAreaA) {
        this.ownedAreaA = ownedAreaA;
    }

    public String getOwnedAreaB() {
        return ownedAreaB;
    }

    public void setOwnedAreaB(String ownedAreaB) {
        this.ownedAreaB = ownedAreaB;
    }
}