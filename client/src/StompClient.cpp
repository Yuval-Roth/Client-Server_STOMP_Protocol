using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"
#include "frames/ExecutableFrame.h"
#include "CommandParser.h"
#include <iostream>
#include <vector>
#include <thread>
#include <ios>

void connect();

void actorThread_run() {
	UserData& userData = UserData::getInstance();
    ConnectionHandler& handler = userData.getHandler();

	while(userData.shouldTerminate() == false){
        string message;
        if(handler.getFrameAscii(message, '\0') && message.empty() == false) {
            ExecutableFrame* responseFrame = ExecutableFrame::parse(message);
            responseFrame->execute();
            delete responseFrame;
        }
	}
}

void connect() {
    string userInput;
    bool loggedIn = false;
    while (!loggedIn) {
        cout<<"~ Please enter a login command:"<<endl;
        UserData& userData = UserData::getInstance();
        getline(cin, userInput);

        vector<Frame*> frames;
        try{
            frames = CommandParser::parseCommand(userInput);
        }catch(ios_base::failure& ignored){
            continue;
        }

        ConnectionHandler& handler = userData.getHandler();

        if(handler.connect() == false) continue;

        for(Frame* frame : frames){
            handler.sendFrameAscii(frame->toString(), '\0');
            delete frame;
        }

        string loginResponse;
        if(handler.getFrameAscii(loginResponse, '\0') && loginResponse.empty() == false) {
            ExecutableFrame* responseFrame = ExecutableFrame::parse(loginResponse);
            if(responseFrame->getCommand() == CONNECTED) {
                responseFrame->execute();
                userData.setConnected(true);
                loggedIn = true;
            }
            else {
                cout << "~ Login failed: "+ responseFrame->getHeaders().at("message") << endl;
            }
            delete responseFrame;
            if(userData.isConnected() == false){
                UserData::deleteInstance(true,true,true);
                continue;	// Login failed
            }
        }
    }
}

int main() {
    while(true){

        cout << "~ Welcome to STOMP." << endl;

        connect();

        thread actorThread(actorThread_run);

        string userInput;
        UserData& userData = UserData::getInstance();
        ConnectionHandler& handler = userData.getHandler();
        while (!userData.shouldTerminate()) {
            getline(cin, userInput);
            if(userData.shouldTerminate()) break;
            vector<Frame*> frames;
            try{
                frames = CommandParser::parseCommand(userInput);
            }catch(ios_base::failure& ignored){continue;}
            for(Frame* frame : frames){
                handler.sendFrameAscii(frame->toString(), '\0');
                delete frame;
            }
        }
        actorThread.join();
        UserData::deleteInstance(true,true,true);
    }
    return 0;
}

