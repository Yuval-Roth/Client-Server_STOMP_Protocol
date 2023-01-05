package bgu.spl.net.impl.stomp.Backend;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import bgu.spl.net.impl.stomp.StompExceptions.ChannelException;

public class ChannelController{

    private volatile ConcurrentHashMap<String, Set<SubscriberId>> channelToIdTuple;
    private volatile ConcurrentHashMap<SubscriberId,String> IdTupleToChannel;

    public ChannelController() {
        channelToIdTuple = new ConcurrentHashMap<>();
        IdTupleToChannel = new ConcurrentHashMap<>();
    }

    /**
     * subscribes a user to a channel.
     * the subscription is identified by a SubscriberId object.
     * if the channel doesn't exist, it is created.
     * @param connectionId
     * @param subId
     * @param channel
     * @return the SubscriberId object that identifies the subscription
     * @throws ChannelException if the user is already subscribed to the channel
     */
    public SubscriberId subscribe(int connectionId, int subId, String channel) throws ChannelException {
        
        SubscriberId subberId = new SubscriberId(connectionId, subId);

        // if channel doesn't exist, create it
        if(channelToIdTuple.containsKey(channel) == false){
            channelToIdTuple.put(channel, ConcurrentHashMap.newKeySet());
        }

        // check if the user is already subscribed to channel
        if(channelToIdTuple.get(channel).contains(subberId) == true){
            throw new ChannelException("user is already subscribed to channel");
        }

        //success
        channelToIdTuple.get(channel).add(subberId);
        IdTupleToChannel.put(subberId, channel);
        return subberId;
    }

    /**
     * unsubscribes a user from a channel using a SubscriberId object.
     * @param subberId
     * @throws ChannelException
     */
    public void unsubscribe(SubscriberId subberId) throws ChannelException {
        // check if the user is subscribed to channel
        if(IdTupleToChannel.containsKey(subberId) == false){
            throw new ChannelException("subId doesn't exist");
        }

        //success
        String channel = IdTupleToChannel.get(subberId);
        channelToIdTuple.get(channel).remove(subberId);
        IdTupleToChannel.remove(subberId);  
    }

    /**
     * unsubscribes a user from a channel using a connectionId and a subId.
     * @param connectionId
     * @param subId
     * @return the SubscriberId object that identified the subscription
     * @throws ChannelException if the user is not subscribed to the channel
     */
    public SubscriberId unsubscribe(int connectionId, int subId) throws ChannelException {

        SubscriberId subberId = new SubscriberId(connectionId, subId);

        // check if the user is subscribed to channel
        if(IdTupleToChannel.containsKey(subberId) == false){
            throw new ChannelException("subId doesn't exist");
        }

        //success
        String channel = IdTupleToChannel.get(subberId);
        channelToIdTuple.get(channel).remove(subberId);
        IdTupleToChannel.remove(subberId);  
        return subberId;
    }

    /**
     * returns a set of SubscriberId objects that identify the subscriptions to the channel.
     * @param channel
     * @throws ChannelException if the channel doesn't exist
     */
    public Set<SubscriberId> getChannelSubscribers(String channel) throws ChannelException {
        if(channelToIdTuple.containsKey(channel) == false){
            throw new ChannelException("channel doesn't exist");
        }
        return channelToIdTuple.get(channel);
    }
}
