#pragma once

#include "ExecutableFrame.h"

class SendFrame : public ExecutableFrame {
    public:
        SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};