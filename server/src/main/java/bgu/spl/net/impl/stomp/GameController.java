package bgu.spl.net.impl.stomp;

import java.util.HashMap;

public class GameController {

    private static GameController instance = null;

    private HashMap<Integer,Game> games;

    private GameController() {
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
    
    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }   
}
