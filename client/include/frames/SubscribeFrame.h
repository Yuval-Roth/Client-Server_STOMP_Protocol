#pragma once

#include "Frame.h"

class SubscribeFrame : public Frame{
    public:
        static SubscribeFrame* get(string gameName);
        SubscribeFrame(unordered_map<string, string> headers, string frameBody);
};