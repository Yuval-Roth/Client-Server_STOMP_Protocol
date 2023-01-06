#pragma once

#include "ExecutableFrame.h"

class DisconnectFrame : public ExecutableFrame {
    public:
        DisconnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};