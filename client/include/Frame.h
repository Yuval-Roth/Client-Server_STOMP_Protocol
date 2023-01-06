#pragma once

using namespace std;

#include "StompCommand.h"

#include <unordered_map>
#include <string>
#include <sstream>

class Frame {
    public:
        static constexpr double PROTOCOL_VERSION = 1.2;
        static constexpr char END_OF_FRAME = '\0';
        static constexpr char NEW_LINE = '\n';
        static constexpr char HEADER_DELIMITER = ':';
           
        Frame(unordered_map<string, string> headers, string frameBody, StompCommand command);
        static Frame* parse(string messageToParse);
        string toString();
        virtual ~Frame() = default;

    private:
        unordered_map<string, string> headers;
        string frameBody;
        StompCommand command;

        static StompCommand parseCommand(string command);   
        static Frame* createFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};

