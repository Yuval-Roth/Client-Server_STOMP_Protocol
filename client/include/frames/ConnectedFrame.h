#pragma once
#include "ExecutableFrame.h"

class ConnectedFrame : public ExecutableFrame {
    public:
        ConnectedFrame(unordered_map<string, string> headers, string frameBody);
        void execute();
};