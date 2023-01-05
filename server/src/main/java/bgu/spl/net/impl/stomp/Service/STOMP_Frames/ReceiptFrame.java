package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

public class ReceiptFrame extends Frame {

    private static final String RECEIPT_ID_FIELD = "receipt-id";

    protected ReceiptFrame(HashMap<String,String> headers, String frameBody) {
        super(headers, frameBody, StompCommand.RECEIPT);
    }
    
    /**
     * Returns a receipt frame with the given receipt id
     *
     * @param receiptId
     */
    public static Frame get(int receiptId) {

        HashMap<String,String> headers = new HashMap<>();
        headers.put(RECEIPT_ID_FIELD, Integer.toString(receiptId));
        return new ReceiptFrame(headers, "");
    }
}
