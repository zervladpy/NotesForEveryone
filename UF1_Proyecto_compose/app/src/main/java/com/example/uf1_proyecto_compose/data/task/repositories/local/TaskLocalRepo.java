package com.example.uf1_proyecto_compose.data.task.repositories.local;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskLocalRepo {

    private static TaskLocalRepo instance;
    private final String jdbc;
    private File file;
    private Connection conn;

    private TaskLocalRepo() {
        this.jdbc = "jdbc:sqlite:";
    }

    public static TaskLocalRepo getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (TaskLocalRepo.class) {
            if (instance == null) {
                instance = new TaskLocalRepo();
            }
        }

        return instance;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Connection getConnection() throws Exception {

        if (file == null) {
            throw new Exception("No file was specified");
        }

        try {

            DriverManager.registerDriver(
                    (Driver) Class.forName("org.sqlite.JDBC").newInstance()
            );

            System.out.println(jdbc + "notes4everyone.sqlite3");
            conn = DriverManager.getConnection(jdbc + file.getAbsolutePath());
        } catch (SQLException e) {
            throw new SQLException("Error establishing database connection", e);
        }

        return conn;

    }

    void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}