package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

/**
 * This class is used to manage all the game channels in the system.
 */
public class GameChannelController {

    private HashMap<String,GameChannel> gameChannels;

    public GameChannelController() {
        gameChannels = new HashMap<String,GameChannel>();
    }

    public void addGameChannel(String topic) throws GameChannelException {

        if (gameChannels.containsKey(topic)) {
            throw new GameChannelException("Channel already exists");
        }
        gameChannels.put(topic, new GameChannel(topic));
    }

    public void removeGameChannel(String topic) throws GameChannelException {

        if (!gameChannels.containsKey(topic)) {
            throw new GameChannelException("Channel does not exist");
        }
        gameChannels.remove(topic);
    }

    public boolean containsGameChannel(String topic) {

        return gameChannels.containsKey(topic);
    }

    public GameChannel getGameChannel(String topic) throws GameChannelException {

        if (!gameChannels.containsKey(topic)) {
            throw new GameChannelException("Channel does not exist");
        }
        return gameChannels.get(topic);
    }
}
