package com.example.arthi.dmssmartapp;

import java.io.Serializable;

public class ResettlementOption implements Serializable {

public String affectedLand;
public String affectedCompensation;
public String lossOfCrops;

public String eligiblehouseholds;
public String proximity;
public String district;
public String sameprovince;
public String accesstoSchools;
public String market;
public String area;
public String pagoda;
public String community;

public String groupRelocation;
public String individualRelocation;
public String selfRelocation;

   public ResettlementOption(String affectedLand, String affectedCompensation, String lossOfCrops, String eligiblehouseholds, String proximity, String district, String sameprovince, String accesstoSchools, String market, String area, String pagoda, String community, String groupRelocation, String individualRelocation, String selfRelocation) {
      this.affectedLand = affectedLand;
      this.affectedCompensation = affectedCompensation;
      this.lossOfCrops = lossOfCrops;
      this.eligiblehouseholds = eligiblehouseholds;
      this.proximity = proximity;
      this.district = district;
      this.sameprovince = sameprovince;
      this.accesstoSchools = accesstoSchools;
      this.market = market;
      this.area = area;
      this.pagoda = pagoda;
      this.community = community;
      this.groupRelocation = groupRelocation;
      this.individualRelocation = individualRelocation;
      this.selfRelocation = selfRelocation;
   }

   public String getAffectedLand() {
      return affectedLand;
   }

   public void setAffectedLand(String affectedLand) {
      this.affectedLand = affectedLand;
   }

   public String getAffectedCompensation() {
      return affectedCompensation;
   }

   public void setAffectedCompensation(String affectedCompensation) {
      this.affectedCompensation = affectedCompensation;
   }

   public String getLossOfCrops() {
      return lossOfCrops;
   }

   public void setLossOfCrops(String lossOfCrops) {
      this.lossOfCrops = lossOfCrops;
   }

   public String getEligiblehouseholds() {
      return eligiblehouseholds;
   }

   public void setEligiblehouseholds(String eligiblehouseholds) {
      this.eligiblehouseholds = eligiblehouseholds;
   }

   public String getProximity() {
      return proximity;
   }

   public void setProximity(String proximity) {
      this.proximity = proximity;
   }

   public String getDistrict() {
      return district;
   }

   public void setDistrict(String district) {
      this.district = district;
   }

   public String getSameprovince() {
      return sameprovince;
   }

   public void setSameprovince(String sameprovince) {
      this.sameprovince = sameprovince;
   }

   public String getAccesstoSchools() {
      return accesstoSchools;
   }

   public void setAccesstoSchools(String accesstoSchools) {
      this.accesstoSchools = accesstoSchools;
   }

   public String getMarket() {
      return market;
   }

   public void setMarket(String market) {
      this.market = market;
   }

   public String getArea() {
      return area;
   }

   public void setArea(String area) {
      this.area = area;
   }

   public String getPagoda() {
      return pagoda;
   }

   public void setPagoda(String pagoda) {
      this.pagoda = pagoda;
   }

   public String getCommunity() {
      return community;
   }

   public void setCommunity(String community) {
      this.community = community;
   }

   public String getGroupRelocation() {
      return groupRelocation;
   }

   public void setGroupRelocation(String groupRelocation) {
      this.groupRelocation = groupRelocation;
   }

   public String getIndividualRelocation() {
      return individualRelocation;
   }

   public void setIndividualRelocation(String individualRelocation) {
      this.individualRelocation = individualRelocation;
   }

   public String getSelfRelocation() {
      return selfRelocation;
   }

   public void setSelfRelocation(String selfRelocation) {
      this.selfRelocation = selfRelocation;
   }
}
