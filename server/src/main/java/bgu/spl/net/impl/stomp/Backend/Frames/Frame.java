package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import bgu.spl.net.impl.stomp.Backend.StompFacade;

public abstract class Frame {

    protected static final StompFacade op = StompFacade.getInstance();

    protected final static String END_OF_FRAME = "^@";
    protected final static String NEW_LINE = "\n";
    protected final static String HEADER_DELIMITER = ":";

    protected enum StompCommand {
        CONNECT, CONNECTED, SEND, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, MESSAGE, RECEIPT, ERROR
    }

    protected static class HeaderLine{
        public final String headerName;
        public final String headerValue;

        public HeaderLine(String key, String value) {
            this.headerName = key;
            this.headerValue = value;
        }
    }

    protected Frame(List<HeaderLine> headers, String frameBody, StompCommand command){
        instantiateHeaders(headers);
        this.frameBody = frameBody;
        this.command = command;
    }

    protected StompCommand command;
    protected HeaderLine[] headers;
    protected String frameBody;


    //================================================================================================|
    //=================================== Main Methods  ==============================================|
    //================================================================================================|

    /**
     * Create a frame according to the command received
     * Returns
     */
    public static Frame parse(String messageToParse){
        String[] frameParameters = messageToParse.split(NEW_LINE); // split by new line
        StompCommand command = StompCommand.valueOf(frameParameters[0]); // parse command

        // parse headers
        List<HeaderLine> headers = new LinkedList<HeaderLine>();
        int frameParametersLine = 1;
        while (!frameParameters[frameParametersLine].trim().equals(""))
        {
            String[] header = frameParameters[frameParametersLine].split(HEADER_DELIMITER);
            headers.add(new HeaderLine(header[0], header[1]));
            frameParametersLine++;
        }
        frameParametersLine++;
        // parse body
        String frameBody = "";
        while (!frameParameters[frameParametersLine].trim().equals(END_OF_FRAME))
        {
            frameBody += frameParameters[frameParametersLine]+NEW_LINE;
            frameParametersLine++;
        }

        if (frameBody.equals("")) {
            frameBody = null;
        }

        return createFrame(command, headers, frameBody);
    }

    public String getFrameString(){
        String output = "";
        output += command + NEW_LINE;
        for (HeaderLine header : headers){
            output += header.headerName + HEADER_DELIMITER + header.headerValue + NEW_LINE;
        }
        output += NEW_LINE;
        if (frameBody != null){
            output += frameBody;
        }
        output += END_OF_FRAME;
        return output;
    }

    //================================================================================================|
    //==================================== Utility Methods ===========================================|
    //================================================================================================|

    protected String encapsulateBody (String body){
        return "Received frame was:\n====================\n" + body + "====================\n";
    }

    private void instantiateHeaders(List<HeaderLine> headers){
        this.headers = new HeaderLine[headers.size()];
        Iterator<HeaderLine> iter = headers.iterator();
        int i = 0;
        while (iter.hasNext()){
            this.headers[i] = iter.next();
            i++;
        }
    }

    private static Frame createFrame(StompCommand command, List<HeaderLine> headers, String frameBody){
        switch (command){
            case CONNECT:
                return new ConnectFrame(headers, frameBody);
            case CONNECTED:
                return new ConnectedFrame(headers, frameBody);
            case SEND:
                return new SendFrame(headers, frameBody);
            case SUBSCRIBE:
                return new SubscribeFrame(headers, frameBody);
            case UNSUBSCRIBE:
                return new UnsubscribeFrame(headers, frameBody);
            case DISCONNECT:
                return new DisconnectFrame(headers, frameBody);
            case MESSAGE:
                return new MessageFrame(headers, frameBody);
            case RECEIPT:
                return new ReceiptFrame(headers, frameBody);
            case ERROR:
                return new ErrorFrame(headers, frameBody);
            default:
                return null;
        }
    }
        
}
