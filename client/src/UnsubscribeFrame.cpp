#include "UnsubscribeFrame.h"

UnsubscribeFrame::UnsubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void UnsubscribeFrame::execute(ConnectionHandler &handler)
{
    //TODO
}