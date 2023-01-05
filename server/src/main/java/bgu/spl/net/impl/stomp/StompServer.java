package bgu.spl.net.impl.stomp;

import java.io.IOException;

import bgu.spl.net.genericServers.BaseServer;
import bgu.spl.net.genericServers.Reactor.Reactor;
import bgu.spl.net.genericServers.ThreadPerClient.ThreadPerClient;
import bgu.spl.net.impl.stomp.Backend.StompFacade;
import bgu.spl.net.impl.stomp.Service.StompMessageEncoderDecoder;
import bgu.spl.net.impl.stomp.Service.StompMessagingProtocolImpl;

public class StompServer{
    public static void main(String[] args) {

        if(args.length != 2){
            System.out.println("Accepted arguments: <port> <Reactor | TPC>");
            return;
        }
        if(args[0].matches("[0-9]+"))
            System.out.println("Port: " + args[0]);
        else{
            System.out.println("Invalid port");
            System.out.println("Accepted arguments: <port> <Reactor | TPC>");
            return;
        }
        
        if(args[1].toLowerCase().equals("reactor"))
            System.out.println("Starting Reactor Server...");
        else if(args[1].toLowerCase().equals("tpc"))
            System.out.println("Starting Thread Per Client Server...");
        else{
            System.out.println("Invalid server type");
            System.out.println("Accepted arguments: <port> <Reactor | TPC>");
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
            case "tpc":
                server = new ThreadPerClient<String>(
                    port,
                    () -> new StompMessagingProtocolImpl(),
                    () -> new StompMessageEncoderDecoder()
                );   
                break;
            default: 
                throw new IllegalArgumentException("Invalid server type");
        }

        StompFacade.getInstance(); // Initialize the StompFacade singleton to prevent parallel initialization
        server.serve();
        
        try{
            server.close();
        }catch(IOException ex){}
    }
}
