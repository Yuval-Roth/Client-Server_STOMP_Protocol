package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class ErrorFrame extends Frame {

    private static final String MESSAGE_FIELD = "message";
    private static final String RECEIPT_ID_FIELD = "receipt-id";

    protected ErrorFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.ERROR);
    }

    /**
     * @param receiptId - if receipt-id => 0, receipt-id header will be added
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     * @param bodyIsBadFrame - if true, the body will be added as the frame that caused the error
     */
    public static Frame get(int receiptId,String message,String body, boolean bodyIsBadFrame) {

        HashMap<String,String> headers = new HashMap<>();
        if(receiptId >= 0)
            headers.put(RECEIPT_ID_FIELD, Integer.toString(receiptId));
        headers.put(MESSAGE_FIELD, message);
        
        if(bodyIsBadFrame){
            body = body.replace("^@", "");
            String badFrame = "";
            badFrame += "The frame received was:"+NEW_LINE;
            badFrame += "========================"+NEW_LINE;
            badFrame += body+NEW_LINE;
            badFrame += "========================"+NEW_LINE;    
            body = badFrame;
        }

        return new ErrorFrame(headers, body);
    }
    
    /**
     * This overload does not add a body and does not add a receipt-id header
     * @param message - the message to be sent in the header
     */
    public static Frame get(String message) {

        return get(-1, message, "", false);
    }

    /**
     * This overload adds the body as a normal string. It does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     */
    public static Frame get(String message,String body) {

        return get(-1, message, body,false);
    }

    /**
     * this overload does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param body - the body of the error frame, will be added iff body.length() > 0 
     * @param bodyIsBadFrame - if true, the body will be added as the frame that caused the error, 
     * if false, the body will be added as a regular string
     */
    public static Frame get(String message,String body,boolean bodyIsBadFrame) {

        return get(-1, message, body, bodyIsBadFrame);
    }

    /**
     * This overload adds the frame that caused the error as the body of the error frame. 
     * It does not add a receipt-id header
     * 
     * @param message - the message to be sent in the header
     * @param badFrame - the frame that caused the error
     */
    public static Frame get(String message,Frame badFrame) {

        return get(-1,message, badFrame.toString(), true);
    }

    /**
     * This overload adds the frame that caused the error to the body of the error frame.
     * 
     * @param receiptId - if receipt-id => 0, receipt-id header will be added
     * @param message - the message to be sent in the header
     * @param badFrame - the frame that caused the error
     */
    public static Frame get(int receiptId,String message,Frame badFrame) {

        return get(receiptId,message, badFrame.toString(),true);
    }


    
}
