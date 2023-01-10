#pragma once
#include "Frame.h"

class ConnectedFrame : public Frame {
    public:
        ConnectedFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};