#include "DisconnectFrame.h"
#include "UserData.h"

DisconnectFrame::DisconnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}



