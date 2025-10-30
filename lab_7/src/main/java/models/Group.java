package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Group {
    @JsonProperty
    private String code;
    @JsonProperty
    private String faculty;
    @JsonProperty
    private int year;

    public Group() {} // для Jackson

    public Group(String code, String faculty, int year) {
        this.code = code;
        this.faculty = faculty;
        this.year = year;
    }

    public String getCode() { return code; }
    public String getFaculty() { return faculty; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Group " + code + " (" + faculty + ", year " + year + ")";
    }
}
