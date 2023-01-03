package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class ConnectFrame extends ExecutableFrame {

    protected ConnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECT);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        String username = headers[2].headerValue;
        String password = headers[3].headerValue;

        try{
            conM.connect(handler,username, password);
        }catch(IOException e){

            //this is the case where the user is already connected or the password is wrong
            return ErrorFrame.get(e.getMessage()).toString();
        }

        //this is the case where the user is connected successfully
        return ConnectedFrame.get().toString();
    }


}
