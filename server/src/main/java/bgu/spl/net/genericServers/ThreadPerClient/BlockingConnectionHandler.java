package bgu.spl.net.genericServers.ThreadPerClient;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T> {

    private final MessagingProtocol<T> protocol;
    private final MessageEncoderDecoder<T> encdec;
    private final Socket sock;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;

    private volatile ConcurrentLinkedQueue<T> messagesToSend;

    public BlockingConnectionHandler(Socket sock, MessageEncoderDecoder<T> reader, MessagingProtocol<T> protocol) {
        this.sock = sock;
        this.encdec = reader;
        this.protocol = protocol;
        this.messagesToSend = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        try (Socket sock = this.sock) { //just for automatic closing
            int read;

            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());

            // read = in.read();
            // T firstMessage = encdec.decodeNextByte((byte) read);
            // if (firstMessage != null) {
            //     T response = protocol.start(firstMessage);
            //     if (response != null) {
            //         out.write(encdec.encode(response));
            //         out.flush();
            //     }
            // }
            

            while (!protocol.shouldTerminate() && connected) {

                if((read = in.read()) > 0){
                    T nextMessage = encdec.decodeNextByte((byte) read);
                    if (nextMessage != null) {
                        T response = protocol.process(nextMessage);
                        if (response != null) {
                            out.write(encdec.encode(response));
                            out.flush();
                        }
                    }
                }
                try{
                    while(true){
                        T msg = messagesToSend.remove();
                        out.write(encdec.encode(msg));
                        out.flush();
                    }
                }catch(NoSuchElementException e){
                    //no more messages to send
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        connected = false;
        sock.close();
    }

    @Override
    public void send(T msg) {
        messagesToSend.add(msg);
        synchronized(in){in.notifyAll();}
    }
}
