package com.example.arthi.dmssmartapp;

import com.example.arthi.dmssmartapp.OneHouseholdData.HouseholdDate;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.AffectedStructure;
import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;

import java.io.Serializable;

public class Mainbean implements Serializable {
    String id;

    HouseholdDate householdDate;
    AffectedLand affectedLand;
    AffectedStructure affectedStructure;
    OtherLivelihood otherLivelihood;
    Participants participants;
    Questionnaire questionnaire;
    ResettlementOption resettlementOption;

    public Mainbean(HouseholdDate householdDate, AffectedLand affectedLand, AffectedStructure affectedStructure, OtherLivelihood otherLivelihood, Participants participants, Questionnaire questionnaire, ResettlementOption resettlementOption) {
        this.householdDate = householdDate;
        this.affectedLand = affectedLand;
        this.affectedStructure = affectedStructure;
        this.otherLivelihood = otherLivelihood;
        this.participants = participants;
        this.questionnaire = questionnaire;
        this.resettlementOption = resettlementOption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HouseholdDate getHouseholdDate() {
        return householdDate;
    }

    public void setHouseholdDate(HouseholdDate householdDate) {
        this.householdDate = householdDate;
    }

    public AffectedLand getAffectedLand() {
        return affectedLand;
    }

    public void setAffectedLand(AffectedLand affectedLand) {
        this.affectedLand = affectedLand;
    }

    public AffectedStructure getAffectedStructure() {
        return affectedStructure;
    }

    public void setAffectedStructure(AffectedStructure affectedStructure) {
        this.affectedStructure = affectedStructure;
    }

    public OtherLivelihood getOtherLivelihood() {
        return otherLivelihood;
    }

    public void setOtherLivelihood(OtherLivelihood otherLivelihood) {
        this.otherLivelihood = otherLivelihood;
    }

    public Participants getParticipants() {
        return participants;
    }

    public void setParticipants(Participants participants) {
        this.participants = participants;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public ResettlementOption getResettlementOption() {
        return resettlementOption;
    }

    public void setResettlementOption(ResettlementOption resettlementOption) {
        this.resettlementOption = resettlementOption;
    }
}