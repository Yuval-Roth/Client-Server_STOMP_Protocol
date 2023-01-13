#include "Frame.h"
#include "ConnectFrame.h"
#include "ConnectedFrame.h"
#include "SendFrame.h"
#include "SubscribeFrame.h"
#include "UnsubscribeFrame.h"
#include "DisconnectFrame.h"
#include "MessageFrame.h"
#include "ReceiptFrame.h"
#include "ErrorFrame.h"
#include "UserData.h"


UserData& Frame::userData = UserData::getInstance();
const string Frame::PROTOCOL_VERSION = "1.2";

Frame::Frame(StompCommand command, unordered_map<string, string> headers, string frameBody)
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
    while (getline(stream, line) && line != "") {
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
    output +=  commandString(command) + NEW_LINE;
    for (const auto& header : headers) {
        output += header.first + HEADER_DELIMITER + header.second + NEW_LINE;
    }
    output += NEW_LINE;
    if (!frameBody.empty()) {
        output += frameBody;
    }
    return output;
}

StompCommand Frame::getCommand()
{
    return command;
}

unordered_map<string, string> Frame::getHeaders()
{
    return headers;
}

string Frame::getFrameBody()
{
    return frameBody;
}

string Frame::commandString(StompCommand command) {
    if(command == CONNECT) {
        return "CONNECT";
    } else if(command == CONNECTED) {
        return "CONNECTED";
    } else if(command == SEND) {
        return "SEND";
    } else if(command == SUBSCRIBE) {
        return "SUBSCRIBE";
    } else if(command == UNSUBSCRIBE) {
        return "UNSUBSCRIBE";
    } else if(command == DISCONNECT) {
        return "DISCONNECT";
    } else if(command == MESSAGE) {
        return "MESSAGE";
    } else if(command == RECEIPT) {
        return "RECEIPT";
    } else if(command == ERROR) {
        return "ERROR";
    } else {
        return "";
    }
}
