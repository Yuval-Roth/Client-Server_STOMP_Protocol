package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ReceiptFrame extends Frame {

    protected ReceiptFrame(List<HeaderLine> headers, String frameBody) {
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
