using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"
#include "frames/ExecutableFrame.h"
#include "CommandParser.h"
#include <iostream>
#include <vector>
#include <thread>

void connect();

void handleFrameQueue() {
    UserData& userData = UserData::getInstance();
    ConnectionHandler& handler = userData.getHandler();
    queue<Frame*>& frameQueue = userData.getFrameQueue();
    mutex& m = userData.getLock();
    unique_lock<mutex> lock(m);
    while(frameQueue.empty() == false){
        Frame* frame = frameQueue.front();
        frameQueue.pop();
        handler.sendFrameAscii(frame->toString(), '\0');
        delete frame;
    }
    lock.unlock();
}

void actorThread_run() {
	UserData& userData = UserData::getInstance();
    ConnectionHandler& handler = userData.getHandler();

	while(userData.shouldTerminate() == false){

        string message;
        if(handler.getFrameAscii(message, '\0') && message != "") {
            ExecutableFrame* responseFrame = ExecutableFrame::parse(message);
            responseFrame->execute();
        }

		if((userData.getFrameQueue().empty() == false) && handler.getFrameAscii(message, '\0')){
			ExecutableFrame* frame = ExecutableFrame::parse(message);
			frame->execute();
		}
		else{
            handleFrameQueue();
        }

	}
}

void connect() {
    string userInput;
    bool loggedIn = false;
    while (!loggedIn) {
        cout<<"Please enter a login command:"<<endl;
        UserData& userData = UserData::getInstance();
        getline(cin, userInput);
        CommandParser::parseCommand(userInput);
        ConnectionHandler& handler = userData.getHandler();

        if(handler.connect() == false) continue;
        Frame* connectFrame = userData.getFrameQueue().front();
        userData.getFrameQueue().pop();
        handler.sendFrameAscii(connectFrame->toString(), '\0');

        string loginResponse;
        if(handler.getFrameAscii(loginResponse, '\0') && loginResponse.empty() == false) {
            ExecutableFrame* responseFrame = ExecutableFrame::parse(loginResponse);
            if(responseFrame->getCommand() == CONNECTED) {
                responseFrame->execute();
                userData.setConnected(true);
                loggedIn = true;
            }
            else {
                cout << "Login failed: "+ responseFrame->getHeaders().at("message") << endl;
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

        cout << "Welcome to STOMP." << endl;

        connect();

        thread actorThread(actorThread_run);

        string userInput;
        UserData& userData = UserData::getInstance();
        ConnectionHandler& handler = userData.getHandler();
        while (!userData.shouldTerminate()) {
            getline(cin, userInput);
            CommandParser::parseCommand(userInput);
            handleFrameQueue();
        }
        actorThread.join();
        UserData::deleteInstance(true,true,true);
    }
    return 0;
}

