package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class Receipt extends Frame {

    protected Receipt(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.RECEIPT);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
