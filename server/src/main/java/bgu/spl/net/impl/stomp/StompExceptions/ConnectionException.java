package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class ConnectionException extends IOException {

    public ConnectionException(String message) {
        super(message);
    }
}
