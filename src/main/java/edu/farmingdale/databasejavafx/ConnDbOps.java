package edu.farmingdale.databasejavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods for database use.
 * @author MoaathAlrajab
 * @author Jason Devaraj
 */
public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://csc311devarajjava12.mysql.database.azure.com/";
    final String DB_URL = MYSQL_SERVER_URL + "DBname";
    final String USERNAME = "reapply";
    final String PASSWORD = "gnGFVdyMCgp1ra";

    /**
     * Method to connect to a database.
     * @return
     */
    public  boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        //Class.forName("com.mysql.jdbc.Driver");
        try {
            System.out.println("Trying to connect.");
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            System.out.println("Connected.");
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS DBname");
            System.out.println("Databse created.");
            statement.close();
            conn.close();
            System.out.println("Closed");
            //Second, connect to the database and create the table "users" if not created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT( 10 ) NOT NULL PRIMARY KEY,"
                    + "first_name VARCHAR(200) NOT NULL,"
                    + "last_name VARCHAR(200) NOT NULL,"
                    + "department VARCHAR(200),"
                    + "major VARCHAR(200)"
                    + ")";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasRegistredUsers;
    }

    /**
     * Method to search for a student using an id number.
     * @param id
     */
    public  void queryUserById(String id) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Select all where the id matches the student
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idNum = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String department = resultSet.getString("department");
                String major = resultSet.getString("major");
                System.out.println("ID: " + idNum + ", First name: " + firstName + "Last name" + lastName + ", Department: " + department + ", Major: " + major);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to search for a student using an id number.
     * @param id
     * @return
     */
    public String queryUser(String id) {
        String completeString = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Select all where the id matches the student
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idNum = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String department = resultSet.getString("department");
                String major = resultSet.getString("major");
                completeString = "ID: " + idNum + ", First name: " + firstName + ", Last name: " + lastName + ", Department: " + department + ", Major: " + major;
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completeString;
    }

    /**
     * Method to delete a user.
     * @param id
     */
    public void delete(String id) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Delete a specific user whose id matches a student.
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int deleted = preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to list all the users.
     */
    public  void listAllUsers() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idNum = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String department = resultSet.getString("department");
                String major = resultSet.getString("major");
                System.out.println("ID: " + idNum + ", First name: " + firstName + "Last name" + lastName + ", Department: " + department + ", Major: " + major);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to return a list that contains all users in the database.
     * @return A list of type person
     */
    public List<Person> displayAllUsers() {
        List<Person> users = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idNum = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String department = resultSet.getString("department");
                String major = resultSet.getString("major");
                users.add(new Person(idNum, firstName, lastName, department, major));
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Inserts a user into the database.
     * @param id
     * @param first_name
     * @param last_name
     * @param department
     * @param major
     */
    public  void insertUser(String id, String first_name, String last_name, String department, String major) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO users (id, first_name, last_name, department, major) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, department);
            preparedStatement.setString(5, major);

            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits a student in the database if given the id number.
     * @param idNum
     * @param firstName
     * @param lastName
     * @param dept
     * @param major
     * @throws SQLException
     */
    public void editUser(String idNum, String firstName, String lastName, String dept, String major) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, department = ?, major = ? WHERE id = ?";;
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(5, idNum);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, dept);
            preparedStatement.setString(4, major);

            int rowsUpdated = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}