/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.adminPage.addCustomer;

import dbCodes.databaseMethods;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author User
 */
public class newCustomer {

    databaseMethods connection = new databaseMethods();

    protected long id;
    protected int day, month, year;
    protected String name, surname, gender;

    protected boolean addCustomer() throws SQLException {
        boolean checker = checkInfo();
        if (checker) {
            connection.addCustomer(id, name, surname, gender, year);
            return true;
        } else {
            return false;
        }
    }

    protected void closeConnection() {
        connection.closeConnection();
    }
    
    //ID length can be changed.
    protected boolean checkInfo() {
        if (month == 2 && day > 28) {
            return false;
        }
        if (month > 12 || month < 1) {
            return false;
        }
        if (day > 31 || day < 1) {
            return false;
        }
        if (Long.toString(id).length() != 11) {
            return false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return !(this.year > currentYear || year < 1890);

    }

}
