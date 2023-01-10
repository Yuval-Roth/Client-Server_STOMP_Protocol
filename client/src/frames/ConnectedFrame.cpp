#include "ConnectedFrame.h"

#include <iostream>

ConnectedFrame::ConnectedFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody){}


void ConnectedFrame::execute()
{
    cout << "Login successful" << endl;
}
