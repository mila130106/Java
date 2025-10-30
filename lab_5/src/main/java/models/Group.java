package models;

public class Group implements Comparable<Group> {
    private String code;
    private String faculty;
    private int year;

    public Group(String code, String faculty, int year) {
        this.code = code;
        this.faculty = faculty;
        this.year = year;
    }

    public String getCode() { return code; }
    public String getFaculty() { return faculty; }
    public int getYear() { return year; }

    @Override
    public int compareTo(Group other) {
        // Основне поле — code
        return this.code.compareTo(other.code);
    }

    @Override
    public String toString() {
        return "Group " + code + " (" + faculty + ", year " + year + ")";
    }
}
