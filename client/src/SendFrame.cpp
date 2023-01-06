#include "SendFrame.h"

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void SendFrame::execute(ConnectionHandler &handler)
{
    //TODO
}