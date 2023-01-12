#include "CommandParser.h"
#include "Frame.h"
#include "UserData.h"
#include "ConnectionHandler.h"
#include "ConnectFrame.h"
#include "DisconnectFrame.h"
#include "SubscribeFrame.h"
#include "UnsubscribeFrame.h"
#include "event.h"
#include <fstream>
#include <sstream>
#include <iostream>
#include "SendFrame.h"

bool CommandParser::parseCommand(string commandToParse)
{
    if(commandToParse == "") return false;
    istringstream pa(commandToParse);
    string parameter;
    string command;
    getline(pa, command, ' ');
    vector<string> commandParameters;
    while(getline(pa, parameter, ' ')){
        commandParameters.push_back(parameter);
    }
    if(command == "login"){
      return parseLoginCommand(commandParameters);
    }
    else if (command == "logout"){
      parseLogoutCommand();
    }
    else if (command == "join"){
      parseJoinCommand(commandParameters);
    }
    else if (command == "exit"){
      parseExitCommand(commandParameters);
    }

    else if (command == "report"){
      parseReportCommand(commandParameters);
    }

    else if (command == "summary"){
      parseSummaryCommand(commandParameters);
    }
    return false;
}

bool CommandParser::parseLoginCommand(vector<string> commandParameters)
{
    if(commandParameters.size() != 3){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: login {host:port} {username} {password}" << endl;
        return false;
    }
    string hostPort = commandParameters[0];
    string host = hostPort.substr(0, hostPort.find(':'));
    string _port = hostPort.substr(hostPort.find(':') + 1);
    int port = stoi(_port); //TODO: check if this is correct

    ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);

    string username = commandParameters[1];
    string password = commandParameters[2];

    Frame* frame = ConnectFrame::get(host, username, password);
    UserData& ud = UserData::getInstance();
    ud.setHandler(*connectionHandler);
    ud.addAction(frame);
    ud.setUserName(username);
    return true;
}

std::vector<std::string> CommandParser::split(std::string str, char delimiter) {
  std::vector<std::string> tokens;
  std::string token;
  size_t start = 0;
  size_t end = 0;
  while ((end = str.find(delimiter, start)) != std::string::npos) {
    token = str.substr(start, end - start);
    tokens.push_back(token);
    start = end + 1;
  }
  tokens.push_back(str.substr(start));
  return tokens;
}

void CommandParser::parseLogoutCommand() {
    DisconnectFrame * frame = DisconnectFrame::get();
    UserData& ud = UserData::getInstance();
    ud.addAction(frame);
}

void CommandParser::parseJoinCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 1){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: join {game_name}" << endl;
        return;
    }
    string gameName = commandParameters[0];
    SubscribeFrame* frame = SubscribeFrame::get(gameName);
    UserData& ud = UserData::getInstance();
    ud.addAction(frame);
}

void CommandParser::parseExitCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 1){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: exit {game_name}" << endl;
        return;
    }
    string gameName = commandParameters[0];
    UnsubscribeFrame* frame = UnsubscribeFrame::get(gameName);
    UserData& ud = UserData::getInstance();
    ud.addAction(frame);
}

void CommandParser::parseReportCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 1){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: report {file}" << endl;
        return;
    }

    UserData & userData = UserData::getInstance();
    string fileName = commandParameters[0];
    names_and_events namesAndEvents = parseEventsFile("../data/" +fileName);
    vector<Event>& gameEvents = namesAndEvents.events;
    // for each
    for (Event& event : gameEvents) {
        SendFrame* sendFrame = SendFrame::get(event);
        userData.addAction(sendFrame);
    }
}

void CommandParser::parseSummaryCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 3){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: summary {game_name} {user} {file}" << endl;
        return;
    }
    string gameName = commandParameters[0];
    string userName = commandParameters[1];
    string fileName = commandParameters[2];
    ofstream summaryFile;
    summaryFile.open(fileName);

    string summaryString = ""; // TODO: collect the summary - perhaps need to contact the server
    summaryFile << summaryString << endl;
    summaryFile.close();
}




