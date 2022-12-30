package bgu.spl.net.srv;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

public abstract class BaseServer<T> implements Server {

    protected final int port;
    protected final Supplier<MessagingProtocol<T>> protocolFactory;
    protected final Supplier<MessageEncoderDecoder<T>> encdecFactory;
    protected ServerSocket sock;

    public BaseServer(
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {

        this.port = port;
        this.protocolFactory = protocolFactory;
        this.encdecFactory = encdecFactory;
		this.sock = null;
    }

    public abstract void serve();

    // @Override
    // public void serve() {

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
    // }

    @Override
    public void close() throws IOException {
		if (sock != null)
			sock.close();
    }

    protected abstract void execute(BlockingConnectionHandler<T>  handler);

}
