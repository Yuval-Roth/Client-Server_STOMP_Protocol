package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

public class UnsubscribeFrame extends ExecutableFrame {

    protected UnsubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.UNSUBSCRIBE);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {

        //TODO: figure out what to do with this id header and how to map it to a connection id
        int id = Integer.parseInt(headers[0].headerValue);


        int receipt = Integer.parseInt(headers[1].headerValue);

        try{

            //TODO implement map from connection id to username and replace PLACE_HOLDER
            subM.unsubscribe("PLACE_HOLDER","PLACE_HOLDER","PLACE_HOLDER");
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    } 
}
