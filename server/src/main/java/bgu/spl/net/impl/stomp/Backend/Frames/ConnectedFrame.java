package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ConnectedFrame extends Frame {

    protected ConnectedFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECTED);
        //TODO Auto-generated constructor stub
    }

    public static Frame generateConnectedFrame() {
        String frame = "CONNECTED"+NEW_LINE; // command
        frame += "version:"+PROTOCOL_VERSION+NEW_LINE;
        frame += NEW_LINE;//end of headers
        frame += END_OF_FRAME;
        return Frame.parse(frame);
    }

    
}
