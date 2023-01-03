package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.interfaces.ConnectionManager;
import bgu.spl.net.impl.stomp.Backend.interfaces.SubscriptionManager;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;


/**
 * This class is the facade of the backend.
 * It implements the Connections interface, the ConnectionManager interface and the SubscriptionManager interface.
 * It is a singleton class.
 */
public class StompFacade implements Connections<String>, ConnectionManager, SubscriptionManager {
    
    private static StompFacade instance = null;
    
    private final UserController uc;
    private final GameChannelController gc;
    private final ConnectionController cc;
    
    private StompFacade() {
        uc = new UserController();
        gc = new GameChannelController();
        cc = new ConnectionController();
    }

    //===============================================================================================|
    //============================ ServerManager interface methods ==================================|
    //===============================================================================================|

    @Override
    public void connect(ConnectionHandler<String> handler,String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
        }
        else {
            uc.addUser(username, password);
        }
        cc.startConnection(handler, username);
    }

    @Override
    public void disconnect(String username) throws UserException{

        validateUser(username);

        uc.logout(username);
        cc.endConnection(username);
    }

    //===============================================================================================|
    //============================ SubscriptionManager interface methods ============================|
    //===============================================================================================|

    @Override
    public void unsubscribe(String username, String topic) throws GameChannelException, UserException{

        validateUser(username);
        
        GameChannel channel = gc.getGameChannel(topic);
        channel.removeSubscriber(username);
    }

    @Override
    public void subscribe(String username ,String topic) throws GameChannelException, UserException{

        validateUser(username);
        
        GameChannel channel = gc.getGameChannel(topic);
        channel.addSubscriber(username);
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

    /**
     * Singleton instance
     */
    public static StompFacade getInstance() {
        if(instance == null) {
            instance = new StompFacade();
        }
        return instance;
    }
}
