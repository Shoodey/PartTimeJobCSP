package com.aui.csps;

import com.aui.entities.*;
import com.aui.loaders.Courses;
import com.aui.loaders.Grades;
import com.aui.loaders.Requests;
import com.aui.loaders.Students;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class Data {

    public static Set<Faculty> faculties;
    public static Set<Course> courses;
    public static Set<Section> sections;
    public static Set<Student> students;
    public static Set<Request> requests;
    public static Set<Grade> grades;

    static void load() throws FileNotFoundException {
        Courses.load();
        Students.load();
        Requests.load();
        Grades.load();
        checkData();
    }

    private static void checkData() {
        System.out.println(sections.size());
        System.out.println(courses.size());
        System.out.println(faculties.size());
        System.out.println(students.size());
        System.out.println(requests.size());
        System.out.println(grades.size());
    }
}
