package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class UserException extends IOException {

    public UserException(String message) {
        super(message);
    }
}
