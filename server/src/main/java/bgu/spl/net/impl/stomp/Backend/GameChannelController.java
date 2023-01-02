package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

public class GameChannelController {

    private HashMap<String,GameChannel> gameChannels;

    public GameChannelController() {
        gameChannels = new HashMap<String,GameChannel>();
    }

    public void addGameChannel(String team1, String team2) throws GameChannelException {

        String name = parseName(team1, team2);

        if (gameChannels.containsKey(name)) {
            throw new GameChannelException("Game already exists");
        }
        gameChannels.put(name, new GameChannel(team1, team2));
    }

    public void removeGameChannel(String team1, String team2) throws GameChannelException {

        String name = parseName(team1, team2);

        if (!gameChannels.containsKey(name)) {
            throw new GameChannelException("Game does not exist");
        }
        gameChannels.remove(name);
    }

    public boolean containsGameChannel(String team1, String team2) {

        String name = parseName(team1, team2);
        
        return gameChannels.containsKey(name);
    }

    public GameChannel getGameChannel(String team1, String team2) throws GameChannelException {

        String name = parseName(team1, team2);

        if (!gameChannels.containsKey(name)) {
            throw new GameChannelException("Game does not exist");
        }
        return gameChannels.get(name);
    }

    private String parseName(String team1, String team2) {
        if (team1.compareTo(team2) < 0) {
            return team1+"_"+team2;
        }
        else if (team1.compareTo(team2) > 0){
            return team2+"_"+team1;
        }
        else {
            throw new IllegalArgumentException("Team names are the same");
        }
    }
}
