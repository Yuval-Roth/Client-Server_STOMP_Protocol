package bgu.spl.net.genericServers.ThreadPerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;
import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.genericServers.BaseServer;

public class ThreadPerClient<T> extends BaseServer<T>{

    public ThreadPerClient(int port, Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {
        super(port, protocolFactory, encdecFactory);
    }

    @Override
    public void serve() {
    
            try (ServerSocket serverSock = new ServerSocket(port)) {
        		System.out.println("ThreadPerClient server started");

                this.sock = serverSock; //just to be able to close

                while (!Thread.currentThread().isInterrupted()) {

                    Socket clientSock = serverSock.accept();

                    BlockingConnectionHandler<T> handler = new BlockingConnectionHandler<>(
                            clientSock,
                            encdecFactory.get(),
                            protocolFactory.get());

                    new Thread(handler).start();
                }
            } catch (IOException ex) {
            }

            System.out.println("server closed!!!");   
    }
}
