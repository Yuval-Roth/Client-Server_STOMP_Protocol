package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class GameChannelException extends IOException{
    
    public GameChannelException(String message) {
        super(message);
    }
}
