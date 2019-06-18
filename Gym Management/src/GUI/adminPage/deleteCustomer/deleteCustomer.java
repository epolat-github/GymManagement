/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.adminPage.deleteCustomer;

import dbCodes.databaseMethods;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class deleteCustomer {

    databaseMethods connection;

    public deleteCustomer() {
        connection = new databaseMethods();
    }

    public boolean delete(long id) throws SQLException {
        return connection.deleteCustomer(id);
    }

}
