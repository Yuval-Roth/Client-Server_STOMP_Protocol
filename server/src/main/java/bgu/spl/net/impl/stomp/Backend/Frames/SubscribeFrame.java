package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SubscribeFrame extends ExecutableFrame{

    private static String TOPIC_FIELD = "destination";
    private static String ID_FIELD = "id";
    private static String RECEIPT_FIELD = "receipt";

    protected SubscribeFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SUBSCRIBE);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        String topic = headers.get(TOPIC_FIELD);
        int id = Integer.parseInt(headers.get(ID_FIELD));
        int receipt = Integer.parseInt(headers.get(RECEIPT_FIELD));

        try{
            subM.subscribe(handler, id,topic);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
}
