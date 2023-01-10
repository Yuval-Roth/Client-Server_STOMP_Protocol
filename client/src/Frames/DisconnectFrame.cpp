#include "DisconnectFrame.h"
#include "UserData.h"

DisconnectFrame::DisconnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void DisconnectFrame::execute(ConnectionHandler &handler)
{
    //TODO
    UserData::getInstance().setConnected(false);
}

