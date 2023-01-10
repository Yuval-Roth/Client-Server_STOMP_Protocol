#include "MessageFrame.h"

MessageFrame::MessageFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody){}

void MessageFrame::execute()
{
    //TODO
}