#pragma once

#include "ExecutableFrame.h"

class SubscribeFrame : public ExecutableFrame {
    public:
        SubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};