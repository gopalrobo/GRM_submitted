package com.example.arthi.dmssmartapp.OneHouseholdData;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Female implements Serializable {
  public  String femaleFirstYear;
  public  String femaleSecondYear;
  public  String femaleThirdYear;
  public  String femaleFourYear;
  public  String femaleFiveYear;
  public  String femaleSixYear;

    public Female(String femaleFirstYear, String femaleSecondYear, String femaleThirdYear, String femaleFourYear, String femaleFiveYear, String femaleSixYear) {
        this.femaleFirstYear = femaleFirstYear;
        this.femaleSecondYear = femaleSecondYear;
        this.femaleThirdYear = femaleThirdYear;
        this.femaleFourYear = femaleFourYear;
        this.femaleFiveYear = femaleFiveYear;
        this.femaleSixYear = femaleSixYear;
    }

    public String getFemaleFirstYear() {
        return femaleFirstYear;
    }

    public void setFemaleFirstYear(String femaleFirstYear) {
        this.femaleFirstYear = femaleFirstYear;
    }

    public String getFemaleSecondYear() {
        return femaleSecondYear;
    }

    public void setFemaleSecondYear(String femaleSecondYear) {
        this.femaleSecondYear = femaleSecondYear;
    }

    public String getFemaleThirdYear() {
        return femaleThirdYear;
    }

    public void setFemaleThirdYear(String femaleThirdYear) {
        this.femaleThirdYear = femaleThirdYear;
    }

    public String getFemaleFourYear() {
        return femaleFourYear;
    }

    public void setFemaleFourYear(String femaleFourYear) {
        this.femaleFourYear = femaleFourYear;
    }

    public String getFemaleFiveYear() {
        return femaleFiveYear;
    }

    public void setFemaleFiveYear(String femaleFiveYear) {
        this.femaleFiveYear = femaleFiveYear;
    }

    public String getFemaleSixYear() {
        return femaleSixYear;
    }

    public void setFemaleSixYear(String femaleSixYear) {
        this.femaleSixYear = femaleSixYear;
    }
}