#include "CommandParser.h"
#include "Frame.h"
#include "UserData.h"
#include "ConnectionHandler.h"
#include "ConnectFrame.h"
#include "DisconnectFrame.h"
#include "SubscribeFrame.h"
#include "UnsubscribeFrame.h"
#include "event.h"
#include <sstream>
#include <fstream>
#include <iostream>
#include "SendFrame.h"

void CommandParser::parseCommand(string commandToParse)
{
  vector<string> commandParameters = split(commandToParse, ' ');
  string command = commandParameters[0];
  if(command == "login"){
      parseLoginCommand(commandParameters);
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
      parseSummaryCommand(commandParameters)
  }
}

void CommandParser::parseLoginCommand(vector<string> commandParameters)
{
  if(commandParameters.size() != 4){
      cout << "Invalid number of parameters" << endl;
      cout << "Usage: login {host:port} {username} {password}" << endl;
      return;
  }
  string hostPort = commandParameters[1];
  string host = hostPort.substr(0, hostPort.find(':'));
  int port = stoi(hostPort.substr(hostPort.find(':'), hostPort.length())); //TODO: check if this is correct

  ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);

  string username = commandParameters[2];
  string password = commandParameters[3];

  Frame* frame = ConnectFrame::get(host, username, password);
  UserData& ud = UserData::getInstance();
  ud.addAction(frame);
  ud.setHandler(*connectionHandler);
  ud.setUserName(username);
  ud.notifyAll();

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
      ud.setConnected(false);
      ud.notifyAll();
}

void CommandParser::parseJoinCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 2){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: join {game_name}" << endl;
        return;
    }
    string gameName = commandParameters[1];
    SubscribeFrame* frame = SubscribeFrame::get(gameName);
    UserData& ud = UserData::getInstance();
    ud.addAction(frame);
    ud.notifyAll();
}

void CommandParser::parseExitCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 2){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: exit {game_name}" << endl;
        return;
    }
    string gameName = commandParameters[1];
    UnsubscribeFrame* frame = UnsubscribeFrame::get(gameName);
    UserData& ud = UserData::getInstance();
    ud.addAction(frame);
    ud.notifyAll();
}

void CommandParser::parseReportCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 2){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: report {file}" << endl;
        return;
    }

    UserData & userData = UserData::getInstance();
    string fileName = commandParameters[1];
    names_and_events namesAndEvents = parseEventsFile(fileName);
    vector<Event> gameEvents = namesAndEvents.events;
    // for each
    for (Event event : gameEvents) {
        SendFrame* sendFrame = SendFrame::get(event);
        userData.addAction(sendFrame);
    }
    userData.notifyAll();


}

void CommandParser::parseSummaryCommand(vector<string> commandParameters) {
    if(commandParameters.size() != 4){
        cout << "Invalid number of parameters" << endl;
        cout << "Usage: summary {game_name} {user} {file}" << endl;
        return;
    }
    string gameName = commandParameters[1];
    string userName = commandParameters[2];
    string fileName = commandParameters[3];
    ofstream summaryFile;
    summaryFile.open(fileName);
    string summaryString = ""; // TODO: collect the summary - perhaps need to contact the server
    summaryFile << summaryString << endl;
    summaryFile.close();

    UserData & userData = UserData::getInstance();

}

}


