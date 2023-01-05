package bgu.spl.net.impl.stomp.Service.STOMP_Frames;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public abstract class ExecutableFrame extends Frame {

    protected ExecutableFrame(HashMap<String,String> headers, String frameBody, StompCommand command) {
        super(headers, frameBody, command);
    }

    /**
     * Execute the frame according to the command received and return the response
     */
    public abstract String execute(ConnectionHandler<String> handler);

    /**
     * Parse the message to an executable frame
     * @param messageToParse
     * @throws RuntimeException if the frame is not executable
     */
    public static ExecutableFrame parse(String messageToParse) throws RuntimeException{

        Frame frame = Frame.parse(messageToParse);

        if(frame == null)
            return null;
        else if(frame instanceof ExecutableFrame)
            return (ExecutableFrame) frame;
        else
            throw new RuntimeException("Frame is not executable, cannot parse");
    }
}
