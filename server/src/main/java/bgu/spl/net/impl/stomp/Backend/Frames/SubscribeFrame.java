package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SubscribeFrame extends ExecutableFrame{

    protected SubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SUBSCRIBE);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        String topic = headers[0].headerValue;
        
        //TODO: figure out what to do with this id header and how to map it to a connection id
        int id = Integer.parseInt(headers[1].headerValue);

        int receipt = Integer.parseInt(headers[2].headerValue);

        try{
            //TODO implement map from connection id to username and replace PLACE_HOLDER
            subM.subscribe("PLACE_HOLDER",topic);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
}
