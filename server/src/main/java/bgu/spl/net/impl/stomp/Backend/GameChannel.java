package bgu.spl.net.impl.stomp.Backend;

import java.util.LinkedList;

import bgu.spl.net.impl.stomp.Backend.interfaces.Subscribeable;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

/**
 * represents a game channel, which is a subscribeable object
 * the channel is identified by the names of the teams playing in the game
 * the channel holds the users subscribed to it and notifies them when a new message is received
 */
public class GameChannel implements Subscribeable{

      /**
     * holds the users subscribed to the channel
     */
    private final LinkedList<String> subscribedUsers;
    private final String topic;

    public GameChannel(String topic) {
        this.topic = topic;
        subscribedUsers = new LinkedList<>();
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

    public String getName() {
        return topic;
    }

    // @Override // TODO uncomment this when done fixing
    public void notifySubscribers(String message) {
        // TODO Auto-generated method stub
        
    }
}
