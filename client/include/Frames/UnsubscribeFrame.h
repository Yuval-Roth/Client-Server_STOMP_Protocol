#pragma once

#include "Frame.h"

class UnsubscribeFrame : public Frame {
    public:
        UnsubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};