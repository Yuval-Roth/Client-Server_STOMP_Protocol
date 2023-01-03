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

            //this is the case where the user is already connected or the password is wrong
            return ErrorFrame.get(e.getMessage()).toString();
        }

        //this is the case where the user is connected successfully
        return ConnectedFrame.get().toString();
    }


}
