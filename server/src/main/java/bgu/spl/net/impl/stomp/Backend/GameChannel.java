package bgu.spl.net.impl.stomp.Backend;

import java.util.LinkedList;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
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
    private final LinkedList<ConnectionHandler<String>> subscribers;
    private final String topic;

    public GameChannel(String topic) {
        this.topic = topic;
        subscribers = new LinkedList<>();
    }

    /**
     * subscribes the user to the channel
     * @throws GameChannelException
     */
    public void addSubscriber(ConnectionHandler<String> handler) throws GameChannelException {

        if(subscribers.contains(handler))
            throw new GameChannelException("User already subscribed to channel");

        subscribers.add(handler);
    }
    /**
     * unsubscribes the user from the channel
     * @throws GameChannelException
     */
    public void removeSubscriber(ConnectionHandler<String> handler) throws GameChannelException {

        if(!subscribers.contains(handler))
            throw new GameChannelException("User not subscribed to channel");

        subscribers.remove(handler);
    }

    public String getName() {
        return topic;
    }

    @Override
    public void notifySubscribers(String message) {
        //TODO GameChannel - notifySubscribers     
    }
}
