package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;
import java.util.HashSet;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.Backend.interfaces.ChannelsManager;
import bgu.spl.net.impl.stomp.StompExceptions.ChannelException;

public class ChannelController implements ChannelsManager<String> {

    private final HashMap<String, HashSet<ConnectionHandler<String>>> channelToHandlers;
    private final HashMap<ConnectionHandler<String>, HashMap<Integer,String>> handlerAndSubIdToChannel;

    public ChannelController() {
        channelToHandlers = new HashMap<>();
        handlerAndSubIdToChannel = new HashMap<>();
    }

    @Override
    public void subscribe(ConnectionHandler<String> handler, int subId, String channel) throws ChannelException {
        
        // if channel doesn't exist, create it
        if(channelToHandlers.containsKey(channel) == false){
            channelToHandlers.put(channel, new HashSet<>());
        }

        // if handler doesn't exist, add it
        if(handlerAndSubIdToChannel.containsKey(handler) == false){
            handlerAndSubIdToChannel.put(handler, new HashMap<>());
        }

        // if handler is already subscribed to channel, throw exception
        if(channelToHandlers.get(channel).contains(handler) == true){
            throw new ChannelException("handler is already subscribed to channel");
        }

        // if subId already exists, throw exception
        if(handlerAndSubIdToChannel.get(handler).containsKey(subId) == true){
            throw new ChannelException("subId already exists");
        }

        // success
        handlerAndSubIdToChannel.get(handler).put(subId, channel);
    }

    @Override
    public void unsubscribe(ConnectionHandler<String> handler, int subId) throws ChannelException {

        // if handler doesn't exist, throw exception
        if(handlerAndSubIdToChannel.containsKey(handler) == false){
            throw new ChannelException("handler doesn't exist");
        }

        // if subId doesn't exist, throw exception
        if(handlerAndSubIdToChannel.get(handler).containsKey(subId) == false){
            throw new ChannelException("subId doesn't exist");
        }

        // if handler is not subscribed to channel, throw exception
        if (channelToHandlers.get(handlerAndSubIdToChannel.get(handler).get(subId)).contains(handler) == false){
            throw new ChannelException("handler is not subscribed to channel");
        }

        // success
        String channel = handlerAndSubIdToChannel.get(handler).get(subId);
        channelToHandlers.get(channel).remove(handler);     
        handlerAndSubIdToChannel.get(handler).remove(subId);
    }

    @Override
    public void whisper(ConnectionHandler<String> handler, String msg) {
        handler.send(msg);
    }

    @Override
    public void broadcast(String channel, String msg) {
        for(ConnectionHandler<String> handler : channelToHandlers.get(channel)){
            handler.send(msg);
        }
    }
}
