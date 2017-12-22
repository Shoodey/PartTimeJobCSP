package com.aui.entities;

public class Section {

    public Faculty faculty;
    public Course course;
    public String code;
    public int seats;

    public Section(Faculty faculty, Course course, String code, int seats) {
        this.faculty = faculty;
        this.course = course;
        this.code = code;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Section{" +
                "faculty=" + faculty +
                ", course=" + course +
                ", code='" + code + '\'' +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (seats != section.seats) return false;
        if (faculty != null ? !faculty.equals(section.faculty) : section.faculty != null) return false;
        if (course != null ? !course.equals(section.course) : section.course != null) return false;
        return code != null ? code.equals(section.code) : section.code == null;
    }

    @Override
    public int hashCode() {
        int result = faculty != null ? faculty.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + seats;
        return result;
    }
}
