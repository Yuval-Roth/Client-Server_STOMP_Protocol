#include "ConnectFrame.h"

ConnectFrame::ConnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody)
{
}

void ConnectFrame::execute(ConnectionHandler &handler)
{
}