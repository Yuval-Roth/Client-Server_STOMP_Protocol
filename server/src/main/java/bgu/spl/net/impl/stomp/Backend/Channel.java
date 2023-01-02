package bgu.spl.net.impl.stomp.Backend;

import java.util.LinkedList;

public class Channel{
    /**
     * holds the users subscribed to the channel
     */
    LinkedList<String> subscribedUsers;
    
    public Channel() {
        subscribedUsers = new LinkedList<String>();
    }

    /**
     * subscribes the user to the channel
     */
    public void Subscribe(String username) {
        subscribedUsers.add(username);
    }
    /**
     * unsubscribes the user from the channel
     */
    public void Unsubscribe(String username) {
        subscribedUsers.remove(username);
    }

}
