#include "ErrorFrame.h"
#include "UserData.h"

ErrorFrame::ErrorFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::ERROR, headers, frameBody){}

void ErrorFrame::execute()
{
    cout << "Error: " << getHeaders().at("message") << endl;
    cout << "Full error details: " << getFrameBody() << endl;
    cout << "Connection terminated" << endl;
    userData.terminate();
}
