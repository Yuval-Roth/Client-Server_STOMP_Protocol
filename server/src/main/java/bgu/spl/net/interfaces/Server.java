package bgu.spl.net.interfaces;

import java.io.Closeable;

public interface Server/*<T>*/ extends Closeable {

    /**
     * The main loop of the server, Starts listening and handling new clients.
     */
    public void serve();
}
