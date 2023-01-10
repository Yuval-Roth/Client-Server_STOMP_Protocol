#pragma once

using namespace std;

#include "Frame.h"

class UnsubscribeFrame : public Frame {
    public:
        UnsubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody);
        static UnsubscribeFrame* get(int subId,string topic);
};