package com.aui.loaders;

import com.aui.csps.Data;
import com.aui.entities.Student;

import java.io.FileNotFoundException;
import java.util.*;

public class Students extends Loader {

    private static String file = "data/students.csv";

    private static Set<Student> studentsHashSet = new HashSet<Student>();


    public static void load() throws FileNotFoundException {
        scanner = init(file);
        skipHeaders();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> fields = new ArrayList<String>(Arrays.asList(line.split(",")));

            if (fields.size() == 6) {
                int id = Integer.parseInt(fields.get(0));

                String name = fields.get(1);

                int level = 0;
                if (fields.get(2).equals("GRA")) {
                    level = Student.LEVEL_GRAD;
                } else if (fields.get(2).equals("UG")) {
                    level = Student.LEVEL_UNDER;
                } else if (fields.get(2).equals("UG/GRA")) {
                    level = Student.LEVEL_BOTH;
                } else {
                    System.err.println("Invalid record in " + file);
                }

                String major = fields.get(3);

                int quota = Integer.parseInt(fields.get(4));

                String status =  fields.get(5);

                studentsHashSet.add(new Student(id, name, level, major, quota, status));
            } else {
                System.err.println("Invalid record in " + file);
            }
        }

        setData();

        scanner.close();
    }

    private static void setData(){
        Data.students = studentsHashSet;
    }
}
