package bgu.spl.net.impl.stomp;

import java.util.HashMap;

public class GameController {

    private static GameController instance = null;

    private HashMap<String,Game> games;

    private GameController() {
        games = new HashMap<String,Game>();
    }

    public boolean addGame(Game game) {

        if (games.containsKey(game.getName())) {
            return false;
        }
        games.put(game.getName(), game);
        return true;
    }

    public boolean removeGame(Game game) {
        if (!games.containsKey(game.getName())) {
            return false;
        }
        games.remove(game.getName());
        return true;
    }

    public boolean containsGame(String home, String away) {
        return games.containsKey(home+"_"+away);
    }

    public Game getGame(String home, String away) {
        return games.get(home+"_"+away);
    }
    
    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }   
}
