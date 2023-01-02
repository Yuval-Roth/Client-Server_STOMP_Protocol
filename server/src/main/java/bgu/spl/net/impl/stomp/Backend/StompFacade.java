package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.facade_interfaces.ConnectionManager;
import bgu.spl.net.impl.stomp.Backend.facade_interfaces.SubscriptionManager;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class StompFacade implements Connections<String>, ConnectionManager, SubscriptionManager {
    
    private static StompFacade instance = null;
    
    private final UserController uc;
    private final GameChannelController gc;
    
    private StompFacade() {
        uc = new UserController();
        gc = new GameChannelController();
    }

    //===============================================================================================|
    //============================ ServerManager interface methods ==================================|
    //===============================================================================================|

    @Override
    public void Connect(String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
        }
        else {
            uc.addUser(username, password);
        }
    }

    @Override
    public void Disconnect(String username) throws UserException{

        validateUser(username);

        uc.logout(username);
    }

    //===============================================================================================|
    //============================ SubscriptionManager interface methods ============================|
    //===============================================================================================|

    @Override
    public void Unsubscribe(String username,String hometeam, String awayteam) throws GameChannelException, UserException{
        
        validateUser(username);
        
        GameChannel channel = gc.getGameChannel(hometeam, awayteam);
        channel.removeSubscriber(username);
    }

    @Override
    public void Subscribe(String username,String hometeam, String awayteam) throws GameChannelException, UserException{

        validateUser(username);
        
        GameChannel channel = gc.getGameChannel(hometeam, awayteam);
        channel.addSubscriber(username);
    }

    public void Send(String username, String hometeam, String awayteam, String message) throws GameChannelException, UserException{

        validateUser(username);

        GameChannel channel = gc.getGameChannel(hometeam, awayteam);
        channel.addMessage(username, message);
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
    //============================== Utility methods ================================================|
    //===============================================================================================|

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

    public static StompFacade getInstance() {
        if(instance == null) {
            instance = new StompFacade();
        }
        return instance;
    }
}
