#pragma once

using namespace std;

#include <vector>
#include "DisconnectFrame.h"

class CommandParser
{
private:
    static vector<string> split(string str, char delimiter);
public:
    CommandParser() = delete;
    static void parseCommand(string commandToParse);
    static void parseLoginCommand(vector<string> commandParameters);

    static void parseLogoutCommand();
};




