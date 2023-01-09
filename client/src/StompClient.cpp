
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
	userData.wait();
	Frame actionQueue = userData.getActionQueue().front();

}