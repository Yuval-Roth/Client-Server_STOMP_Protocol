package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class ConnectFrame extends ExecutableFrame {

    protected ConnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECT);
    }

    @Override
    public String execute() {

        String username = headers[2].headerValue;
        String password = headers[3].headerValue;

        try{
            conM.Connect(username, password);
        }catch(UserException e){
            return ErrorFrame.generateErrorFrame(e.getMessage()).toString();
        }
        return ConnectedFrame.generateConnectedFrame().toString();
    }


}
