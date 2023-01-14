package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.Frame;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.MessageFrame;
import bgu.spl.net.impl.stomp.Service.interfaces.ChannelsManager;
import bgu.spl.net.impl.stomp.Service.interfaces.ConnectionsManager;
import bgu.spl.net.impl.stomp.StompExceptions.ChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.ConnectionException;


/**
 * This class is the facade of the backend.
 * It implements the ConnectionsManager and ChannelsManager interfaces.
 * It is a singleton class.
 */
public class StompFacade implements ChannelsManager<String>, ConnectionsManager<String> {
    
    private static StompFacade instance = null;
    
    private final UserController uc;
    private final ChannelController cc;
    private final SessionController sc;

    private volatile int connectionIdCounter;

    private StompFacade() {
        uc = new UserController();
        cc = new ChannelController();
        sc = new SessionController();

        connectionIdCounter = 0;
    }

    //===============================================================================================|
    //========================== ConnectionManager interface methods ================================|
    //===============================================================================================|

    @Override
    public void connect(ConnectionHandler<String> handler,String username, String password) throws ConnectionException{
        
        if(uc.containsUser(username) == false) {
            uc.addUser(username, password);
        }
        
        uc.login(username, password);
        int connectionId = connectionIdCounter++;
        sc.newSession(handler,connectionId, username);
    }

    @Override
    public void disconnect(ConnectionHandler<String> handler) throws ConnectionException{

        Session session = sc.getSession(handler);
        if(session == null) {
            return;
        }
        String username = session.getUsername();
        for(SubscriberId subscription : session.getSubscriptions().keySet()) {
            try {
                cc.unsubscribe(subscription);
            } catch (ChannelException e) {
                e.printStackTrace();
                throw new ConnectionException("Error while disconnecting");
            }
        }
        uc.logout(username);
        sc.closeSession(handler);
    }

    //===============================================================================================|
    //============================== ChannelsManager interface methods ==============================|
    //===============================================================================================|

    @Override
    public void subscribe(ConnectionHandler<String> handler, int subId, String channel) throws ChannelException {
        Session session = sc.getSession(handler);
        if(session == null) {
            return;
        }
        int connectionId = session.getConnectionId();
        SubscriberId subberId = cc.subscribe(connectionId, subId, channel);
        session.addSubscription(subberId, channel);
    }

    @Override
    public void unsubscribe(ConnectionHandler<String> handler, int subId) throws ChannelException {
        Session session = sc.getSession(handler);
        if(session == null) {
            return;
        }
        int connectionId = session.getConnectionId();
        SubscriberId subberId = cc.unsubscribe(connectionId, subId);
        session.removeSubscription(subberId);
    }

    @Override
    public void whisper(ConnectionHandler<String> handler, String msg) {
        handler.send(msg);     
    }

    @Override
    public void broadcast(ConnectionHandler<String> sender ,String channel, String msg) throws ChannelException {

        Session session = sc.getSession(sender);
        if(session == null) {
            return;
        }
        HashMap<SubscriberId,String> senderSubscriptions = session.getSubscriptions();
        if(senderSubscriptions.containsValue(channel) == false) {
            throw new ChannelException("User is not subscribed to channel");
        }
    
        for(SubscriberId subberId : cc.getChannelSubscribers(channel)) {
            int connectionId = subberId.connectionId;
            int subId = subberId.subId;
            Frame frameToSend = MessageFrame.get(subId, channel, msg);

            session = sc.getSession(connectionId);
            ConnectionHandler<String> handler = session.getHandler();
            handler.send(frameToSend.toString());
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
