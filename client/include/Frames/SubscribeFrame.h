#pragma once

#include "Frame.h"

class SubscribeFrame : public Frame {
    public:
        SubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
};