#include "CommandParser.h"
#include "Frame.h"
#include "UserData.h"
#include "ConnectionHandler.h"
#include "ConnectFrame.h"
#include "DisconnectFrame.h"
#include "SubscribeFrame.h"
#include "UnsubscribeFrame.h"
#include "event.h"
#include "SendFrame.h"

#include <fstream>
#include <sstream>
#include <unistd.h>
#include <iostream>
#include <ios>
#include "UserData.h"

vector<Frame*> CommandParser::parseCommand(string commandToParse)
{
    if(commandToParse == "") {
        throw ios_base::failure("");
    }

    istringstream pa(commandToParse);
    string parameter;
    string command;

    // get the command
    getline(pa, command, ' ');

    if(command != "login" && command != "logout" && command != "join" &&
          command != "exit" && command != "report" && command != "summary") {
        cerr << "~ Unknown command" << endl;
        cerr.flush();
        throw ios_base::failure("");
    }

    // get the parameters
    vector<string> commandParameters;
    while(getline(pa, parameter, ' ')){
        commandParameters.push_back(parameter);
    }

    // parse the command
    if(command == "login"){
      return parseLoginCommand(commandParameters);
    }
    if (UserData::getInstance().isConnected()==false) {
        cerr << "~ You must login first" << endl;
        cerr << "~ Usage: login {host:port} {username} {password}" << endl;
        throw ios_base::failure("");

    }
    else if (command == "logout"){
      return parseLogoutCommand();
    }
    else if (command == "join"){
      return parseJoinCommand(commandParameters);
    }
    else if (command == "exit"){
      return parseExitCommand(commandParameters);
    }

    else if (command == "report"){
      return parseReportCommand(commandParameters);
    }

    else if (command == "summary"){
      parseSummaryCommand(commandParameters);
    }
    throw ios_base::failure("");
}

vector<Frame*> CommandParser::parseLoginCommand(vector<string>& commandParameters)
{
    vector<Frame*> output;

    UserData& ud = UserData::getInstance();
    if(ud.isConnected()){
        cerr<<  "~ You are already logged in as \" "+ud.getUserName() +" \"" <<endl;
        throw ios_base::failure("");
    }
    else if(commandParameters.size() != 3){
        cerr << "~ Invalid number of parameters" << endl;
        cerr << "~ Usage: login {host:port} {username} {password}" << endl;
        throw ios_base::failure("");
    }
    else {
        string hostPort = commandParameters[0];
        string host = hostPort.substr(0, hostPort.find(':'));
        string _port = hostPort.substr(hostPort.find(':') + 1);
        for (char c : _port) { // check if port is a number
            if (!isdigit(c)) {
                cerr << "~ Invalid port, port must be a number" << endl;
                cerr << "~ Usage: login {host:port} {username} {password}" << endl;
                throw ios_base::failure("");
            }
        }
        int port = stoi(_port);
        ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);

        string username = commandParameters[1];
        string password = commandParameters[2];

        ud.setHandler(*connectionHandler);
        ud.setUserName(username);
        Frame* frame = ConnectFrame::get(host, username, password);
        output.push_back(frame);
    }

    return output;
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

vector<Frame*> CommandParser::parseLogoutCommand() {

    vector<Frame*> output;

    DisconnectFrame * frame = DisconnectFrame::get();
    output.push_back(frame);

    return output;
}

vector<Frame*> CommandParser::parseJoinCommand(vector<string>& commandParameters) {

    vector<Frame*> output;

    if(commandParameters.size() != 1){
        cerr << "~ Invalid number of parameters" << endl;
        cerr << "~ Usage: join {game_name}" << endl;
    }
    else{
        string gameName = commandParameters[0];
        SubscribeFrame* frame = SubscribeFrame::get(gameName);
        output.push_back(frame);
    }
    return output;
}

vector<Frame*> CommandParser::parseExitCommand(vector<string>& commandParameters) {

    vector<Frame*> output;

    if(commandParameters.size() != 1){
        cerr << "~ Invalid number of parameters" << endl;
        cerr << "~ Usage: exit {game_name}" << endl;
    }
    else {
        string gameName = commandParameters[0];
        UnsubscribeFrame* frame = UnsubscribeFrame::get(gameName);
        output.push_back(frame);
    }
    return output;
}

