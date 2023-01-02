package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class ConnectFrame extends Frame {

    protected ConnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECT);
    }

    @Override
    public String execute() {

        //TODO - finish the implementation of execute in ConnectFrame

        String output = "";

        try{
            op.Connect(headers[0].headerValue, headers[1].headerValue);
            output = "CONNECTED" + NEW_LINE + "version:1.2" + NEW_LINE + END_OF_FRAME;
        }catch(UserException e){
            
        }

        return null;
    }


}
