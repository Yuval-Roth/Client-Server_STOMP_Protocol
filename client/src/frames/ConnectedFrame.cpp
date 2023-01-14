#include "ConnectedFrame.h"

#include <iostream>

ConnectedFrame::ConnectedFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::CONNECTED, headers, frameBody){}


void ConnectedFrame::execute()
{
    cout << "~ Login successful" << endl;
    cout.flush();
}
