#include "CommandParser.h"
#include "Frame.h"
#include "UserData.h"
#include "ConnectionHandler.h"
#include "ConnectFrame.h"
#include "DisconnectFrame.h"
#include "SubscribeFrame.h"
#include "UnsubscribeFrame.h"

#include <sstream>
#include <iostream>

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
      parseReportCommand(commandParameters)
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
    string gameName = commandParameters[1];
    // TODO: Read JSON file, create game event, create SEND frame

}


