#include "CommandParser.h"
#include "Frame.h"
#include <string>
#include <vector>
#include <sstream>
using namespace std;


CommandParser::CommandParser()
{
}

CommandParser::~CommandParser()
{
}

Frame CommandParser::parseCommand(string commandToParse)
{
    //TODO
    CommandParser commandParser;
    Frame* frame = new Frame();
    vector<string> commandParameters = split(commandToParse, ' ');
	string command = commandParameters[0];
	if(command == "login"){
        commandParser.parseLoginCommand(commandParameters);
    }
    return Frame();
}

void CommandParser::parseLoginCommand(vector<string> commandParameters)
{
    if(!commandParameters.length() == 4){
        cout << "Invalid number of parameters" << endl;
        cout << "Useage: login {host:port} {username} {password}" << endl;
        return;
    }
    string hostPort = commandParameters[1];
    string host = hostPort.substr(0, hostPort.find(':'));
    int port = stoi(hostPort.substr(hostPort.find(':'), hostPort.length()); //TODO: check if this is correct
    ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);
    string username = commandParameters[2];
    string password = commandParameters[3];

}