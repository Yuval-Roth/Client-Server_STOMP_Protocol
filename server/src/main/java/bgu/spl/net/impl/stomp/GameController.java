package bgu.spl.net.impl.stomp;

import java.util.HashMap;

public class GameController {

    HashMap<Integer,Game> games;

    public GameController() {
        games = new HashMap<Integer,Game>();
    }
    public boolean addGame(int id, Game game) {
        if (games.containsKey(id)) {
            return false;
        }
        games.put(id, game);
        return true;
    }
    public boolean removeGame(int id) {
        if (!games.containsKey(id)) {
            return false;
        }
        games.remove(id);
        return true;
    }
    public boolean containsGame(int id) {
        return games.containsKey(id);
    }
    public Game getGame(int id) {
        return games.get(id);
    }
    
}
