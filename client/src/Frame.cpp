#include "../include/Frame.h"
#include "../include/ConnectFrame.h"
#include "../include/ConnectedFrame.h"
#include "../include/SendFrame.h"
#include "../include/SubscribeFrame.h"
#include "../include/UnsubscribeFrame.h"
#include "../include/DisconnectFrame.h"
#include "../include/MessageFrame.h"
#include "../include/ReceiptFrame.h"
#include "../include/ErrorFrame.h"

Frame::Frame(unordered_map<string, string> headers, string frameBody, StompCommand command)
    : headers(headers), frameBody(frameBody), command(command) {}

Frame* Frame::parse(string messageToParse) {
    istringstream stream(messageToParse);
    string line;
    getline(stream, line);
    StompCommand command = parseCommand(line);

    unordered_map<string, string> headers;
    while (getline(stream, line) && line != "") {
        size_t delimiterPos = line.find(HEADER_DELIMITER);
        string headerName = line.substr(0, delimiterPos);
        string headerValue = line.substr(delimiterPos + 1);
        headers[headerName] = headerValue;
    }

    string frameBody;
    while (getline(stream, line) && line != string(1, END_OF_FRAME)) {
        frameBody += line + NEW_LINE;
    }

    if (frameBody.empty()) {
        frameBody = "";
    }

    return createFrame(command, headers, frameBody);
}

StompCommand Frame::parseCommand(string command) {
    if (command == "CONNECT") {
        return CONNECT;
    } else if (command == "CONNECTED") {
        return CONNECTED;
    } else if (command == "SEND") {
        return SEND;
    } else if (command == "SUBSCRIBE") {
        return SUBSCRIBE;
    } else if (command == "UNSUBSCRIBE") {
        return UNSUBSCRIBE;
    } else if (command == "DISCONNECT") {
        return DISCONNECT;
    } else if (command == "MESSAGE") {
        return MESSAGE;
    } else if (command == "RECEIPT") {
        return RECEIPT;
    } else if (command == "ERROR") {
        return ERROR;
    } else {
        throw "Invalid command";
    }
}

Frame* Frame::createFrame(StompCommand command, unordered_map<string, string> headers, string frameBody) {
    switch (command) {
        case CONNECT:
            return new ConnectFrame(headers, frameBody);
        case CONNECTED:
            return new ConnectedFrame(headers, frameBody);
        case SEND:
            return new SendFrame(headers, frameBody);
        case SUBSCRIBE:
            return new SubscribeFrame(headers, frameBody);
        case UNSUBSCRIBE:
            return new UnsubscribeFrame(headers, frameBody);
        case DISCONNECT:
            return new DisconnectFrame(headers, frameBody);
        case MESSAGE:
            return new MessageFrame(headers, frameBody);
        case RECEIPT:
            return new ReceiptFrame(headers, frameBody);
        case ERROR:
            return new ErrorFrame(headers, frameBody);
        default: return nullptr;
    }
}

string Frame::toString() {
    string output = "";
    output += command + NEW_LINE;
    for (const auto& header : headers) {
        output += header.first + HEADER_DELIMITER + header.second + NEW_LINE;
    }
    output += NEW_LINE;
    if (!frameBody.empty()) {
        output += frameBody;
    }
    output += END_OF_FRAME;
    return output;
}