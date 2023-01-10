#pragma once

#include "ExecutableFrame.h"

class MessageFrame : public ExecutableFrame {
    public:
        MessageFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute();
};