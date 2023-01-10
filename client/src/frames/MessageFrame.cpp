#include "MessageFrame.h"
#include "UserData.h"
#include "../include/event.h"

MessageFrame::MessageFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody){}

void MessageFrame::execute()
{
    cout<<frameBody<<endl;
    userData.addGameEvent(new Event(frameBody));
}