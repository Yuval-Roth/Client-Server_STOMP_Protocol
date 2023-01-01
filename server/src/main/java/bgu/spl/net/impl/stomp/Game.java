package bgu.spl.net.impl.stomp;

import java.util.LinkedList;

/**
 * The Game class represents a game in the system.
 * It holds the channel of the game, the game events and the scores.
 * It also holds the id, home and away teams.
 */
public class Game {

    private final Channel channel;
    private final LinkedList<GameEvent> gameEvents;
    public final String home;
    public final String away;
    private int homeScore;
    private int awayScore;
    //TODO add more fields


    private Game(Channel channel, String home, String away) {
        this.channel = channel;
        this.home = home;
        this.away = away;
        gameEvents = new LinkedList<GameEvent>();
        homeScore = 0;
        awayScore = 0;
    }

    public void addGameEvent(GameEvent gameEvent) {
        //TODO check if this fits
        gameEvents.add(gameEvent);
    }

    public static Game generateGame(Channel channel, String home, String away) {
        return new Game(channel, home, away);
    }

    //========================================================================|
    //======================= Getters and Setters ============================|
    //========================================================================|
    public String getName() {return home+"_"+away;}
    public Channel getChannel() {return channel;}
    public LinkedList<GameEvent> getGameEvents() {return gameEvents;}
    public int getHomeScore() {return homeScore;}
    public void setHomeScore(int homeScore) {this.homeScore = homeScore;}
    public int getAwayScore() {return awayScore;}
    public void setAwayScore(int awayScore) {this.awayScore = awayScore;}

    
    
    
}
