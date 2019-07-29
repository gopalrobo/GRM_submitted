package com.example.arthi.dmssmartapp.TwoAffectedLand;

import java.io.Serializable;
import java.util.ArrayList;

public class AffectedLand implements Serializable {
public String legalOwner;
public String tenure;
public String legalRight;
public String residential;
public String agricultural;
public String commercial;

public String landowner;
public String landResidential;
public String landAgricultural;
public String landCommercial;
public String landOthers;

public String underHarvest;
public ArrayList<Owned>owneds;
public ArrayList<Project>projects;
public ArrayList<Crop>crops;

   public AffectedLand(String legalOwner, String tenure, String legalRight, String residential, String agricultural, String commercial, String landowner, String landResidential, String landAgricultural, String landCommercial, String landOthers, String underHarvest, ArrayList<Owned> owneds, ArrayList<Project> projects, ArrayList<Crop> crops) {
      this.legalOwner = legalOwner;
      this.tenure = tenure;
      this.legalRight = legalRight;
      this.residential = residential;
      this.agricultural = agricultural;
      this.commercial = commercial;
      this.landowner = landowner;
      this.landResidential = landResidential;
      this.landAgricultural = landAgricultural;
      this.landCommercial = landCommercial;
      this.landOthers = landOthers;
      this.underHarvest = underHarvest;
      this.owneds = owneds;
      this.projects = projects;
      this.crops = crops;
   }

   public String getLegalOwner() {
      return legalOwner;
   }

   public void setLegalOwner(String legalOwner) {
      this.legalOwner = legalOwner;
   }

   public String getTenure() {
      return tenure;
   }

   public void setTenure(String tenure) {
      this.tenure = tenure;
   }

   public String getLegalRight() {
      return legalRight;
   }

   public void setLegalRight(String legalRight) {
      this.legalRight = legalRight;
   }

   public String getResidential() {
      return residential;
   }

   public void setResidential(String residential) {
      this.residential = residential;
   }

   public String getAgricultural() {
      return agricultural;
   }

   public void setAgricultural(String agricultural) {
      this.agricultural = agricultural;
   }

   public String getCommercial() {
      return commercial;
   }

   public void setCommercial(String commercial) {
      this.commercial = commercial;
   }

   public String getLandowner() {
      return landowner;
   }

   public void setLandowner(String landowner) {
      this.landowner = landowner;
   }

   public String getLandResidential() {
      return landResidential;
   }

   public void setLandResidential(String landResidential) {
      this.landResidential = landResidential;
   }

   public String getLandAgricultural() {
      return landAgricultural;
   }

   public void setLandAgricultural(String landAgricultural) {
      this.landAgricultural = landAgricultural;
   }

   public String getLandCommercial() {
      return landCommercial;
   }

   public void setLandCommercial(String landCommercial) {
      this.landCommercial = landCommercial;
   }

   public String getLandOthers() {
      return landOthers;
   }

   public void setLandOthers(String landOthers) {
      this.landOthers = landOthers;
   }

   public String getUnderHarvest() {
      return underHarvest;
   }

   public void setUnderHarvest(String underHarvest) {
      this.underHarvest = underHarvest;
   }

   public ArrayList<Owned> getOwneds() {
      return owneds;
   }

   public void setOwneds(ArrayList<Owned> owneds) {
      this.owneds = owneds;
   }

   public ArrayList<Project> getProjects() {
      return projects;
   }

   public void setProjects(ArrayList<Project> projects) {
      this.projects = projects;
   }

   public ArrayList<Crop> getCrops() {
      return crops;
   }

   public void setCrops(ArrayList<Crop> crops) {
      this.crops = crops;
   }
}
