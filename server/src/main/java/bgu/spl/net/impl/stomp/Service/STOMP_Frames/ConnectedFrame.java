package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class ConnectedFrame extends Frame {

    private static final String VERSION_FIELD = "version";

    protected ConnectedFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECTED);
    }

    /**
     * Returns a connected frame with the current protocol version
     */
    public static Frame get() {

        HashMap<String,String> headers = new HashMap<>();
        headers.put(VERSION_FIELD, ""+PROTOCOL_VERSION);
        return new ConnectedFrame(headers, "");
    }

    
}
