#pragma once

#include "ExecutableFrame.h"

class ErrorFrame : public ExecutableFrame {
    public:
        ErrorFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        void execute();
};