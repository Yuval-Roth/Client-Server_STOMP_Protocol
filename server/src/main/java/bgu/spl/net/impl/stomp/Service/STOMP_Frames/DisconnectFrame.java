package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class DisconnectFrame extends ExecutableFrame {

    private static String RECEIPT_FIELD = "receipt";

    protected DisconnectFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.DISCONNECT);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        
        int receipt = Integer.parseInt(headers.get(RECEIPT_FIELD));

        try{
            connectionsManager.disconnect(handler);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
  
}
