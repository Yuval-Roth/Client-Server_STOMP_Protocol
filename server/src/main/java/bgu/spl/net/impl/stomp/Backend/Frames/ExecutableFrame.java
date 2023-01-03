package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public abstract class ExecutableFrame extends Frame {

    protected ExecutableFrame(List<HeaderLine> headers, String frameBody, StompCommand command) {
        super(headers, frameBody, command);
    }

    /**
     * Execute the frame according to the command received and return the response
     */
    public abstract String execute();

    public static ExecutableFrame parse(String messageToParse){

        Frame frame = Frame.parse(messageToParse);

        if(frame == null)
            return null;
        else if(frame instanceof ExecutableFrame)
            return (ExecutableFrame) frame;
        else
            throw new RuntimeException("Frame is not executable, cannot parse");
    }
}
