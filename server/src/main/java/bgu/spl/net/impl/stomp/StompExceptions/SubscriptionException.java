package bgu.spl.net.impl.stomp.StompExceptions;

import java.io.IOException;

public class SubscriptionException extends IOException {

    public SubscriptionException(String message) {
        super(message);
    }
}
