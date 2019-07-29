package com.example.arthi.dmssmartapp.OneHouseholdData;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Male implements Serializable {

   public String maleFirstYear;
   public String maleSecondYear;
   public String maleThirdYear;
   public String maleFourYear;
   public String maleFiveYear;
   public String maleSixYear;

    public Male(String maleFirstYear, String maleSecondYear, String maleThirdYear, String maleFourYear, String maleFiveYear, String maleSixYear) {
        this.maleFirstYear = maleFirstYear;
        this.maleSecondYear = maleSecondYear;
        this.maleThirdYear = maleThirdYear;
        this.maleFourYear = maleFourYear;
        this.maleFiveYear = maleFiveYear;
        this.maleSixYear = maleSixYear;
    }

    public String getMaleFirstYear() {
        return maleFirstYear;
    }

    public void setMaleFirstYear(String maleFirstYear) {
        this.maleFirstYear = maleFirstYear;
    }

    public String getMaleSecondYear() {
        return maleSecondYear;
    }

    public void setMaleSecondYear(String maleSecondYear) {
        this.maleSecondYear = maleSecondYear;
    }

    public String getMaleThirdYear() {
        return maleThirdYear;
    }

    public void setMaleThirdYear(String maleThirdYear) {
        this.maleThirdYear = maleThirdYear;
    }

    public String getMaleFourYear() {
        return maleFourYear;
    }

    public void setMaleFourYear(String maleFourYear) {
        this.maleFourYear = maleFourYear;
    }

    public String getMaleFiveYear() {
        return maleFiveYear;
    }

    public void setMaleFiveYear(String maleFiveYear) {
        this.maleFiveYear = maleFiveYear;
    }

    public String getMaleSixYear() {
        return maleSixYear;
    }

    public void setMaleSixYear(String maleSixYear) {
        this.maleSixYear = maleSixYear;
    }
}