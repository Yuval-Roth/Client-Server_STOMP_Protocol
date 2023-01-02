package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class GameException extends IOException{
    
    public GameException(String message) {
        super(message);
    }
}
