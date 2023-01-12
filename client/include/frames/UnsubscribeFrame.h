#pragma once

using namespace std;

#include "Frame.h"

class UnsubscribeFrame : public Frame {
    public:
        UnsubscribeFrame(unordered_map<string, string> headers, string frameBody);
        static UnsubscribeFrame* get(string topic);
};