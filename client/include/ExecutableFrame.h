#pragma once

#include "Frame.h"
#include "ConnectionHandler.h"

class ExecutableFrame : public Frame {
    public:
        ExecutableFrame(unordered_map<string, string> headers, string frameBody);
        virtual void execute(ConnectionHandler& handler) = 0;
};