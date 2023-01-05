package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class ConnectedFrame extends Frame {

    protected ConnectedFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECTED);
    }

    public static Frame get() {
        String frame = "CONNECTED"+NEW_LINE; // command
        frame += "version:"+PROTOCOL_VERSION+NEW_LINE;
        frame += NEW_LINE;//end of headers
        frame += END_OF_FRAME;
        return Frame.parse(frame);
    }

    
}
