#pragma once
#include <vector>
#include "Frame.h"
#include "ConnectionHandler.h"

class CommandParser
{
private:
    CommandParser(/* args */);
    ~CommandParser();
public:
    static Frame parseCommand(string commandToParse);
    void parseLoginCommand(vector<string> commandParameters);
};


