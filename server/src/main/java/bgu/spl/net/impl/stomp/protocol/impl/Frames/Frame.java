package bgu.spl.net.impl.stomp.protocol.impl.Frames;

import java.util.Iterator;
import java.util.List;

public abstract class Frame {

    protected enum StompCommand {
        CONNECT, CONNECTED, SEND, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, MESSAGE, RECEIPT, ERROR
    }

    protected class HeaderLine{
        String headerName;
        String headerValue;

        public HeaderLine(String key, String value) {
            this.headerName = key;
            this.headerValue = value;
        }
    }

    protected Frame(List<HeaderLine> headers){
        instantiateHeaders(headers);
    }

    protected StompCommand command;
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
}
