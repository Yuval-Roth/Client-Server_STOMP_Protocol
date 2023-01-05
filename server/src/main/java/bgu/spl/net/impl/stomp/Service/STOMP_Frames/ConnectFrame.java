package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class ConnectFrame extends ExecutableFrame {

    private static String USERNAME_FIELD = "login";
    private static String PASSWORD_FIELD = "passcode";

    protected ConnectFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.CONNECT);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        String username = headers.get(USERNAME_FIELD);
        String password = headers.get(PASSWORD_FIELD);

        try{
            connectionsManager.connect(handler,username, password);
        }catch(IOException e){

            //this is the case where the user is already connected or the password is wrong
            return ErrorFrame.get(e.getMessage()).toString();
        }

        //this is the case where the user is connected successfully
        return ConnectedFrame.get().toString();
    }


}
