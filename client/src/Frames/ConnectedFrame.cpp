#include "ConnectedFrame.h"
#include "UserData.h"

ConnectedFrame::ConnectedFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody)
{
    
}


void ConnectedFrame::execute(ConnectionHandler &connectionHandler) {
    userData.setLoggedIn(true);
    cout << "Login successful!" << endl;

}