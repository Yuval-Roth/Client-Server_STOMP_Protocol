package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.impl.stomp.Backend.Frames.Frame;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.Backend.Frames.ErrorFrame;
import bgu.spl.net.impl.stomp.Backend.Frames.ExecutableFrame;

public class StompMessagingProtocolImpl implements MessagingProtocol<String> {

    @Override
    public void start(ConnectionHandler<String> handler) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String process(String message) {
        ExecutableFrame frame = ExecutableFrame.parse(message);

        if(frame == null){
            return ErrorFrame.get("The frame received does not contain a valid STOMP command"
                                                    ,message,true).toString();
        }
        else{
            return frame.execute();
        }
    }

    @Override
    public boolean shouldTerminate() {
        // TODO shouldTerminate
        return false;
    }


}
