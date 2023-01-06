#pragma once

#include "Frame.h"

class ErrorFrame : public Frame {
    public:
        ErrorFrame(unordered_map<string, string> headers, string frameBody);
};