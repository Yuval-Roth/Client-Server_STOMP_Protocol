package bgu.spl.net.srv;

import java.util.function.Supplier;
import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;

public class ThreadPerClient<T> extends BaseServer<T>{

    public ThreadPerClient(int port, Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {
        super(port, protocolFactory, encdecFactory);
        //TODO constructor of ThreadPerClient
    }

    @Override
    public void serve() {
        //TODO change this to support the thread per client mode
    
        //     try (ServerSocket serverSock = new ServerSocket(port)) {
        // 		System.out.println("Server started");

        //         this.sock = serverSock; //just to be able to close

        //         while (!Thread.currentThread().isInterrupted()) {

        //             Socket clientSock = serverSock.accept();

        //             BlockingConnectionHandler<T> handler = new BlockingConnectionHandler<>(
        //                     clientSock,
        //                     encdecFactory.get(),
        //                     protocolFactory.get());

        //             execute(handler);
        //         }
        //     } catch (IOException ex) {
        //     }

        //     System.out.println("server closed!!!");   
    }

    // @Override
    // protected void execute(BlockingConnectionHandler<T> handler) {
        
    // }

    

    
}
