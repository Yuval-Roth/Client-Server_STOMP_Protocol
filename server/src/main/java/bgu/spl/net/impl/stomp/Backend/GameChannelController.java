package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;

public class GameChannelController {

    private HashMap<String,GameChannel> gameChannels;

    public GameChannelController() {
        gameChannels = new HashMap<String,GameChannel>();
    }

    public void addGame(GameChannel game) throws GameChannelException {

        if (gameChannels.containsKey(game.getName())) {
            throw new GameChannelException("Game already exists");
        }
        gameChannels.put(game.getName(), game);
    }

    public void removeGame(GameChannel game) throws GameChannelException {
        if (!gameChannels.containsKey(game.getName())) {
            throw new GameChannelException("Game does not exist");
        }
        gameChannels.remove(game.getName());
    }

    public boolean containsGame(String home, String away) {
        return gameChannels.containsKey(home+"_"+away);
    }

    public GameChannel getGame(String home, String away) throws GameChannelException {

        if (!gameChannels.containsKey(home+"_"+away)) {
            throw new GameChannelException("Game does not exist");
        }
        return gameChannels.get(home+"_"+away);
    }
}
