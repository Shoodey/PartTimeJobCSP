package com.aui.loaders;

import com.aui.csps.Data;
import com.aui.entities.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Requests extends Loader {

    private static String file = "data/requests.csv";
    private static Set<Request> requestsHashSet = new HashSet<Request>();

    public static void load() throws FileNotFoundException {
        scanner = init(file);
        skipHeaders();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> fields = new ArrayList<String>(Arrays.asList(line.split(",")));

            if (fields.size() >= 2) {
                Faculty faculty = setFaculty(fields.get(0));
                int purpose = setPurpose(fields.get(1));
                Set<Student> students = setStudents(fields.get(2));
                double quota;
                if(fields.size() >= 4){
                    quota = setQuota(fields.get(3));
                }else{
                    quota = 10;
                }

                requestsHashSet.add(new Request(faculty, purpose, students, quota));

            } else {
                System.err.println("Invalid record in " + file);
            }
        }

        setData();

        scanner.close();
    }

    private static Faculty setFaculty(String name) {
        for (Faculty faculty : Data.faculties) {
            if (faculty.name.equals(name)) {
                return faculty;
            }
        }
        return null;
    }

    private static int setPurpose(String purpose) {
        if (purpose.equals("grading")) {
            return Request.PURPOSE_GRADING;
        } else if (purpose.equals("research")) {
            return Request.PURPOSE_RESEARCH;
        } else if (purpose.equals("grading/research")) {
            return Request.PURPOSE_BOTH;
        }

        return 0;
    }

    private static Set<Student> setStudents(String names) {
        Set<Student> students = new HashSet<Student>();

        String[] namesArray = names.trim().split(" ");

        for (String name : namesArray) {
            for (Student student : Data.students) {
                if (student.name.equals(name) && name.length() > 0) {
                    students.add(student);
                }
            }
        }

        return students;
    }

    private static double setQuota(String litteralQuota){
        try {
            return Double.parseDouble(litteralQuota);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    private static void setData(){
        Data.requests = requestsHashSet;
    }
}
