package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ErrorFrame extends Frame {

    protected ErrorFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.ERROR);
    }

    public static Frame generateErrorFrame(int receiptId,String message,String receivedFrame) {

        String frame = "ERROR"+NEW_LINE; // command
        if(receiptId >= 0)
            frame += "receipt-id:"+receiptId+NEW_LINE;
        frame += "message:"+message+NEW_LINE;
        frame += NEW_LINE;//end of headers
        
        //body
        if(receivedFrame.length() > 0){
            frame += "The frame received was:"+NEW_LINE;
            frame += "========================"+NEW_LINE;
            frame += receivedFrame.replace("^@", "")+NEW_LINE;
            frame += "========================"+NEW_LINE;
        }
        frame += END_OF_FRAME;

        return Frame.parse(frame);
    }
    
    public static Frame generateErrorFrame(String message,String body) {

        return generateErrorFrame(-1, message, body);
    }

    public static Frame generateErrorFrame(String message,Frame badFrame) {

        return generateErrorFrame(message, badFrame.getFrameString());
    }

    public static Frame generateErrorFrame(int receiptId,String message,Frame badFrame) {

        return generateErrorFrame(message, badFrame.getFrameString());
    }

    public static Frame generateErrorFrame(String message) {

        return generateErrorFrame(-1, message, "");
    }

    
}
