package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.interfaces.ConnectionManager;
import bgu.spl.net.impl.stomp.Backend.interfaces.SubscriptionManager;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.SubscriptionException;
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
    private final ChannelController sc;
    private final SessionController sesc;

    private int connectionIdCounter;
    
    private StompFacade() {
        uc = new UserController();
        gc = new GameChannelController();
        sc = new ChannelController();
        sesc = new SessionController();

        connectionIdCounter = 0;
    }

    //===============================================================================================|
    //========================== ConnectionManager interface methods ================================|
    //===============================================================================================|

    @Override
    public void connect(ConnectionHandler<String> handler,String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
        }
        else {
            uc.addUser(username, password);
        }
        int connectionId = connectionIdCounter++;

        sesc.newSession(handler,connectionId, username);
    }

    @Override
    public void disconnect(ConnectionHandler<String> handler) throws UserException{

        Session s = sesc.getSession(handler);
        String username = s.getUsername();
        sesc.closeSession(handler);
        uc.logout(username);

    }

    //===============================================================================================|
    //============================ SubscriptionManager interface methods ============================|
    //===============================================================================================|

    
    @Override
    public void subscribe(ConnectionHandler<String> handler, int subId ,String topic) throws GameChannelException, SubscriptionException{
        
        if(gc.containsGameChannel(topic) == false) {
            gc.addGameChannel(topic);
        }

        GameChannel channel = gc.getGameChannel(topic);
        channel.addSubscriber(handler);
        sc.addSub(handler, subId, topic);
    }
    
    @Override
    public void unsubscribe(ConnectionHandler<String> handler, int subId) throws GameChannelException, SubscriptionException{

        String topic = sc.getTopic(handler,subId);
        GameChannel channel = gc.getGameChannel(topic);
        channel.removeSubscriber(handler);
        sc.removeSub(handler,subId);
    }

    //================================================================================================|
    //============================ Connections interface methods =====================================|
    //================================================================================================|
    
    @Override
    public boolean send(int connectionId, String msg) {
        // TODO Connections interface - send(int connectionId, String msg)
        return false;
    }

    @Override
    public void send(String channel, String msg) {
        // TODO Connections interface - send(String channel, String msg)
        
    }

    @Override
    public void disconnect(int connectionId) {
        // TODO Connections interface - disconnect(int connectionId)
        
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
