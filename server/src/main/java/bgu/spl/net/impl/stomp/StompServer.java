package bgu.spl.net.impl.stomp;

import java.io.IOException;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.ThreadPerClient;

public class StompServer{
    public static void main(String[] args) {

        if(args.length != 2){
            System.out.println("Usage: java StompServer <port> <Reactor | ThreadPerClient>");
            return;
        }
        if(args[0].matches("[0-9]+"))
            System.out.println("Port: " + args[0]);
        else{
            System.out.println("Invalid port");
            System.out.println("Usage: java StompServer <port> <Reactor | ThreadPerClient>");
            return;
        }
        
        if(args[1].toLowerCase().equals("reactor"))
            System.out.println("Starting Reactor Server...");
        else if(args[1].toLowerCase().equals("threadperclient"))
            System.out.println("Starting Thread Per Client Server...");
        else{
            System.out.println("Invalid server type");
            System.out.println("Usage: java StompServer <port> <Reactor | ThreadPerClient>");
            return;
        }


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
