package bgu.spl.net.impl.stomp.Backend.Frames;

import java.util.List;

public class Error extends Frame {

    protected Error(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.ERROR);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String execute() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static Frame generateInvalidCommandError(String badFrame) {

        String frame = "ERROR"+NEW_LINE; // command
        frame += NEW_LINE;//end of headers

        //body
        frame += "The frame received does not contain a valid STOMP command."+NEW_LINE;
        frame += "The frame received is:"+NEW_LINE;
        frame += "========================"+NEW_LINE;
        frame += badFrame.replace("^@", "")+NEW_LINE;
        frame += "========================"+NEW_LINE;
        frame += END_OF_FRAME;
        
        System.out.println(frame);
        return Frame.parse(frame);
    }
}
