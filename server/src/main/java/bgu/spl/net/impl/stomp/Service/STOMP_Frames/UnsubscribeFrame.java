package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class UnsubscribeFrame extends ExecutableFrame {

    private static String ID_FIELD = "id";
    private static String RECEIPT_FIELD = "receipt";

    protected UnsubscribeFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.UNSUBSCRIBE);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        int id = Integer.parseInt(headers.get(ID_FIELD));
        int receipt = Integer.parseInt(headers.get(RECEIPT_FIELD));

        try{
            channelsManager.unsubscribe(handler,id);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    } 
}
