package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;
import java.util.HashSet;

import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class UserController {

    private HashMap<String,String> users;
    private HashSet<String> loggedInUsers;

    public UserController() {
        users = new HashMap<String,String>();
        loggedInUsers = new HashSet<String>();
    }
    public boolean addUser(String username, String password) {
        username = username.toLowerCase();
        
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        return true;
    }
    public boolean containsUser(String username) {
        username = username.toLowerCase();

        return users.containsKey(username);
    }

    public void login(String username, String password) throws UserException {
        username = username.toLowerCase();

        if (loggedInUsers.contains(username)) {
            throw new UserException("User already logged in");
        }
        else {
            if(users.get(username).equals(password)) {
                loggedInUsers.add(username);
            }
            else {
                throw new UserException("Wrong password");
            }
        }
    }
}
