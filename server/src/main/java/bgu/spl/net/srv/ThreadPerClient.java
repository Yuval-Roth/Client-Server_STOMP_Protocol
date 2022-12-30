package bgu.spl.net.srv;

import java.io.IOException;
import java.util.function.Supplier;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;

public class ThreadPerClient<T> extends BaseServer<T>{

    public ThreadPerClient(int numThreads, int port, Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {
        super(port, protocolFactory, encdecFactory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void serve() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void execute(BlockingConnectionHandler<T> handler) {
        // TODO Auto-generated method stub
        
    }

    

    
}
