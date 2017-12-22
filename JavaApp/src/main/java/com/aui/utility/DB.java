package com.aui.utility;

import java.sql.*;

public class DB {

    public static final String COURSE = "coursesHashSet";
    public static final String FACULTY = "facultiesHashSet";
    public static final String SECTION = "sectionsHashSet";

    private static Statement statement = null;
    private static Connection connection = null;

    public static void init() throws SQLException {
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:csp.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            createTables();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e);
        }
    }

    private static void createTables() throws SQLException {
        createFacultiesTable();
        createCoursesTable();
        createSectionsTable();
    }

    private static void createFacultiesTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS faculties");
        statement.executeUpdate("CREATE TABLE faculties (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL" +
                ")");
    }

    private static void createCoursesTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS courses");
        statement.executeUpdate("CREATE TABLE courses (" +
                "id INTEGER PRIMARY KEY," +
                "code TEXT NOT NULL" +
                ")");
    }

    private static void createSectionsTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS sections");
        statement.executeUpdate("CREATE TABLE sections (" +
                "id INTEGER PRIMARY KEY," +
                "code TEXT NOT NULL," +
                "seats INTEGER," +
                "faculty_id INTEGER," +
                "course_id INTEGER," +
                "FOREIGN KEY (faculty_id) REFERENCES faculties(id)," +
                "FOREIGN KEY (course_id) REFERENCES courses(id)" +
                ")");
    }

    public static void get(String table) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + table);
        while (rs.next()) {
            // read the result set
            System.out.println("name = " + rs.getString("code"));
            System.out.println("id = " + rs.getInt("id"));
        }
    }

    public static void save(String table, String values) throws SQLException {
        statement.executeUpdate("insert into " + table + " values(" + values + ")");
    }
}
