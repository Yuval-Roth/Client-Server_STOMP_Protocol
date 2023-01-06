#pragma once

#include "ExecutableFrame.h"

class UnsubscribeFrame : public ExecutableFrame {
    public:
        UnsubscribeFrame(unordered_map<string, string> headers, string frameBody);
        void execute(ConnectionHandler& handler);
};