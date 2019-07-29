package com.example.arthi.dmssmartapp.OneHouseholdData;

import java.io.Serializable;
import java.util.ArrayList;

public class HouseholdDate implements Serializable {


 public String householdHead;
 public String gender;
 public String age;
 public String maritalStatus;
 public String education;
 public String ethnicGroup;
 public String regularWages;
 public String dailyWages;
 public String nonwagedEarnings;
 public String seasonalEarnings;
 public String sourceOfIncome;
 public String mainIncome;
 public String secondIncome;
 public String fullTimeMale;
 public String fullTimeFemale;
 public String partTimeMale;
 public String partTimeFemale;
 public String totalHouseHold;
 public String governmentService;
 public String privateSector;
 public String services;
 public String trade;
 public String construction;
 public String agriculture;
 public String casuallabor;
 public String totalother;

 public String eaarned;
 public String govPension;
 public String govAssistance;
 public String remittances;
 public String rentalIncome;
 public String nonearnedOthers;

 public String source;
 public String vegetables;
 public String rice;
 public String otherCrop;
 public String livestock;
 public String poultry;
 public String forestProducts;
 public String handicrafts;
 public String estimateOthers;

 public String house;
 public String roof;
 public String walls;
 public String floor;

 public String disabledMale;
 public String disabledFemale;
 public String disabledillness;

 public String fmailyLiving;

 public ArrayList<Male> males;
 public ArrayList<Female> females;

    public HouseholdDate(String householdHead, String gender, String age, String maritalStatus, String education, String ethnicGroup,

                         String regularWages, String dailyWages, String nonwagedEarnings, String seasonalEarnings,
                         String sourceOfIncome, String mainIncome, String secondIncome, String fullTimeMale,
                         String fullTimeFemale, String partTimeMale, String partTimeFemale, String totalHouseHold,
                         String governmentService, String privateSector, String services, String trade, String construction,
                         String agriculture, String casuallabor, String totalother, String eaarned, String govPension,
                         String govAssistance, String remittances, String rentalIncome, String nonearnedOthers,
                         String source, String vegetables, String rice, String otherCrop, String livestock,
                         String poultry, String forestProducts, String handicrafts, String estimateOthers,
                         String house, String roof, String walls, String floor, String disabledMale,
                         String disabledFemale, String disabledillness, String fmailyLiving,
                         ArrayList<Male> males, ArrayList<Female> females) {
        this.householdHead = householdHead;
        this.gender = gender;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.ethnicGroup = ethnicGroup;
        this.regularWages = regularWages;
        this.dailyWages = dailyWages;
        this.nonwagedEarnings = nonwagedEarnings;
        this.seasonalEarnings = seasonalEarnings;
        this.sourceOfIncome = sourceOfIncome;
        this.mainIncome = mainIncome;
        this.secondIncome = secondIncome;
        this.fullTimeMale = fullTimeMale;
        this.fullTimeFemale = fullTimeFemale;
        this.partTimeMale = partTimeMale;
        this.partTimeFemale = partTimeFemale;
        this.totalHouseHold = totalHouseHold;
        this.governmentService = governmentService;
        this.privateSector = privateSector;
        this.services = services;
        this.trade = trade;
        this.construction = construction;
        this.agriculture = agriculture;
        this.casuallabor = casuallabor;
        this.totalother = totalother;
        this.eaarned = eaarned;
        this.govPension = govPension;
        this.govAssistance = govAssistance;
        this.remittances = remittances;
        this.rentalIncome = rentalIncome;
        this.nonearnedOthers = nonearnedOthers;
        this.source = source;
        this.vegetables = vegetables;
        this.rice = rice;
        this.otherCrop = otherCrop;
        this.livestock = livestock;
        this.poultry = poultry;
        this.forestProducts = forestProducts;
        this.handicrafts = handicrafts;
        this.estimateOthers = estimateOthers;
        this.house = house;
        this.roof = roof;
        this.walls = walls;
        this.floor = floor;
        this.disabledMale = disabledMale;
        this.disabledFemale = disabledFemale;
        this.disabledillness = disabledillness;
        this.fmailyLiving = fmailyLiving;
        this.males = males;
        this.females = females;
    }

    public String getHouseholdHead() {
        return householdHead;
    }

