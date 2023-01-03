package bgu.spl.net.impl.stomp.Service;

import bgu.spl.net.impl.stomp.Backend.Frames.Frame;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.stomp.Backend.Frames.ErrorFrame;
import bgu.spl.net.impl.stomp.Backend.Frames.ExecutableFrame;

public class StompMessagingProtocolImpl implements MessagingProtocol<String> {

    @Override
    public String process(String message) {
        ExecutableFrame frame = ExecutableFrame.parse(message);

        if(frame == null){
            return ErrorFrame.get("The frame received does not contain a valid STOMP command"
                                                    ,frame).toString();
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
