#pragma once
#include "Frame.h"

class Event;
class SendFrame : public Frame {
    public:
        SendFrame(unordered_map<string, string> headers, string frameBody);
        static SendFrame *get(Event& event);
};