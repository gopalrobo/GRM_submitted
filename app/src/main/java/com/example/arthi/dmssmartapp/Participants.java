package com.example.arthi.dmssmartapp;

import java.io.Serializable;

public class Participants implements Serializable {

 public String householdHead;
 public String signature;
 public String date;
 public String witnessName;
 public String witnessSignature;
 public String witnessDate;
 public String surveyNameOne;
 public String surveySignatureOne;
 public String surveyDateOne;
 public String surveyNameTwo;
 public String surveySignatureTwo;
 public String surveyDateTwo;
 public String surveyNameThree;
 public String surveySignatureThree;
 public String surveyDateThree;

   public Participants(String householdHead, String signature, String date, String witnessName, String witnessSignature, String witnessDate, String surveyNameOne, String surveySignatureOne, String surveyDateOne, String surveyNameTwo, String surveySignatureTwo, String surveyDateTwo, String surveyNameThree, String surveySignatureThree, String surveyDateThree) {
      this.householdHead = householdHead;
      this.signature = signature;
      this.date = date;
      this.witnessName = witnessName;
      this.witnessSignature = witnessSignature;
      this.witnessDate = witnessDate;
      this.surveyNameOne = surveyNameOne;
      this.surveySignatureOne = surveySignatureOne;
      this.surveyDateOne = surveyDateOne;
      this.surveyNameTwo = surveyNameTwo;
      this.surveySignatureTwo = surveySignatureTwo;
      this.surveyDateTwo = surveyDateTwo;
      this.surveyNameThree = surveyNameThree;
      this.surveySignatureThree = surveySignatureThree;
      this.surveyDateThree = surveyDateThree;
   }

   public String getHouseholdHead() {
      return householdHead;
   }

   public void setHouseholdHead(String householdHead) {
      this.householdHead = householdHead;
   }

   public String getSignature() {
      return signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getWitnessName() {
      return witnessName;
   }

   public void setWitnessName(String witnessName) {
      this.witnessName = witnessName;
   }

   public String getWitnessSignature() {
      return witnessSignature;
   }

   public void setWitnessSignature(String witnessSignature) {
      this.witnessSignature = witnessSignature;
   }

   public String getWitnessDate() {
      return witnessDate;
   }

   public void setWitnessDate(String witnessDate) {
      this.witnessDate = witnessDate;
   }

   public String getSurveyNameOne() {
      return surveyNameOne;
   }

   public void setSurveyNameOne(String surveyNameOne) {
      this.surveyNameOne = surveyNameOne;
   }

   public String getSurveySignatureOne() {
      return surveySignatureOne;
   }

   public void setSurveySignatureOne(String surveySignatureOne) {
      this.surveySignatureOne = surveySignatureOne;
   }

   public String getSurveyDateOne() {
      return surveyDateOne;
   }

   public void setSurveyDateOne(String surveyDateOne) {
      this.surveyDateOne = surveyDateOne;
   }

   public String getSurveyNameTwo() {
      return surveyNameTwo;
   }

   public void setSurveyNameTwo(String surveyNameTwo) {
      this.surveyNameTwo = surveyNameTwo;
   }

   public String getSurveySignatureTwo() {
      return surveySignatureTwo;
   }

   public void setSurveySignatureTwo(String surveySignatureTwo) {
      this.surveySignatureTwo = surveySignatureTwo;
   }

   public String getSurveyDateTwo() {
      return surveyDateTwo;
   }

   public void setSurveyDateTwo(String surveyDateTwo) {
      this.surveyDateTwo = surveyDateTwo;
   }

   public String getSurveyNameThree() {
      return surveyNameThree;
   }

   public void setSurveyNameThree(String surveyNameThree) {
      this.surveyNameThree = surveyNameThree;
   }

   public String getSurveySignatureThree() {
      return surveySignatureThree;
   }

   public void setSurveySignatureThree(String surveySignatureThree) {
      this.surveySignatureThree = surveySignatureThree;
   }

   public String getSurveyDateThree() {
      return surveyDateThree;
   }

   public void setSurveyDateThree(String surveyDateThree) {
      this.surveyDateThree = surveyDateThree;
   }
}
