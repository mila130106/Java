package models;

import exceptions.InvalidDataException;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String code;
    private String faculty;
    private int year;

    public Group(String code, String faculty, int year) throws InvalidDataException {
        List<String> errors = new ArrayList<>();
        if (code == null || code.isEmpty()) errors.add("code: cannot be empty");
        if (faculty == null || faculty.isEmpty()) errors.add("faculty: cannot be empty");
        if (year <= 0) errors.add("year: must be > 0");

        if (!errors.isEmpty()) {
            LoggerUtil.log("Validation failed: " + String.join("; ", errors));
            throw new InvalidDataException(errors);
        }

        this.code = code;
        this.faculty = faculty;
        this.year = year;
        LoggerUtil.log("Group created successfully: " + this);
    }

    public String getCode() { return code; }
    public void setCode(String code) throws InvalidDataException {
        if (code == null || code.isEmpty()) {
            LoggerUtil.log("Invalid code update attempt");
            throw new InvalidDataException(List.of("code: cannot be empty"));
        }
        this.code = code;
        LoggerUtil.log("Code updated: " + code);
    }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) throws InvalidDataException {
        if (faculty == null || faculty.isEmpty()) {
            LoggerUtil.log("Invalid faculty update attempt");
            throw new InvalidDataException(List.of("faculty: cannot be empty"));
        }
        this.faculty = faculty;
        LoggerUtil.log("Faculty updated: " + faculty);
    }

    public int getYear() { return year; }
    public void setYear(int year) throws InvalidDataException {
        if (year <= 0) {
            LoggerUtil.log("Invalid year update attempt");
            throw new InvalidDataException(List.of("year: must be > 0"));
        }
        this.year = year;
        LoggerUtil.log("Year updated: " + year);
    }

    @Override
    public String toString() {
        return "Group " + code + " (" + faculty + ", year " + year + ")";
    }
}
