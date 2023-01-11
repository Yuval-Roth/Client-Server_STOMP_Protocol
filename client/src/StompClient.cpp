using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"
#include "frames/ExecutableFrame.h"
#include "CommandParser.h"
#include <iostream>
#include <vector>
#include <thread>

void actorThread_run() {
	UserData& userData = UserData::getInstance();
	Frame* connectFrame = userData.getFrameQueue().front();
	userData.getFrameQueue().pop();
    ConnectionHandler& handler = userData.getHandler();
	handler.sendFrameAscii(connectFrame->toString(), '\0');

	string loginResponse;
	if(handler.getFrameAscii(loginResponse, '\0')) {
		ExecutableFrame* responseFrame = ExecutableFrame::parse(loginResponse);
		if(responseFrame->getCommand() == StompCommand::CONNECTED) {
			responseFrame->execute();
			userData.setConnected(true);
		}
		else {
			cout << "Login failed: "+ responseFrame->getHeaders().at("message") << endl;
		}
		delete responseFrame;
		if(userData.isConnected() == false){
			return;	// Login failed, terminate thread
		}
	}

	while(userData.shouldTerminate() == false){

		string message;
		if((userData.getFrameQueue().empty() == false) && handler.getFrameAscii(message, '\0')){
			ExecutableFrame* frame = ExecutableFrame::parse(message);
			frame->execute();
		}
		else{
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

	}
}

int main() {

    string userInput;
    cout << "Welcome to STOMP. Please enter a login command:" << endl;
    cin >> userInput;

    bool loggedIn = false;
    while (!loggedIn) {
        loggedIn = CommandParser::parseCommand(userInput);
    }

    UserData& userData = UserData::getInstance();
    ConnectionHandler& handler = userData.getHandler();
    thread actorThread(actorThread_run);

    while (!userData.shouldTerminate()) {
        cin >> userInput;
        CommandParser::parseCommand(userInput);
        handler.interrupt();
    }

    actorThread.join();
    return 0;
}
