package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class ChannelException extends IOException{
    
    public ChannelException(String message) {
        super(message);
    }
}
