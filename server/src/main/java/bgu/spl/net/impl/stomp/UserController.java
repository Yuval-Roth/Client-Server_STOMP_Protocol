package bgu.spl.net.impl.stomp;

import java.util.HashMap;

public class UserController {
    
    private static UserController instance = null;

    private HashMap<String,String> users;

    public UserController() {
        users = new HashMap<String,String>();
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
    public boolean login(String username, String password) {
        username = username.toLowerCase();

        if (!users.containsKey(username)) {
            return false;
        }
        return users.get(username).equals(password);
    }
    
    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }

}
