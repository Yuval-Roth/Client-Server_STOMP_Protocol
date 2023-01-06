#pragma once

enum StompCommand {
    CONNECT,
    CONNECTED,
    SEND, 
    SUBSCRIBE, 
    UNSUBSCRIBE, 
    DISCONNECT, 
    MESSAGE, 
    RECEIPT, 
    ERROR
};