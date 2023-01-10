#pragma once

#include <vector>


class CommandParser
{
private:
    static std::vector<std::string> split(std::string str, char delimiter);
public:
    CommandParser() = delete;
    static void parseCommand(string commandToParse);
    static void parseLoginCommand(vector<string> commandParameters);
};




