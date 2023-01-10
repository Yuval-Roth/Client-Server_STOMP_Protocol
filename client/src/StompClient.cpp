using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"
#include "frames/ExecutableFrame.h"
#include <vector>

int main(int argc, char *argv[]) {

	//thread actorThread(actorThread_run); // Understand why it doesn't compile 
		// note - it didn't compile even before I removed the connectionHandler argument from the function
	// UserData& userData = UserData::getInstance(); 
	return 0;
}

void actorThread_run() {
	UserData& userData = UserData::getInstance();
	ConnectionHandler& handler = userData.getHandler();
	Frame* connectFrame = userData.getFrameQueue().front();
	userData.getFrameQueue().pop();

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
		if(handler.getFrameAscii(message, '\0') & (userData.getFrameQueue().empty() == false)){
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
