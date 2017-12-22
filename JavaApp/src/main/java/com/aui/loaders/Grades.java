package com.aui.loaders;

import com.aui.csps.Data;
import com.aui.entities.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class Grades extends Loader {
    private static String file = "data/grades.csv";
    private static Set<Grade> gradesHashSet = new HashSet<Grade>();
    private static List<String> eligibleGrades = new ArrayList<String>(Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "P"));

    public static void load() throws FileNotFoundException {
        scanner = init(file);

        ArrayList<String> headers = getHeaders(scanner.nextLine());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ArrayList<String> fields = new ArrayList<String>(Arrays.asList(line.split(",")));

            Student student = setStudent(fields.remove(0));

            for (int i = 0; i < fields.size(); i += 2) {
                String letterGrade = fields.get(i);
                if (eligibleGrades.contains(letterGrade)) {
                    String courseCode = headers.get(i / 2);
                    String taken = fields.get(i + 1);
                    Grade grade = setGrade(student, courseCode, letterGrade, taken);

                    if(grade != null)
                        gradesHashSet.add(grade);
                }
            }

        }

        setData();

        scanner.close();
    }

    private static ArrayList<String> getHeaders(String line) {
        ArrayList<String> headers = new ArrayList<String>();
        ArrayList<String> allHeaders = new ArrayList<String>(Arrays.asList(line.trim().split(",")));
        allHeaders.remove(0); // Remove the empty cell value

        for (String header : allHeaders) {
            if (!header.equals("WHEN TAKEN")) {
                if (header.length() == 7) {
                    String head = header.substring(0, 3);
                    String tail = header.substring(3, 7);
                    headers.add(StringUtils.upperCase(head) + " " + tail);
                } else {
                    headers.add(header.substring(0, 8));
                }
            }
        }

        return headers;
    }

    private static Student setStudent(String name) {
        for (Student student : Data.students) {
            if (student.name.equals(name)) {
                return student;
            }
        }

        return null;
    }

    private static Grade setGrade(Student student, String courseCode, String grade, String takenLiteral) {
        Course course = null;

        for (Course c : Data.courses) {
            if (c.code.equals(courseCode))
                course = c;
        }

        if (course == null) return null;

        int taken;
        try {
            taken = Integer.parseInt(takenLiteral);
        } catch (NumberFormatException ignored) {
            return null;
        }

        return new Grade(student, course, grade, taken);
    }

    private static void setData(){
        Data.grades = gradesHashSet;
    }
}
