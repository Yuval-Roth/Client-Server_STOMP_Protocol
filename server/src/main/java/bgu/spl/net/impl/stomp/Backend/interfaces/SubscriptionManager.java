package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface SubscriptionManager {
    
    
    /**
     * unsubscribe from a game's channel
     * @param username - the user that wants to unsubscribe
     * @param team1 - any of the teams in the game
     * @param team2 - any of the teams in the game
     * @throws UserException
     * @throws GameChannelException
     */
    public void Unsubscribe(int id/*, String team1, String team2*/) throws UserException, GameChannelException;
    //TODO: fix to support int id and removal of team1 and team2


    /**
     * subscribe to a game's channel
     * @param username - the user that wants to subscribe
     * @param team1 - any of the teams in the game
     * @param team2 - any of the teams in the game
     * @throws UserException
     * @throws GameChannelException
     */
    public void Subscribe(int id, String team1, String team2) throws UserException, GameChannelException;
}
