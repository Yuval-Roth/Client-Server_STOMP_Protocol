package bgu.spl.net.impl.stomp.Backend.Frames;

import java.io.IOException;
import java.util.List;

public class SubscribeFrame extends ExecutableFrame{

    protected SubscribeFrame(List<HeaderLine> headers, String frameBody) {
        super(headers, frameBody, StompCommand.SUBSCRIBE);
    }

    @Override
    public String execute() {

        String[] destination = /*Arrays.stream(*/headers[0].headerValue.split("/")/*)*//*.filter(s -> !s.isEmpty()).toArray(String[]::new)*/;
        
        //TODO: figure out what to do with this id header and how to map it to a connection id
        int id = Integer.parseInt(headers[1].headerValue);

        int receipt = Integer.parseInt(headers[2].headerValue);

        //TODO: check if we need to support sub topics
        String mainTopic = destination[0];
        String team1 = mainTopic.split("_")[0];
        String team2 = mainTopic.split("_")[1];
        try{
            //TODO implement map from connection id to username and replace PLACE_HOLDER
            subM.subscribe("PLACE_HOLDER", team1,team2);
            return ReceiptFrame.get(receipt).toString();
        }catch (IOException e){
            return ErrorFrame.get(e.getMessage()).toString();
        }
    }
}
