package com.example.arthi.dmssmartapp.ThreeAffectedStructure;

import java.io.Serializable;
import java.util.ArrayList;

public class AffectedStructure implements Serializable {

public String householdAffected;
public String structuresAffected;
public String mainHouse;
public String bath;
public String hut;
public String shop;
public String stable;
public String wall;
public String otherStructuresOne;
public String otherStructuresTwo;

public String mainHouseArea;
public String outdoortoiletArea;
public String hutArea;
public String shopArea;
public String stableArea;
public String wallArea;
public String otherAreaOne;
public String otherAreaTwo;

public String mainHouseAffected;
public String outdoorAffected;
public String hutAffected;
public String shopAffected;
public String stableAffected;
public String wallAffected;
public String otherAffectedOne;
public String otherAffectedTwo;

public String viableUse;
public String rebuilt;
public String construction;

public String mainHouseWeeks;
public String otherStructureWeeks;

public String pagodaAffected;
public String gravesAffected;
public String publicAffected;

public String description;


public ArrayList<Construction>constructions;

   public AffectedStructure(String householdAffected, String structuresAffected, String mainHouse, String bath, String hut, String shop, String stable, String wall, String otherStructuresOne, String otherStructuresTwo, String mainHouseArea, String outdoortoiletArea, String hutArea, String shopArea, String stableArea, String wallArea, String otherAreaOne, String otherAreaTwo, String mainHouseAffected, String outdoorAffected, String hutAffected, String shopAffected, String stableAffected, String wallAffected, String otherAffectedOne, String otherAffectedTwo, String viableUse, String rebuilt, String construction, String mainHouseWeeks, String otherStructureWeeks, String pagodaAffected, String gravesAffected, String publicAffected, String description, ArrayList<Construction> constructions) {
      this.householdAffected = householdAffected;
      this.structuresAffected = structuresAffected;
      this.mainHouse = mainHouse;
      this.bath = bath;
      this.hut = hut;
      this.shop = shop;
      this.stable = stable;
      this.wall = wall;
      this.otherStructuresOne = otherStructuresOne;
      this.otherStructuresTwo = otherStructuresTwo;
      this.mainHouseArea = mainHouseArea;
      this.outdoortoiletArea = outdoortoiletArea;
      this.hutArea = hutArea;
      this.shopArea = shopArea;
      this.stableArea = stableArea;
      this.wallArea = wallArea;
      this.otherAreaOne = otherAreaOne;
      this.otherAreaTwo = otherAreaTwo;
      this.mainHouseAffected = mainHouseAffected;
      this.outdoorAffected = outdoorAffected;
      this.hutAffected = hutAffected;
      this.shopAffected = shopAffected;
      this.stableAffected = stableAffected;
      this.wallAffected = wallAffected;
      this.otherAffectedOne = otherAffectedOne;
      this.otherAffectedTwo = otherAffectedTwo;
      this.viableUse = viableUse;
      this.rebuilt = rebuilt;
      this.construction = construction;
      this.mainHouseWeeks = mainHouseWeeks;
      this.otherStructureWeeks = otherStructureWeeks;
      this.pagodaAffected = pagodaAffected;
      this.gravesAffected = gravesAffected;
      this.publicAffected = publicAffected;
      this.description = description;
      this.constructions = constructions;
   }

   public String getHouseholdAffected() {
      return householdAffected;
   }

   public void setHouseholdAffected(String householdAffected) {
      this.householdAffected = householdAffected;
   }

   public String getStructuresAffected() {
      return structuresAffected;
   }

   public void setStructuresAffected(String structuresAffected) {
      this.structuresAffected = structuresAffected;
   }

   public String getMainHouse() {
      return mainHouse;
   }

   public void setMainHouse(String mainHouse) {
      this.mainHouse = mainHouse;
   }

   public String getBath() {
      return bath;
   }

   public void setBath(String bath) {
      this.bath = bath;
   }

   public String getHut() {
      return hut;
   }

   public void setHut(String hut) {
      this.hut = hut;
   }

   public String getShop() {
      return shop;
   }

   public void setShop(String shop) {
      this.shop = shop;
   }

   public String getStable() {
      return stable;
   }

   public void setStable(String stable) {
      this.stable = stable;
   }

   public String getWall() {
      return wall;
   }

   public void setWall(String wall) {
      this.wall = wall;
   }

   public String getOtherStructuresOne() {
      return otherStructuresOne;
   }

   public void setOtherStructuresOne(String otherStructuresOne) {
      this.otherStructuresOne = otherStructuresOne;
   }

