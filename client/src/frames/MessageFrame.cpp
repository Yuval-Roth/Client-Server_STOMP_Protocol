#include "MessageFrame.h"
#include "UserData.h"
#include "../include/event.h"

MessageFrame::MessageFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::MESSAGE, headers, frameBody){}

void MessageFrame::execute()
{
    cout<<frameBody<<endl;
    userData.addGameEvent(new Event(frameBody));
}