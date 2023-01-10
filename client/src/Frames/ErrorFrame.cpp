#include "ErrorFrame.h"

ErrorFrame::ErrorFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(command, headers, frameBody){}

void ErrorFrame::execute()
{
}
