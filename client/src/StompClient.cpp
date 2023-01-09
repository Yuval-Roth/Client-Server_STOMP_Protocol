
using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"

int main(int argc, char *argv[]) {

	thread actorThread(ActorThread_run);
	UserData& userData = UserData::getInstance();
	



	return 0;
}

void ActorThread_run() {
	UserData& userData = UserData::getInstance();
	ConnectionHandler handler = ConnectionHandler("localhost", 7777);

	while(userData.isConnected() == false){
		userData.wait();
		Frame actionQueue = userData.getActionQueue().front();
		userData.getActionQueue().pop();
		handler.sendFrameAscii(actionQueue.toString(), '\0');
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