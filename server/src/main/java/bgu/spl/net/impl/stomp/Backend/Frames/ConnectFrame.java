package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public class ConnectFrame extends ExecutableFrame {

    protected ConnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECT);
    }

    @Override
    public String execute() {

        //TODO - finish the implementation of execute in ConnectFrame

        String username = headers[2].headerValue;
        String password = headers[3].headerValue;

        try{
            op.Connect(username, password);
        }catch(UserException e){
            String body = encapsulateBody(getFrameString());
            return ErrorFrame.generateErrorFrame(e.getMessage(),body).getFrameString();
        }
        return ConnectedFrame.generateConnectedFrame().getFrameString();
    }


}
