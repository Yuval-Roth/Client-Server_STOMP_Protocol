#pragma once

#include "ExecutableFrame.h"

class ErrorFrame : public ExecutableFrame {
    public:
        ErrorFrame(unordered_map<string, string> headers, string frameBody);
        void execute();
};