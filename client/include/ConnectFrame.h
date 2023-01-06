#pragma once

#include "ExecutableFrame.h"

class ConnectFrame : public ExecutableFrame {
    public:
        ConnectFrame(unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};