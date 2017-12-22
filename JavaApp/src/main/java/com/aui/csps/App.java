package com.aui.csps;

import com.aui.entities.*;
import com.aui.utility.Screen;

import java.io.FileNotFoundException;


public class App {
    public static final String BLUE = "\u001B[34m";
    public static void main(String[] args) throws FileNotFoundException {

//        Screen.displayGreetings();

        Data.load();
//        applyHardConstraints();
    }

    private static void applyHardConstraints(){
        for (Request request : Data.requests) {
            int purpose = request.purpose; // 1: grading, 2: research, 3: both
            if(purpose == 2 || purpose == 3){
                findResearcher(request);
            }
            if(purpose == 1){
                findGrader(request);
            }
        }
    }

    private static void findGrader(Request request){
        for(Course course : request.faculty.courses){
            if(course.isGrad()) return;

            for(Student student : request.students){
                for(Grade grade : Data.grades){
                    if(grade.student.equals(student) && student.quota >= request.quota*16){
                        System.out.println(student.level + " allocated to " + request.faculty.name + " as grader (Preference)");
                        updateStudent(student, request.quota);
                        return;
                    }
                }
            }

            for(Grade grade : Data.grades){
                if(course.equals(grade.course) && grade.student.quota >= request.quota*16){
                    System.out.println(grade.student.level + " allocated to " + request.faculty.name + " as grader (No Preference)");
                    updateStudent(grade.student, request.quota);
                    return;
                }
            }
        }
    }

    private static void findResearcher(Request request){
        for(Student student : request.students){
            if(student.level == Student.LEVEL_GRAD  && student.quota >= request.quota*16){
                System.out.println(student.level + " allocated to " + request.faculty.name + " as researcher (Preference)");
                updateStudent(student, request.quota);
                return;
            }
        }

        for(Student student : Data.students){
            if(student.level == Student.LEVEL_GRAD  && student.quota >= request.quota*16){
                System.out.println(student.level + " allocated to " + request.faculty.name + " as researcher (No Preference)");
                updateStudent(student, request.quota);
                return;
            }
        }

    }

    private static void updateStudent(Student student, double quota){
        for(Student s : Data.students){
            if(s.equals(student)){
                s.quota -= (quota*16);
                s.work += (quota*16);
                System.out.println(student.name + " new quota: " + s.work + "/" + s.const_quota);
                return;
            }
        }
    }
}