    public void setHouseholdHead(String householdHead) {
        this.householdHead = householdHead;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getRegularWages() {
        return regularWages;
    }

    public void setRegularWages(String regularWages) {
        this.regularWages = regularWages;
    }

    public String getDailyWages() {
        return dailyWages;
    }

    public void setDailyWages(String dailyWages) {
        this.dailyWages = dailyWages;
    }

    public String getNonwagedEarnings() {
        return nonwagedEarnings;
    }

    public void setNonwagedEarnings(String nonwagedEarnings) {
        this.nonwagedEarnings = nonwagedEarnings;
    }

    public String getSeasonalEarnings() {
        return seasonalEarnings;
    }

    public void setSeasonalEarnings(String seasonalEarnings) {
        this.seasonalEarnings = seasonalEarnings;
    }

    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    public String getMainIncome() {
        return mainIncome;
    }

    public void setMainIncome(String mainIncome) {
        this.mainIncome = mainIncome;
    }

    public String getSecondIncome() {
        return secondIncome;
    }

    public void setSecondIncome(String secondIncome) {
        this.secondIncome = secondIncome;
    }

    public String getFullTimeMale() {
        return fullTimeMale;
    }

    public void setFullTimeMale(String fullTimeMale) {
        this.fullTimeMale = fullTimeMale;
    }

    public String getFullTimeFemale() {
        return fullTimeFemale;
    }

    public void setFullTimeFemale(String fullTimeFemale) {
        this.fullTimeFemale = fullTimeFemale;
    }

    public String getPartTimeMale() {
        return partTimeMale;
    }

    public void setPartTimeMale(String partTimeMale) {
        this.partTimeMale = partTimeMale;
    }

    public String getPartTimeFemale() {
        return partTimeFemale;
    }

    public void setPartTimeFemale(String partTimeFemale) {
        this.partTimeFemale = partTimeFemale;
    }

    public String getTotalHouseHold() {
        return totalHouseHold;
    }

    public void setTotalHouseHold(String totalHouseHold) {
        this.totalHouseHold = totalHouseHold;
    }

    public String getGovernmentService() {
        return governmentService;
    }

    public void setGovernmentService(String governmentService) {
        this.governmentService = governmentService;
    }

    public String getPrivateSector() {
        return privateSector;
    }

    public void setPrivateSector(String privateSector) {
        this.privateSector = privateSector;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getCasuallabor() {
        return casuallabor;
    }

    public void setCasuallabor(String casuallabor) {
        this.casuallabor = casuallabor;
    }

    public String getTotalother() {
        return totalother;
    }

    public void setTotalother(String totalother) {
        this.totalother = totalother;
    }

    public String getEaarned() {
        return eaarned;
    }

    public void setEaarned(String eaarned) {
        this.eaarned = eaarned;
    }

    public String getGovPension() {
        return govPension;
    }

    public void setGovPension(String govPension) {
        this.govPension = govPension;
    }

    public String getGovAssistance() {
        return govAssistance;
    }

    public void setGovAssistance(String govAssistance) {
        this.govAssistance = govAssistance;
    }

    public String getRemittances() {
        return remittances;
    }

    public void setRemittances(String remittances) {
        this.remittances = remittances;
    }

    public String getRentalIncome() {
        return rentalIncome;
    }

    public void setRentalIncome(String rentalIncome) {
        this.rentalIncome = rentalIncome;
    }

    public String getNonearnedOthers() {
        return nonearnedOthers;
    }

    public void setNonearnedOthers(String nonearnedOthers) {
        this.nonearnedOthers = nonearnedOthers;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVegetables() {
        return vegetables;
    }

    public void setVegetables(String vegetables) {
        this.vegetables = vegetables;
    }

    public String getRice() {
        return rice;
    }

    public void setRice(String rice) {
        this.rice = rice;
    }

    public String getOtherCrop() {
        return otherCrop;
    }

    public void setOtherCrop(String otherCrop) {
        this.otherCrop = otherCrop;
    }

    public String getLivestock() {
        return livestock;
    }

    public void setLivestock(String livestock) {
        this.livestock = livestock;
    }

    public String getPoultry() {
        return poultry;
    }

    public void setPoultry(String poultry) {
        this.poultry = poultry;
    }

    public String getForestProducts() {
        return forestProducts;
    }

    public void setForestProducts(String forestProducts) {
        this.forestProducts = forestProducts;
    }

    public String getHandicrafts() {
        return handicrafts;
    }

    public void setHandicrafts(String handicrafts) {
        this.handicrafts = handicrafts;
    }

    public String getEstimateOthers() {
        return estimateOthers;
    }

    public void setEstimateOthers(String estimateOthers) {
        this.estimateOthers = estimateOthers;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
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

    public String getDisabledMale() {
        return disabledMale;
    }

    public void setDisabledMale(String disabledMale) {
        this.disabledMale = disabledMale;
    }

    public String getDisabledFemale() {
        return disabledFemale;
    }

    public void setDisabledFemale(String disabledFemale) {
        this.disabledFemale = disabledFemale;
    }

    public String getDisabledillness() {
        return disabledillness;
    }

    public void setDisabledillness(String disabledillness) {
        this.disabledillness = disabledillness;
    }

    public String getFmailyLiving() {
        return fmailyLiving;
    }

    public void setFmailyLiving(String fmailyLiving) {
        this.fmailyLiving = fmailyLiving;
    }

    public ArrayList<Male> getMales() {
        return males;
    }

    public void setMales(ArrayList<Male> males) {
        this.males = males;
    }

    public ArrayList<Female> getFemales() {
        return females;
    }

    public void setFemales(ArrayList<Female> females) {
        this.females = females;
    }
}