package bgu.spl.net.impl.stomp;

import java.io.IOException;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.ThreadPerClient;

public class StompServer{
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        BaseServer<String> server;

        switch(args[1].toLowerCase()){
            case "reactor":
                server = new Reactor<String>(
                    5,
                    port,
                    () -> new StompMessagingProtocolImpl(),
                    () -> new StompMessageEncoderDecoder()
                );
                break;
            case "threadperclient":
                server = new ThreadPerClient<String>(
                    port,
                    () -> new StompMessagingProtocolImpl(),
                    () -> new StompMessageEncoderDecoder()
                );   
                break;
            default: 
                throw new IllegalArgumentException("Invalid server type");
        }
        server.serve();
        try{
            server.close();
        }catch(IOException ex){}
    }
}
