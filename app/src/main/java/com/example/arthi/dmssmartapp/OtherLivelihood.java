package com.example.arthi.dmssmartapp;

import java.io.Serializable;

public class OtherLivelihood implements Serializable {

 public String otherLivelihood;
 public String businessShop;
 public String businessFood;
 public String businessMechanic;
 public String businessCafé;
 public String businessOther;

 public String membersMale;
 public String membersFemale;

 public String earnings;
 public String businessRegistered;

   public OtherLivelihood(String otherLivelihood, String businessShop, String businessFood, String businessMechanic, String businessCafé, String businessOther, String membersMale, String membersFemale, String earnings, String businessRegistered) {
      this.otherLivelihood = otherLivelihood;
      this.businessShop = businessShop;
      this.businessFood = businessFood;
      this.businessMechanic = businessMechanic;
      this.businessCafé = businessCafé;
      this.businessOther = businessOther;
      this.membersMale = membersMale;
      this.membersFemale = membersFemale;
      this.earnings = earnings;
      this.businessRegistered = businessRegistered;
   }

   public String getOtherLivelihood() {
      return otherLivelihood;
   }

   public void setOtherLivelihood(String otherLivelihood) {
      this.otherLivelihood = otherLivelihood;
   }

   public String getBusinessShop() {
      return businessShop;
   }

   public void setBusinessShop(String businessShop) {
      this.businessShop = businessShop;
   }

   public String getBusinessFood() {
      return businessFood;
   }

   public void setBusinessFood(String businessFood) {
      this.businessFood = businessFood;
   }

   public String getBusinessMechanic() {
      return businessMechanic;
   }

   public void setBusinessMechanic(String businessMechanic) {
      this.businessMechanic = businessMechanic;
   }

   public String getBusinessCafé() {
      return businessCafé;
   }

   public void setBusinessCafé(String businessCafé) {
      this.businessCafé = businessCafé;
   }

   public String getBusinessOther() {
      return businessOther;
   }

   public void setBusinessOther(String businessOther) {
      this.businessOther = businessOther;
   }

   public String getMembersMale() {
      return membersMale;
   }

   public void setMembersMale(String membersMale) {
      this.membersMale = membersMale;
   }

   public String getMembersFemale() {
      return membersFemale;
   }

   public void setMembersFemale(String membersFemale) {
      this.membersFemale = membersFemale;
   }

   public String getEarnings() {
      return earnings;
   }

   public void setEarnings(String earnings) {
      this.earnings = earnings;
   }

   public String getBusinessRegistered() {
      return businessRegistered;
   }

   public void setBusinessRegistered(String businessRegistered) {
      this.businessRegistered = businessRegistered;
   }
}
