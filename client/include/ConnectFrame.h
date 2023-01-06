#pragma once

#include "ExecutableFrame.h"

class ConnectFrame : public ExecutableFrame {
    public:
        ConnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};