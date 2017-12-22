package com.aui.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Faculty {

    public String name;
    public Set<Course> courses = new HashSet<Course>();
    public Set<Section> sections = new HashSet<Section>();

    public Faculty(String name) {
        this.name = name;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public void addSection(Section section){
        this.sections.add(section);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        return name.equals(faculty.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
