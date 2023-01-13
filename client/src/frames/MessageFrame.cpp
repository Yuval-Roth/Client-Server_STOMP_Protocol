#include "MessageFrame.h"
#include "UserData.h"
#include "../include/event.h"

MessageFrame::MessageFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::MESSAGE, headers, frameBody){}

void MessageFrame::execute()
{
    cout<<frameBody<<endl;
    string gameName = headers["destination"];
    gameName = gameName.substr(1);
    userData.addGameEvent(new Event(gameName, frameBody));
    // Where should we delete the event?
    // This is probably bad software engineering, but in terms of flow I think in the UserData destructor
}