vector<Frame*> CommandParser::parseReportCommand(vector<string>& commandParameters) {

    vector<Frame*> output;

    if(commandParameters.size() != 1){
        cerr << "~ Invalid number of parameters" << endl;
        cerr << "~ Usage: report {file}" << endl;
    }
    else{
        string fileName = commandParameters[0];
        if(fileName.find(".json") != string::npos || fileName.find('/') != string::npos || fileName.find("..") != string::npos){
            cerr << "~ report error: output file name cannot contain file extension or path to folder, e.g '.json' , '/', '..'" << endl;
        }
        else{
            char cwd[1024];
            getcwd(cwd, sizeof(cwd));
            string path(cwd, sizeof(cwd));

            if(path.find("/SPL_Assignment3/") == string::npos){
            cerr<< "~ report error: the client program directory needs to be in the 'SPL_Assignment3' directory, but it is not. "<<endl;
            cerr<< "~ Would you like to insert the full path instead? y/n"<<endl;
            string answer;
            getline(cin,answer);
            if(answer == "y"){
                cout<<"~ Insert full path:"<<endl;
                getline(cin,path);
            }
            else if(answer == "n"){
                cout<<"~ canceling"<<endl;
                return output;
            }
            else {
                cout<<"~ invalid answer, canceling"<<endl;
                return output;
            }
            }
            else{
                path = path.substr(0, path.find("/SPL_Assignment3/")+17) + "client/data/" + fileName + ".json";
            }
    

            try {
                names_and_events namesAndEvents = parseEventsFile(path);
                vector<Event>& gameEvents = namesAndEvents.events;
                for (Event& event : gameEvents) {
                    SendFrame* sendFrame = SendFrame::get(event);
                    output.push_back(sendFrame);
                }
            }
            catch (const std::exception& e) {
                cerr << "~ report error: file probably does not exist in the data directory. full error details:\n "<<e.what() << endl;
            }
        }
    }
    return output;
}

void CommandParser::parseSummaryCommand(vector<string>& commandParameters) {

    if(commandParameters.size() != 3){
        cerr << "~ Invalid number of parameters" << endl;
        cerr << "~ Usage: summary {game_name} {user} {output file name}" << endl;
        return;
    }
    string gameName = commandParameters[0];
    string userName = commandParameters[1];
    string fileName = commandParameters[2];

    if(fileName.find(".json") != string::npos || fileName.find('/') != string::npos || fileName.find("..") != string::npos){
        cerr << "~ summary error: output file name cannot contain file extension or path to folder, e.g '.json' , '/', '..'" << endl;
        return;
    }

    char cwd[1024];
    getcwd(cwd, sizeof(cwd));
    string path(cwd, sizeof(cwd));

    if(path.find("/SPL_Assignment3/") == string::npos){
        cerr<< "~ summary error: the client program directory needs to be in the 'SPL_Assignment3' directory, but it is not. "<<endl;
        cerr<< "~ Would you like to insert the full path instead? y/n"<<endl;
        string answer;
        getline(cin,answer);
        if(answer == "y"){
            cout<<"~ Insert full path:"<<endl;
            getline(cin,path);
        }
        else if(answer == "n"){
            cout<<"~ canceling"<<endl;
            return;
        }
        else {
            cout<<"~ invalid answer, canceling"<<endl;
            return;
        }
    }
    else{
        path = path.substr(0, path.find("/SPL_Assignment3/")+17) + "client/data/" + fileName + ".json";
    }

    UserData & userData = UserData::getInstance();
    string summaryString;
    try{
        summaryString = userData.getSummary(userName, gameName);
        cout << summaryString << endl;
    }catch(ios_base::failure& e){
        cerr << "~ summary error: " << e.what() << endl;
        return;
    }

    ofstream summaryFile;
    summaryFile.open(path);
    summaryFile << summaryString << endl;
    summaryFile.close();
}




