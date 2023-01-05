package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;
import java.util.HashSet;

import bgu.spl.net.impl.stomp.StompExceptions.ConnectionException;


/**
 * This class is responsible for managing the users of the system.
 */
public class UserController {

    private volatile HashMap<String,String> users;
    private volatile HashSet<String> loggedInUsers;

    public UserController() {
        users = new HashMap<String,String>();
        loggedInUsers = new HashSet<String>();
    }

    /**
     * Add a new user to the system.
     * @param username
     * @param password
     * @throws ConnectionException if the user already exists
     */
    public void addUser(String username, String password) throws ConnectionException {
        username = username.toLowerCase(); // Is this necessary? 
        if (users.containsKey(username)) {
            throw new ConnectionException("User already exists");
        }
        users.put(username, password);
    }

    /**
     * Check if a user exists in the system.
     * @param username
     * @return true if the user exists, false otherwise
     */
    public boolean containsUser(String username) {
        username = username.toLowerCase();

        return users.containsKey(username);
    }

    /**
     * Login a user to the system.
     * @param username
     * @param password
     * @throws ConnectionException if the user is already logged in or the password is wrong
     */
    public void login(String username, String password) throws ConnectionException {
        username = username.toLowerCase();

        if (loggedInUsers.contains(username)) {
            throw new ConnectionException("User already logged in");
        }
        else {
            if(users.get(username).equals(password)) {
                loggedInUsers.add(username);
            }
            else {
                throw new ConnectionException("Wrong password");
            }
        }
    }

    /**
     * Logout a user from the system.
     * @param username
     * @throws ConnectionException if the user is not logged in
     */
    public void logout(String username) throws ConnectionException {
        username = username.toLowerCase();

        if (!loggedInUsers.contains(username)) {
            throw new ConnectionException("User not logged in");
        }
        else {
            loggedInUsers.remove(username);
        }
    }

    /**
     * Check if a user is logged in.
     * @param username
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn(String username) {
        username = username.toLowerCase();

        return loggedInUsers.contains(username);
    }
}
