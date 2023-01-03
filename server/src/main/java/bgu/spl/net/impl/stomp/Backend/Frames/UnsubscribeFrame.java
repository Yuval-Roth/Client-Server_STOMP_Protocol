package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class UnsubscribeFrame extends ExecutableFrame {

    protected UnsubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.UNSUBSCRIBE);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {

        int id = Integer.parseInt(headers[0].headerValue);
        int receipt = Integer.parseInt(headers[1].headerValue);

        try{
            subM.unsubscribe(handler,id);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    } 
}
