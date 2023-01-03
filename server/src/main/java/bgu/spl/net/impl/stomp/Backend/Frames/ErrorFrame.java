package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class ErrorFrame extends Frame {

    protected ErrorFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.ERROR);
    }

    /**
     * @param receiptId - if receipt-id => 0, receipt-id header will be added
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     * @param bodyIsBadFrame - if true, the body will be added as the frame that caused the error
     */
    public static Frame generateErrorFrame(int receiptId,String message,String body, boolean bodyIsBadFrame) {

        String frame = "ERROR"+NEW_LINE; // command
        if(receiptId >= 0)
            frame += "receipt-id:"+receiptId+NEW_LINE;
        frame += "message:"+message+NEW_LINE;
        frame += NEW_LINE;//end of headers
        
        //body
        if(body.length() > 0){
            if(bodyIsBadFrame){
                frame += "The frame received was:"+NEW_LINE;
                frame += "========================"+NEW_LINE;
                frame += body.replace("^@", "")+NEW_LINE;
                frame += "========================"+NEW_LINE;    
            }
            else{
                frame += body.replace("^@", "")+NEW_LINE;
            }
        }
        frame += END_OF_FRAME;

        return Frame.parse(frame);
    }
    
    /**
     * This overload does not add a body and does not add a receipt-id header
     * @param message - the message to be sent in the header
     */
    public static Frame generateErrorFrame(String message) {

        return generateErrorFrame(-1, message, "", false);
    }

    /**
     * This overload adds the body as a normal string. It does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     */
    public static Frame generateErrorFrame(String message,String body) {

        return generateErrorFrame(-1, message, body,false);
    }

    /**
     * this overload does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     * @param bodyIsBadFrame - if true, the body will be added as the frame that caused the error, 
     * if false, the body will be added as a regular string
     */
    public static Frame generateErrorFrame(String message,String body,boolean bodyIsBadFrame) {

        return generateErrorFrame(-1, message, body, bodyIsBadFrame);
    }

    /**
     * This overload adds the frame that caused the error as the body of the error frame. 
     * It does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param badFrame - the frame that caused the error
     */
    public static Frame generateErrorFrame(String message,Frame badFrame) {

        return generateErrorFrame(-1,message, badFrame.toString(), true);
    }

    /**
     * This overload adds the frame that caused the error to the body of the error frame.
     * 
     * @param receiptId - if receipt-id => 0, receipt-id header will be added
     * @param message - the message to be sent in the header
     * @param badFrame - the frame that caused the error
     */
    public static Frame generateErrorFrame(int receiptId,String message,Frame badFrame) {

        return generateErrorFrame(receiptId,message, badFrame.toString(),true);
    }


    
}
