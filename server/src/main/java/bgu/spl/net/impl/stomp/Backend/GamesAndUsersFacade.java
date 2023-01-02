package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.impl.stomp.StompExceptions.GameException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class GamesAndUsersFacade {
    
    private static GamesAndUsersFacade instance = null;
    
    private final UserController uc;
    private final GameController gc;

    private GamesAndUsersFacade() {
        uc = new UserController();
        gc = new GameController();
    }
    
    public boolean Connect(String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
            return true;
        }
        else {
            return uc.addUser(username, password);
        }
    }


    public static GamesAndUsersFacade getInstance() {
        if(instance == null) {
            instance = new GamesAndUsersFacade();
        }
        return instance;
    }

    
}
