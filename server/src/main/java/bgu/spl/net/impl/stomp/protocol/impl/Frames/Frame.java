package bgu.spl.net.impl.stomp.protocol.impl.Frames;

import java.util.Iterator;
import java.util.List;

public abstract class Frame {

    protected enum StompCommand {
        CONNECT, CONNECTED, SEND, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, MESSAGE, RECEIPT, ERROR
    }

    protected class HeaderLine{
        public final String headerName;
        public final String headerValue;

        public HeaderLine(String key, String value) {
            this.headerName = key;
            this.headerValue = value;
        }
    }

    protected Frame(List<HeaderLine> headers, String frameBody){
        instantiateHeaders(headers);
        this.frameBody = frameBody;
    }

    protected HeaderLine[] headers;
    protected String frameBody;

    public abstract void execute();

    private void instantiateHeaders(List<HeaderLine> headers){
        this.headers = new HeaderLine[headers.size()];
        Iterator<HeaderLine> iter = headers.iterator();
        int i = 0;
        while (iter.hasNext()){
            this.headers[i] = iter.next();
            i++;
        }
    }

    public static Frame createFrame(StompCommand command, List<HeaderLine> headers, String frameBody){
        switch (command){
            case CONNECT:
                return new Connect(headers, frameBody);
            case CONNECTED:
                return new Connected(headers, frameBody);
            case SEND:
                return new Send(headers, frameBody);
            case SUBSCRIBE:
                return new Subscribe(headers, frameBody);
            case UNSUBSCRIBE:
                return new Unsubscribe(headers, frameBody);
            case DISCONNECT:
                return new Disconnect(headers, frameBody);
            case MESSAGE:
                return new Message(headers, frameBody);
            case RECEIPT:
                return new Receipt(headers, frameBody);
            case ERROR:
                return new Error(headers, frameBody);
            default:
                return null;
        }
    }
        
}
