#pragma once
#include <vector>
#include "Frame.h"
#include "ConnectionHandler.h"
#include "UserData.h"
#include "StompCommand.h"
#include <sstream>
#include <iostream>

class CommandParser
{
private:
    CommandParser();
    ~CommandParser();
    std::vector<std::string> split(std::string str, char delimiter);
public:
    void parseCommand(string commandToParse);
    void parseLoginCommand(vector<string> commandParameters);
};




