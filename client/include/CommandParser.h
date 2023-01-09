#pragma once
#include <vector>
#include "Frame.h"
#include "ConnectionHandler.h"
#include "UserData.h"
#include "StompCommand.h"
#include <sstream>

class CommandParser
{
private:
    CommandParser(/* args */);
    ~CommandParser();
public:
    static void parseCommand(string commandToParse);
    ConnectionHandler* parseLoginCommand(vector<string> commandParameters);
};


