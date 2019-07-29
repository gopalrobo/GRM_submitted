package com.example.arthi.dmssmartapp.TwoAffectedLand;

import java.io.Serializable;

/**
 * Created by arthi on 12/3/2018.
 */

public class Project implements Serializable {
public String typesOfProject;
public String projectAreaaA;
public String projectAreaB;

    public Project(String typesOfProject, String projectAreaaA, String projectAreaB) {
        this.typesOfProject = typesOfProject;
        this.projectAreaaA = projectAreaaA;
        this.projectAreaB = projectAreaB;
    }

    public String getTypesOfProject() {
        return typesOfProject;
    }

    public void setTypesOfProject(String typesOfProject) {
        this.typesOfProject = typesOfProject;
    }

    public String getProjectAreaaA() {
        return projectAreaaA;
    }

    public void setProjectAreaaA(String projectAreaaA) {
        this.projectAreaaA = projectAreaaA;
    }

    public String getProjectAreaB() {
        return projectAreaB;
    }

    public void setProjectAreaB(String projectAreaB) {
        this.projectAreaB = projectAreaB;
    }
}