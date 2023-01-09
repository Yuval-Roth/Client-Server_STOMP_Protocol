
using namespace std;

#include "ConnectionHandler.h"
#include "UserData.h"


UserData userData = UserData();

int main(int argc, char *argv[]) {

	thread actorThread(ActorThread_run);

	



	return 0;
}

void ActorThread_run() {

	ConnectionHandler handler = ConnectionHandler("localhost", 7777);
	userData.wait();
	Frame actionQueue = userData.getActionQueue().front();

}