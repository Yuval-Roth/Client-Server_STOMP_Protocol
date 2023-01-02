package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.impl.stomp.StompExceptions.GameException;

public class GameChannelController {

    private HashMap<String,GameChannel> gameChannels;

    public GameChannelController() {
        gameChannels = new HashMap<String,GameChannel>();
    }

    public void addGame(GameChannel game) throws GameException {

        if (gameChannels.containsKey(game.getName())) {
            throw new GameException("Game already exists");
        }
        gameChannels.put(game.getName(), game);
    }

    public void removeGame(GameChannel game) throws GameException {
        if (!gameChannels.containsKey(game.getName())) {
            throw new GameException("Game does not exist");
        }
        gameChannels.remove(game.getName());
    }

    public boolean containsGame(String home, String away) {
        return gameChannels.containsKey(home+"_"+away);
    }

    public GameChannel getGame(String home, String away) throws GameException {

        if (!gameChannels.containsKey(home+"_"+away)) {
            throw new GameException("Game does not exist");
        }
        return gameChannels.get(home+"_"+away);
    }
}
