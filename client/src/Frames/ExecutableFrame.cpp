#include "ExecutableFrame.h"

ExecutableFrame::ExecutableFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody)
{
}

ExecutableFrame *ExecutableFrame::parse(string messageToParse)
{
    //TODO - ExecutableFrame::parse
    return nullptr;
}
