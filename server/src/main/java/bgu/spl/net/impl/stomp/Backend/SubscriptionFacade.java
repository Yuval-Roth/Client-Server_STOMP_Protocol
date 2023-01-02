package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.facade_interfaces.ServerManager;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class SubscriptionFacade implements Connections<String>, ServerManager {
    
    private static SubscriptionFacade instance = null;
    
    private final UserController uc;
    private final GameChannelController gc;
    
    private SubscriptionFacade() {
        uc = new UserController();
        gc = new GameChannelController();
    }

    //===============================================================================================|
    //============================ ServerManager interface methods ==================================|
    //===============================================================================================|

    
    public void Connect(String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
        }
        else {
            uc.addUser(username, password);

        }
    }

    public void Disconnect(String username) throws UserException{

        validateUser(username);

        uc.logout(username);
    }

    public void Unsubscribe(String username,String hometeam, String awayteam) throws GameChannelException, UserException{
        
        validateUser(username);
        
        GameChannel channel = gc.getGame(hometeam, awayteam);
        channel.removeSubscriber(username);
    }

    public void Subscribe(String username,String hometeam, String awayteam) throws GameChannelException, UserException{

        validateUser(username);
        
        GameChannel channel = gc.getGame(hometeam, awayteam);
        channel.addSubscriber(username);
    }

    public void Send(String username, String hometeam, String awayteam, String message) throws GameChannelException, UserException{

        validateUser(username);

        GameChannel channel = gc.getGame(hometeam, awayteam);
        channel.addMessage(username, message);
    }


    /**
     * Validate that the user exists and is logged in
     * @param username
     * @throws UserException
     */
    private void validateUser(String username) throws UserException {
        if(uc.containsUser(username) == false) {
            throw new UserException("User does not exist");
        }
        if(uc.isLoggedIn(username) == false) {
            throw new UserException("User is not logged in");
        }
    }

    //================================================================================================|
    //============================ Connections interface methods =====================================|
    //================================================================================================|

    @Override
    public boolean send(int connectionId, String msg) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void send(String channel, String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void disconnect(int connectionId) {
        // TODO Auto-generated method stub
        
    }

    //===============================================================================================|
    //============================ Singleton methods ================================================|
    //===============================================================================================|

    public static SubscriptionFacade getInstance() {
        if(instance == null) {
            instance = new SubscriptionFacade();
        }
        return instance;
    }
}
