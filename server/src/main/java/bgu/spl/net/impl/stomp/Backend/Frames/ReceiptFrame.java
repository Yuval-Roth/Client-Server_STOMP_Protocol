package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ReceiptFrame extends Frame {

    protected ReceiptFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.RECEIPT);
        //TODO Auto-generated constructor stub
    }
 
}