   public String getOtherStructuresTwo() {
      return otherStructuresTwo;
   }

   public void setOtherStructuresTwo(String otherStructuresTwo) {
      this.otherStructuresTwo = otherStructuresTwo;
   }

   public String getMainHouseArea() {
      return mainHouseArea;
   }

   public void setMainHouseArea(String mainHouseArea) {
      this.mainHouseArea = mainHouseArea;
   }

   public String getOutdoortoiletArea() {
      return outdoortoiletArea;
   }

   public void setOutdoortoiletArea(String outdoortoiletArea) {
      this.outdoortoiletArea = outdoortoiletArea;
   }

   public String getHutArea() {
      return hutArea;
   }

   public void setHutArea(String hutArea) {
      this.hutArea = hutArea;
   }

   public String getShopArea() {
      return shopArea;
   }

   public void setShopArea(String shopArea) {
      this.shopArea = shopArea;
   }

   public String getStableArea() {
      return stableArea;
   }

   public void setStableArea(String stableArea) {
      this.stableArea = stableArea;
   }

   public String getWallArea() {
      return wallArea;
   }

   public void setWallArea(String wallArea) {
      this.wallArea = wallArea;
   }

   public String getOtherAreaOne() {
      return otherAreaOne;
   }

   public void setOtherAreaOne(String otherAreaOne) {
      this.otherAreaOne = otherAreaOne;
   }

   public String getOtherAreaTwo() {
      return otherAreaTwo;
   }

   public void setOtherAreaTwo(String otherAreaTwo) {
      this.otherAreaTwo = otherAreaTwo;
   }

   public String getMainHouseAffected() {
      return mainHouseAffected;
   }

   public void setMainHouseAffected(String mainHouseAffected) {
      this.mainHouseAffected = mainHouseAffected;
   }

   public String getOutdoorAffected() {
      return outdoorAffected;
   }

   public void setOutdoorAffected(String outdoorAffected) {
      this.outdoorAffected = outdoorAffected;
   }

   public String getHutAffected() {
      return hutAffected;
   }

   public void setHutAffected(String hutAffected) {
      this.hutAffected = hutAffected;
   }

   public String getShopAffected() {
      return shopAffected;
   }

   public void setShopAffected(String shopAffected) {
      this.shopAffected = shopAffected;
   }

   public String getStableAffected() {
      return stableAffected;
   }

   public void setStableAffected(String stableAffected) {
      this.stableAffected = stableAffected;
   }

   public String getWallAffected() {
      return wallAffected;
   }

   public void setWallAffected(String wallAffected) {
      this.wallAffected = wallAffected;
   }

   public String getOtherAffectedOne() {
      return otherAffectedOne;
   }

   public void setOtherAffectedOne(String otherAffectedOne) {
      this.otherAffectedOne = otherAffectedOne;
   }

   public String getOtherAffectedTwo() {
      return otherAffectedTwo;
   }

   public void setOtherAffectedTwo(String otherAffectedTwo) {
      this.otherAffectedTwo = otherAffectedTwo;
   }

   public String getViableUse() {
      return viableUse;
   }

   public void setViableUse(String viableUse) {
      this.viableUse = viableUse;
   }

   public String getRebuilt() {
      return rebuilt;
   }

   public void setRebuilt(String rebuilt) {
      this.rebuilt = rebuilt;
   }

   public String getConstruction() {
      return construction;
   }

   public void setConstruction(String construction) {
      this.construction = construction;
   }

   public String getMainHouseWeeks() {
      return mainHouseWeeks;
   }

   public void setMainHouseWeeks(String mainHouseWeeks) {
      this.mainHouseWeeks = mainHouseWeeks;
   }

   public String getOtherStructureWeeks() {
      return otherStructureWeeks;
   }

   public void setOtherStructureWeeks(String otherStructureWeeks) {
      this.otherStructureWeeks = otherStructureWeeks;
   }

   public String getPagodaAffected() {
      return pagodaAffected;
   }

   public void setPagodaAffected(String pagodaAffected) {
      this.pagodaAffected = pagodaAffected;
   }

   public String getGravesAffected() {
      return gravesAffected;
   }

   public void setGravesAffected(String gravesAffected) {
      this.gravesAffected = gravesAffected;
   }

   public String getPublicAffected() {
      return publicAffected;
   }

   public void setPublicAffected(String publicAffected) {
      this.publicAffected = publicAffected;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public ArrayList<Construction> getConstructions() {
      return constructions;
   }

   public void setConstructions(ArrayList<Construction> constructions) {
      this.constructions = constructions;
   }
}
