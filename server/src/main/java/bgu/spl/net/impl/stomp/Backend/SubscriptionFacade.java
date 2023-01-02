package bgu.spl.net.impl.stomp.Backend;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class SubscriptionFacade {
    
    private static SubscriptionFacade instance = null;
    
    private final UserController uc;
    private final GameChannelController gc;
    
    private SubscriptionFacade() {
        uc = new UserController();
        gc = new GameChannelController();
    }
    
    public void Connect(String username, String password) throws UserException{
        
        if(uc.containsUser(username)) {
            uc.login(username, password);
        }
        else {
            uc.addUser(username, password);

        }
    }

    public void Disconnect(String username) throws UserException{
        uc.logout(username);
    }

    /**
     * unsubscribe from a game's channel
     * @param username
     * @param game
     */
    public void Unsubscribe(String username,String hometeam, String awayteam) throws GameChannelException{
        GameChannel channel = gc.getGame(hometeam, awayteam);
        channel.removeSubscriber(username);
    }

    /**
     * Subscribe to a game's channel
     * @param username
     * @param game
     */
    public void Subscribe(String username,String hometeam, String awayteam) throws GameChannelException{
        GameChannel channel = gc.getGame(hometeam, awayteam);
        channel.addSubscriber(username);
    }

    public static SubscriptionFacade getInstance() {
        if(instance == null) {
            instance = new SubscriptionFacade();
        }
        return instance;
    }
}
