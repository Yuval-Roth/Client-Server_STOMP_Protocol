#include "SubscribeFrame.h"

SubscribeFrame::SubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}