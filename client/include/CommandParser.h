#pragma once
#include <vector>
#include "Frame.h"
#include "UserData.h"
#include <sstream>
#include <iostream>

class CommandParser
{
private:
    static std::vector<std::string> split(std::string str, char delimiter);
public:
    CommandParser() = delete;
    static void parseCommand(string commandToParse);
    static void parseLoginCommand(vector<string> commandParameters);
};




