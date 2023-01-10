#pragma once

#include "Frame.h"

class SubscribeFrame : public Frame{
    public:
        static SubscribeFrame* get(string gameName);

};