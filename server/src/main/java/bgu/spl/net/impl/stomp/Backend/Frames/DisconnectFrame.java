package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class DisconnectFrame extends ExecutableFrame {

    protected DisconnectFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.DISCONNECT);
    }

    @Override
    public String execute(ConnectionHandler<String> handler) {
        
        int receipt = Integer.parseInt(headers[0].headerValue);

        try{
            conM.disconnect(handler);
            handler.close();
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
  
}
