
using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"
#include "ExecutableFrame.h"

int main(int argc, char *argv[]) {

	UserData& userData = UserData::getInstance();
	



	return 0;
}
void ActorThread_run(ConnectionHandler& handler) {
	UserData& userData = UserData::getInstance();

	while(userData.isConnected() == false){
		userData.wait();

		Frame* connectFrame = userData.getFrameQueue().front();
		userData.getFrameQueue().pop();

		handler.sendFrameAscii(connectFrame->toString(), '\0');
		
		string loginResponse;
		if(handler.getFrameAscii(loginResponse, '\0')) {
			Frame* loginFrame = Frame::parse(loginResponse);
			if(loginFrame->getCommand() == StompCommand::CONNECTED) {
				cout << "Login successful" << endl;
				userData.setConnected(true);
			}
			else {
				cout << "Login failed: "+ loginFrame->getHeaders().at("message") << endl;
			}
		}
	}
	while(userData.shouldTerminate() == false){
		//TODO main actor thread loop
		string message;
		if(handler.getFrameAscii(message, '\0')){
			ExecutableFrame* frame = ExecutableFrame::parse(message);
			frame->execute(handler);
		}
		else{
			queue<Frame*>& frameQueue = userData.getFrameQueue();
			mutex& m = userData.getLock();
			unique_lock<mutex> lock(m);
			while(frameQueue.empty() == false){
				Frame* frame = frameQueue.front();
				frameQueue.pop();
				handler.sendFrameAscii(frame->toString(), '\0');
			}
			lock.unlock();
		}

	}
	

}