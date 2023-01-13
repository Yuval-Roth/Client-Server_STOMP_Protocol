package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.Backend.StompFacade;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.DisconnectFrame;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.ErrorFrame;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.ExecutableFrame;
import bgu.spl.net.impl.stomp.Service.STOMP_Frames.Frame;
import bgu.spl.net.impl.stomp.StompExceptions.ConnectionException;

public class StompMessagingProtocolImpl implements MessagingProtocol<String> {

    private boolean shouldTerminate = false;

    @Override
    public String process(ConnectionHandler<String> handler,String message) {
        ExecutableFrame frame = null;
        try {
            frame = ExecutableFrame.parse(message);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ErrorFrame.get(e.getMessage(),message,true).toString();
        }

        if(frame == null){
            return ErrorFrame.get("The frame received does not contain a valid STOMP command"
                                                    ,message,true).toString();
        }
        else{
            String output = frame.execute(handler);
            if(frame.getClass() == DisconnectFrame.class || output != "" && Frame.parse(output).getClass() == ErrorFrame.class){
                shouldTerminate = true;
            }
            return output;
        }
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    @Override
    public void terminatedCallback(ConnectionHandler<String> handler) {
        try {
            StompFacade.getInstance().disconnect(handler);
        } catch (ConnectionException ignored) {}
    }


}
