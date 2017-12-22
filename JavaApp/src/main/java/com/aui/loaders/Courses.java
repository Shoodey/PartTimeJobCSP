package com.aui.loaders;

import com.aui.csps.Data;
import com.aui.entities.Course;
import com.aui.entities.Faculty;
import com.aui.entities.Section;

import java.io.FileNotFoundException;
import java.util.*;

public class Courses extends Loader {

    private static String file = "data/courses.csv";
    private static Set<Section> sectionsHashSet = new HashSet<Section>();

    public static void load() throws FileNotFoundException {
        scanner = init(file);
        skipHeaders();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> fields = new ArrayList<String>(Arrays.asList(line.split(",")));

            if (fields.size() >= 3 && (fields.size() - 1) % 2 == 0) {
                Faculty faculty = new Faculty(fields.remove(0));
                for (int i = 0; i < fields.size(); i += 2) {
                    int seats = setSeats(fields.get(i + 1));
                    String code = fields.get(i).trim();
                    Course course = setCourse(code);
                    Section section = setSection(faculty, course, code, seats);
                    sectionsHashSet.add(section);
                }
            } else {
                System.err.println("Invalid record in " + file);
            }
        }

        setData();

        scanner.close();
    }

    private static Course setCourse(String code) {
        String courseCode = code.substring(0, 8);
        return new Course(courseCode);
    }

    private static Section setSection(Faculty faculty, Course course, String code, int seats) {
        String sectionCode = code.substring(9);
        return new Section(faculty, course, sectionCode, seats);
    }

    private static int setSeats(String seats) {
        return Integer.parseInt(seats);
    }

    /**
     * Reverses the sectionsHashSet into a coursesHashSet set.
     *
     * @param sections The collected sectionsHashSet
     * @return Set<Course>
     */
    private static Set<Course> parseCourses(Set<Section> sections) {
        Set<Course> courses = new HashSet<Course>();

        for (Section section : sections) {
            courses.add(section.course);
        }

        for (Course course : courses) {
            for (Section section : sections) {
                if (course.code.equals(section.course.code)) {
                    course.addSection(section);
                }
            }
        }

        return courses;
    }

    /**
     * Reverses the sectionsHashSet into a facultiesHashSet set.
     *
     * @param sections The collected sectionsHashSet
     * @return Set<Course>
     */
    private static Set<Faculty> parseFaculties(Set<Section> sections) {
        Set<Faculty> faculties = new HashSet<Faculty>();

        for (Section section : sections) {
            faculties.add(section.faculty);
        }

        for (Faculty faculty : faculties) {
            for (Section section : sections) {
                if (faculty.name.equals(section.faculty.name)) {
                    faculty.addCourse(section.course);
                }
            }
        }

        for (Faculty faculty : faculties) {
            for (Section section : sections) {
                if (faculty.name.equals(section.faculty.name)) {
                    faculty.addSection(section);
                }
            }
        }

        return faculties;
    }

    private static void setData(){
        Data.faculties = parseFaculties(sectionsHashSet);
        Data.courses = parseCourses(sectionsHashSet);
        Data.sections =sectionsHashSet;
    }
}
