package com.dw.jdbcapp.model;

public class MileGrade {
    String gradeName;
    int gradeLower;
    int gradeHigher;

    public MileGrade() {
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeLower() {
        return gradeLower;
    }

    public void setGradeLower(int gradeLower) {
        this.gradeLower = gradeLower;
    }

    public int getGradeHigher() {
        return gradeHigher;
    }

    public void setGradeHigher(int gradeHigher) {
        this.gradeHigher = gradeHigher;
    }

    @Override
    public String toString() {
        return "MileGrade{" +
                "gradeName='" + gradeName + '\'' +
                ", gradeLower='" + gradeLower + '\'' +
                ", gradeHigher='" + gradeHigher + '\'' +
                '}';
    }
}
