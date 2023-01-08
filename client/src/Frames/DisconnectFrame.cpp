#include "DisconnectFrame.h"

DisconnectFrame::DisconnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void DisconnectFrame::execute(ConnectionHandler &handler)
{
    //TODO
}