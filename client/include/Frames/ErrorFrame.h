#pragma once

#include "Frame.h"

class ErrorFrame : public Frame {
    public:
        ErrorFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};