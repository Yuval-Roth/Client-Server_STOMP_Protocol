package bgu.spl.net.impl.stomp.protocol.impl.Frames;

import java.util.List;

public class Error extends Frame {

    protected Error(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
