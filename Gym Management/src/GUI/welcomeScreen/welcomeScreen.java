/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.welcomeScreen;

/**
 *
 * @author User
 */
public class welcomeScreen {

    /*Username and password for admin account.
      They can change.
     */
    private final static String ADMINUSERNAME = "admin";
    private final static String ADMINPASSWORD = "password";
    protected long userID;
    protected String userPassword, adminPassword, adminUsername, type;

    protected boolean login() {
        boolean checker;
        if (type.equalsIgnoreCase("user")) {
            checker = checkUserInfo();
        } else {
            checker = checkAdminInfo();
        }

        return checker;
    }

    private boolean checkUserInfo() {
        if (Long.toString(userID).length() != 11) {
            return false;
        }
        return checkUserDB();
    }

    //Check login information for admin account.
    private boolean checkAdminInfo() {
        if (adminUsername.equalsIgnoreCase("") || adminPassword.equalsIgnoreCase("")) {
            return false;
        }
        return (adminUsername.equals(ADMINUSERNAME)
                && adminPassword.equals(ADMINPASSWORD));
    }

    //Will be added (Non-admin user implementation)
    private boolean checkUserDB() {
        return true;
    }
}
