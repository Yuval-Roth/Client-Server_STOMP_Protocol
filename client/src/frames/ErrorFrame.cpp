#include "ErrorFrame.h"
#include "UserData.h"
#include <iostream>

ErrorFrame::ErrorFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::ERROR, headers, frameBody){}

void ErrorFrame::execute()
{
    cerr << "~ Error: " << getHeaders().at("message") << endl;
    if(frameBody != "")
    {
        cerr << "~ Full error Details: " << frameBody << endl;
    }
    cerr << "~ Connection terminated" << endl;
    cerr << "~ press enter to continue" << endl;

    userData.terminate();
}
