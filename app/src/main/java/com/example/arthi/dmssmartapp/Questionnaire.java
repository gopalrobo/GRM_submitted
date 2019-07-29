package com.example.arthi.dmssmartapp;

import com.example.arthi.dmssmartapp.OneHouseholdData.HouseholdDate;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.AffectedStructure;
import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;

import java.io.Serializable;

public class Questionnaire implements Serializable {
  public String surveyId;
  public String questionNo;
  public String province;
  public String district;
  public String commune;
  public String nameOfHouseholdHead;
  public String idorFamilyCardNo;

   public Questionnaire(String surveyId, String questionNo, String province, String district, String commune, String nameOfHouseholdHead, String idorFamilyCardNo) {
      this.surveyId = surveyId;
      this.questionNo = questionNo;
      this.province = province;
      this.district = district;
      this.commune = commune;
      this.nameOfHouseholdHead = nameOfHouseholdHead;
      this.idorFamilyCardNo = idorFamilyCardNo;
   }

   public String getSurveyId() {
      return surveyId;
   }

   public void setSurveyId(String surveyId) {
      this.surveyId = surveyId;
   }

   public String getQuestionNo() {
      return questionNo;
   }

   public void setQuestionNo(String questionNo) {
      this.questionNo = questionNo;
   }

   public String getProvince() {
      return province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getDistrict() {
      return district;
   }

   public void setDistrict(String district) {
      this.district = district;
   }

   public String getCommune() {
      return commune;
   }

   public void setCommune(String commune) {
      this.commune = commune;
   }

   public String getNameOfHouseholdHead() {
      return nameOfHouseholdHead;
   }

   public void setNameOfHouseholdHead(String nameOfHouseholdHead) {
      this.nameOfHouseholdHead = nameOfHouseholdHead;
   }

   public String getIdorFamilyCardNo() {
      return idorFamilyCardNo;
   }

   public void setIdorFamilyCardNo(String idorFamilyCardNo) {
      this.idorFamilyCardNo = idorFamilyCardNo;
   }
}
