/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbCodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class databaseMethods {

    Connection con;
    Statement stmt;

    public databaseMethods() {
        //setting the Oracle Database connection.
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##EPOLAT", "aassddff");
            stmt = con.createStatement();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(databaseMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Couldn't reach to the database!\n\n\tERRORS MAY OCCUR",
                    "Database Error", 2);
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't close connection.");
        }
    }
    
    public void addCustomer(long id, String name, String surname,
            String gender, int year) throws SQLException {

        String query = String.format("INSERT INTO CUSTOMER (CUSTOMERID, NAME, SURNAME, GENDER, BIRTHYEAR) "
                + "VALUES (%d, '%s', '%s', '%s', %d)", id, name, surname, gender, year);
        int updated = stmt.executeUpdate(query);
        if (updated == 1) {
            System.out.println("updated");
        } else {
            System.out.println("not updated");
        }
    }

    public boolean deleteCustomer(long id) throws SQLException {
        String query = String.format("DELETE FROM CUSTOMER "
                + "WHERE CUSTOMERID = %d", id);

        int updated = stmt.executeUpdate(query);
        return updated == 1;
    }

    public void addCourse(String code, String name, String type) throws SQLException {
        String query = String.format("INSERT INTO COURSE (CODE, NAME, TYPE)"
                + "VALUES ('%s', '%s', '%s')", code, name, type);
        int updated = stmt.executeUpdate(query);
    }

    public void deleteCourse(String code) throws SQLException {
        String queryDeleteCourse = String.format("DELETE FROM COURSE WHERE CODE = '%s'", code);
        String queryDeleteSection = String.format("DELETE FROM SECTION WHERE CODE = '%s'", code);
        stmt.executeUpdate(queryDeleteCourse);
        stmt.executeQuery(queryDeleteSection);
    }

    public boolean updateCourseType(String code, String type) throws SQLException {
        String query = String.format("UPDATE COURSE SET TYPE = '%s' "
                + "WHERE CODE = '%s'", type, code);
        int updated = stmt.executeUpdate(query);
        return updated == 1;
    }

    public boolean addSection(String code, int count) throws SQLException {
        int maxSecNum = 0;
        String maxSectionQuery = "SELECT MAX(SECTIONNUMBER) FROM SECTION";
        ResultSet rs = stmt.executeQuery(maxSectionQuery);
        while (rs.next()) {
            maxSecNum = rs.getInt(1);
        }

        String insertQuery = String.format("INSERT INTO SECTION (SECTIONNUMBER, TIME, CODE)"
                + "VALUES (%d, %d, '%s')", (maxSecNum + 1), count, code);
        int updated = stmt.executeUpdate(insertQuery);
        return updated == 1;
    }

    public boolean assignSection(int secNumber, long customerID) throws SQLException {
        String query = String.format("INSERT INTO TAKES (SECNUMBER, CUSTOMERID) "
                + "VALUES (%d, %d)", secNumber, customerID);
        int updated = stmt.executeUpdate(query);
        return updated == 1;
    }

    public void updateName(long customerID, String newName) throws SQLException {
        String query = String.format("UPDATE CUSTOMER SET NAME = '%s'"
                + " WHERE CUSTOMERID = %d", newName, customerID);
        stmt.executeUpdate(query);
    }

    public void updateSurname(long customerID, String newSurname) throws SQLException {
        String query = String.format("UPDATE CUSTOMER SET SURNAME = '%s'"
                + " WHERE CUSTOMERID = %d", newSurname, customerID);
        stmt.executeUpdate(query);
    }

    public void updateGender(long customerID, String newGender) throws SQLException {
        String query = String.format("UPDATE CUSTOMER SET GENDER = '%s'"
                + " WHERE CUSTOMERID = %d", newGender, customerID);
        stmt.executeUpdate(query);
    }

    public ResultSet listCustomers() throws SQLException {
        String query = "SELECT\n"
                + "TAKES.CUSTOMERID CUSTOMERID,\n"
                + "CUSTOMER.NAME NAME,\n"
                + "CUSTOMER.SURNAME SURNAME,\n"
                + "CUSTOMER.GENDER GENDER,\n"
                + "CUSTOMER.BIRTHYEAR BIRTHYEAR,\n"
                + "SECTION.CODE CODE,\n"
                + "SECTION.SECTIONNUMBER SECTIONNUMBER,\n"
                + "SECTION.TIME TIME\n"
                + "FROM TAKES INNER JOIN CUSTOMER ON takes.customerıd = customer.customerıd\n"
                + "INNER JOIN SECTION ON takes.secnumber = sectıon.sectıonnumber\n"
                + "ORDER BY customer.customerıd";
        return stmt.executeQuery(query);
    }
}
