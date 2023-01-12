#pragma once

#include "ExecutableFrame.h"

class DisconnectFrame : public Frame {
    public:
        DisconnectFrame(unordered_map<string, string> headers, string frameBody);
        static DisconnectFrame *get();
};