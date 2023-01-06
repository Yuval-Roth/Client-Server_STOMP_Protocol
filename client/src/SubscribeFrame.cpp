#include "SubscribeFrame.h"

SubscribeFrame::SubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void SubscribeFrame::execute(ConnectionHandler &handler)
{
    //TODO
}