#include "ExecutableFrame.h"

ExecutableFrame::ExecutableFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}

ExecutableFrame *ExecutableFrame::parse(string messageToParse)
{
    Frame* frame = Frame::parse(messageToParse);
    if(frame == nullptr){
        return nullptr;
    }
    else if(ExecutableFrame *executableFrame = dynamic_cast<ExecutableFrame*>(frame)){
        return executableFrame;
    }
    else{
        delete frame;
        throw std::runtime_error("Error: Frame is not executable, cannot parse");
    }
}
