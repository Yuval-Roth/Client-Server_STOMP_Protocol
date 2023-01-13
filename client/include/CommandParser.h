#pragma once

using namespace std;

#include <vector>
#include <string>

class Frame;

class CommandParser
{
private:
    static vector<string> split(string str, char delimiter);
public:
    CommandParser() = delete;

    static vector<Frame*> parseCommand(string commandToParse);

    static vector<Frame*> parseLoginCommand(vector<string>& commandParameters);

    static vector<Frame*> parseLogoutCommand();

    static vector<Frame*> parseJoinCommand(vector<string>& commandParameters);

    static vector<Frame*> parseExitCommand(vector<string>& commandParameters);

    static vector<Frame*> parseReportCommand(vector<string>& commandParameters);

    static void parseSummaryCommand(vector<string>& vector1);
};




