package bgu.spl.net.genericServers;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.interfaces.Server;

import java.io.IOException;
import java.net.ServerSocket;
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
    }

    public abstract void serve();

    @Override
    public void close() throws IOException {
		if (sock != null)
			sock.close();
    }
}
