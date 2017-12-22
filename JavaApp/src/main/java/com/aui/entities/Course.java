package com.aui.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Course {

    public String code;
    public Set<Section> sections = new HashSet<Section>();

    public Course(String code) {
        this.code = code;
    }

    public void addSection(Section section){
        this.sections.add(section);
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    public boolean isGrad(){
        return this.code.charAt(4) == '5';
    }
}
