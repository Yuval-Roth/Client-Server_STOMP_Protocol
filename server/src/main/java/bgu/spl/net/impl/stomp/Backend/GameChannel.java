package bgu.spl.net.impl.stomp.Backend;

import java.util.LinkedList;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

/**
 * The Game class represents a game in the system.
 * It holds the channel of the game, the game events and the scores.
 * It also holds the id, home and away teams.
 */
public class GameChannel {

      /**
     * holds the users subscribed to the channel
     */
    LinkedList<String> subscribedUsers;

    private final String home;
    private final String away;

    public GameChannel(String home, String away) {
        this.home = home;
        this.away = away;
    }

    /**
     * subscribes the user to the channel
     * @throws GameChannelException
     */
    public void addSubscriber(String username) throws GameChannelException {
        if(subscribedUsers.contains(username))
            throw new GameChannelException("User already subscribed to channel");

        subscribedUsers.add(username);
    }
    /**
     * unsubscribes the user from the channel
     * @throws GameChannelException
     */
    public void removeSubscriber(String username) throws GameChannelException {
        if(!subscribedUsers.contains(username))
            throw new GameChannelException("User not subscribed to channel");

        subscribedUsers.remove(username);
    }

    public String getName() {return home+"_"+away;}

    public void addMessage(String username, String message) {
        //TODO
        
    }
}
