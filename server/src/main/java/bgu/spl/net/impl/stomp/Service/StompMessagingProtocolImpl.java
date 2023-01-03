package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.genericServers.interfaces.Connections;
import bgu.spl.net.impl.stomp.Backend.StompFacade;
import bgu.spl.net.impl.stomp.Backend.Frames.ErrorFrame;
import bgu.spl.net.impl.stomp.Backend.Frames.ExecutableFrame;

public class StompMessagingProtocolImpl implements MessagingProtocol<String> {

    @Override
    public void start(ConnectionHandler<String> handler, String connectionMessage) {
        Connections<String> conn = StompFacade.getInstance();
        conn.connect(handler,connectionMessage);
    }

    @Override
    public String process(ConnectionHandler<String> handler,String message) {
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
