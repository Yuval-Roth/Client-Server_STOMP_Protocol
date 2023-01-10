package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;
import java.util.Iterator;

import bgu.spl.net.impl.stomp.Backend.StompFacade;
import bgu.spl.net.impl.stomp.Service.interfaces.ChannelsManager;
import bgu.spl.net.impl.stomp.Service.interfaces.ConnectionsManager;

/**
 * Frame is an abstract class that represents a frame in the STOMP protocol.
 * It contains the main methods that are used by all the frames.
 * It also contains the main fields that are used by all the frames.
 */
public abstract class Frame {

    protected static final ChannelsManager<String> channelsManager = StompFacade.getInstance();
    protected static final ConnectionsManager<String> connectionsManager = StompFacade.getInstance();

    protected static final double PROTOCOL_VERSION = 1.2;
    protected static final char END_OF_FRAME = '\0';
    protected static final String NEW_LINE = "\n";
    protected static final String HEADER_DELIMITER = ":";

    protected enum StompCommand {
        CONNECT, CONNECTED, SEND, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, MESSAGE, RECEIPT, ERROR
    }

    protected Frame(HashMap<String,String> headers, String frameBody, StompCommand command){
        this.headers = headers;
        this.frameBody = frameBody;
        this.command = command;
    }

    protected StompCommand command;
    protected HashMap<String,String> headers;
    protected String frameBody;
    
    //================================================================================================|
    //=================================== Main Methods  ==============================================|
    //================================================================================================|


    /**
     * this method parses a string to a frame in the STOMP protocol.
     * if the string is not in the correct format or the command is not a valid command,
     * the method will return null.
     * @param messageToParse - the string to parse
     */
    public static Frame parse(String messageToParse){
        String[] frameParameters = messageToParse.split(NEW_LINE); // split by new line
        StompCommand command = StompCommand.valueOf(frameParameters[0]); // parse command

        // parse headers
        HashMap<String,String> headers = new HashMap<String,String>();
        int frameParametersLine = 1;
        while (!frameParameters[frameParametersLine].trim().equals(""))
        {
            String[] header = frameParameters[frameParametersLine].split(HEADER_DELIMITER);
            headers.put(header[0], header[1]);
            frameParametersLine++;
        }
        frameParametersLine++;
        // parse body
        String frameBody = "";
        while (frameParameters[frameParametersLine].charAt(0) != END_OF_FRAME)
        {
            frameBody += frameParameters[frameParametersLine]+NEW_LINE;
            frameParametersLine++;
        }

        if (frameBody.equals("")) {
            frameBody = null;
        }

        return createFrame(command, headers, frameBody);
    }

    @Override
    public String toString(){
        String output = "";
        output += command + NEW_LINE;
        Iterator<String> iter = headers.keySet().iterator();
        while (iter.hasNext()){
            String headerName = iter.next();
            String headerValue = headers.get(headerName);
            output += headerName + HEADER_DELIMITER + headerValue + NEW_LINE;
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

    private static Frame createFrame(StompCommand command, HashMap<String,String> headers, String frameBody){
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
