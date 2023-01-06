#pragma once

#include "ExecutableFrame.h"

class MessageFrame : public ExecutableFrame {
    public:
        MessageFrame(unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};