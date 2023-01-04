package bgu.spl.net.impl.stomp.Backend.Frames;

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
            conM.disconnect(handler);
            handler.close();
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
  
}
