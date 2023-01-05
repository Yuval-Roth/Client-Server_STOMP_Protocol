package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;
import java.util.HashSet;

import bgu.spl.net.impl.stomp.Backend.utils.Tuple;
import bgu.spl.net.impl.stomp.StompExceptions.ChannelException;

public class ChannelController{

    private final HashMap<String, HashSet<Tuple<Integer>>> channelToIdTuple;
    private final HashMap<Tuple<Integer>,String> IdTupleToChannel;

    public ChannelController() {
        channelToIdTuple = new HashMap<>();
        IdTupleToChannel = new HashMap<>();
    }

    public void subscribe(int connectionId, int subId, String channel) throws ChannelException {
        
        Tuple<Integer> tuple = new Tuple<>(connectionId, subId);

        // if channel doesn't exist, create it
        if(channelToIdTuple.containsKey(channel) == false){
            channelToIdTuple.put(channel, new HashSet<>());
        }

        // check if the user is already subscribed to channel
        if(channelToIdTuple.get(channel).contains(tuple) == true){
            throw new ChannelException("user is already subscribed to channel");
        }

        //success
        channelToIdTuple.get(channel).add(tuple);
        IdTupleToChannel.put(tuple, channel);
    }

    public void unsubscribe(int connectionId, int subId) throws ChannelException {

        Tuple<Integer> tuple = new Tuple<>(connectionId, subId);

        // check if the user is subscribed to channel
        if(IdTupleToChannel.containsKey(tuple) == false){
            throw new ChannelException("subId doesn't exist");
        }

        //success
        String channel = IdTupleToChannel.get(tuple);
        channelToIdTuple.get(channel).remove(tuple);
        IdTupleToChannel.remove(tuple);  
    }

    public HashSet<Tuple<Integer>> getChannelSubscribers(String channel) throws ChannelException {
        if(channelToIdTuple.containsKey(channel) == false){
            throw new ChannelException("channel doesn't exist");
        }
        return channelToIdTuple.get(channel);
    }
}
