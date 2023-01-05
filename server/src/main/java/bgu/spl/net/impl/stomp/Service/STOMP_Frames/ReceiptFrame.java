package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class ReceiptFrame extends Frame {

    protected ReceiptFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.RECEIPT);
    }
    
    public static Frame get(int receiptId) {
        String frame = "RECEIPT"+NEW_LINE; // command
        frame += "receipt-id:"+receiptId+NEW_LINE;
        frame += NEW_LINE;//end of headers
        frame += END_OF_FRAME;
        return Frame.parse(frame);
    }
}
