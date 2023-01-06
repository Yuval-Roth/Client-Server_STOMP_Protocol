#pragma once

using namespace std;

#include <unordered_map>
#include <string>
#include <sstream>

class Frame {
    public:
        static constexpr double PROTOCOL_VERSION = 1.2;
        static constexpr char END_OF_FRAME = '\0';
        static constexpr char NEW_LINE = '\n';
        static constexpr char HEADER_DELIMITER = ':';

        enum StompCommand {
            CONNECT, CONNECTED, SEND, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, MESSAGE, RECEIPT, ERROR
        };

    private:
        unordered_map<string, string> headers;
        string frameBody;
        StompCommand command;

    public:
        Frame(unordered_map<string, string> headers, string frameBody, StompCommand command)
            : headers(headers), frameBody(frameBody), command(command) {}

        static Frame* parse(string messageToParse) {
            istringstream stream(messageToParse);
            string line;
            getline(stream, line);
            StompCommand command = static_cast<StompCommand>(stoi(line));

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

        string toString() {
            string output = "";
            output += command + NEW_LINE;
            for (const auto& [key, value] : headers) {
                output += key + HEADER_DELIMITER + value + NEW_LINE;
            }
            output += NEW_LINE;
            if (!frameBody.empty()) {
                output += frameBody;
            }
            output += END_OF_FRAME;
            return output;
        }

    private:
        static Frame* createFrame(StompCommand command, unordered_map<string, string> headers, string frameBody) {
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
                default:
                    return nullptr;
                }
        }
};

