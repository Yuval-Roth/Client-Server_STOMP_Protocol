package bgu.spl.net.impl.stomp.Backend;

import java.util.LinkedList;

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
     */
    public void addSubscriber(String username) {
        subscribedUsers.add(username);
    }
    /**
     * unsubscribes the user from the channel
     */
    public void removeSubscriber(String username) {
        subscribedUsers.remove(username);
    }

    public String getName() {return home+"_"+away;}
}
