#include "ErrorFrame.h"
#include "UserData.h"
#include <iostream>

ErrorFrame::ErrorFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::ERROR, headers, frameBody){}

void ErrorFrame::execute()
{
    cout << "~ Error: " << getHeaders().at("message") << endl;
    if(frameBody != "")
    {
        cout << "~ Full error Details: " << frameBody << endl;
    }
    cout << "~ Connection terminated" << endl;
    cout << "~ press enter to continue" << endl;

    userData.terminate();
}
