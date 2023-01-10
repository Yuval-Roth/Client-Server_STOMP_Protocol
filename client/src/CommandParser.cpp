#include "CommandParser.h"
#include "Frame.h"
#include <string>
#include <vector>
#include <sstream>
using namespace std;

void CommandParser::parseCommand(string commandToParse)
{
  //TODO
  // Frame* frame = new Frame(); //unused variable
  vector<string> commandParameters = split(commandToParse, ' ');
  //vector<string> commandParameters;
  string command = commandParameters[0];
  if(command == "login"){
      parseLoginCommand(commandParameters);
  }
}

void CommandParser::parseLoginCommand(vector<string> commandParameters)
{
  // if(!commandParameters.size() == 4){ //dafuck is that lol
    if(commandParameters.size() != 4){
      cout << "Invalid number of parameters" << endl;
      cout << "Useage: login {host:port} {username} {password}" << endl;
      throw "Invalid number of parameters";
  }
  string hostPort = commandParameters[1];
  string host = hostPort.substr(0, hostPort.find(':'));
  int port = stoi(hostPort.substr(hostPort.find(':'), hostPort.length())); //TODO: check if this is correct

  ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);

  string username = commandParameters[2];
  string password = commandParameters[3];

  unordered_map<string, string> headersMap;
  headersMap["accept-version"] = "1.2";
  headersMap["host"] = host;
  headersMap["username"] = username;
  headersMap["password"] = password;
  StompCommand command = StompCommand::CONNECT;
  string frameBody = "";

  Frame* frame = new Frame(command, headersMap, frameBody);
  UserData& ud = UserData::getInstance();
  ud.addAction(frame);
  ud.setHandler(*connectionHandler);
  ud.setUserName(username);
  // ud.setPassword(password);
  ud.notifyAll();
  // TODO: make this a factory?


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
